package com.ajudaqui.recall_de_compras_v3.service

import com.ajudaqui.recall_de_compras_v3.dto.ProductDTO
import com.ajudaqui.recall_de_compras_v3.entity.PurchaseItem
import com.ajudaqui.recall_de_compras_v3.entity.Users
import com.ajudaqui.recall_de_compras_v3.exception.NotFoundException
import com.ajudaqui.recall_de_compras_v3.exception.UnauthorizedException
import com.ajudaqui.recall_de_compras_v3.repository.PurchaseItemRepository
import org.springframework.stereotype.Service

@Service
class PurchaseItemService(
    private var purchaseItemRepository: PurchaseItemRepository,
    private var purchaseService: PurchaseService,
    private var productService: ProductService,
    private var usersService: UsersService
) {

    fun create(purchaseId: Long, quantity: Double, productDto: ProductDTO): PurchaseItem {
        val purchase = purchaseService.findById(purchaseId)
        purchaseService.update(purchase)
        val product = productService.getOrCreate(productDto, purchase.users.accessToken)
        return save(PurchaseItem(product = product, quantity = quantity, purchase = purchase))
    }

    fun findById(id: Long, accessToken: String): PurchaseItem =
        purchaseItemRepository.findById(id).orElseThrow {
            throw NotFoundException("Item não encontrado")
        }.also { checkPermission(it, usersService.findByAccessToken(accessToken)) }

    fun findByPurchase(purchaseId: Long): List<PurchaseItem> =
        purchaseItemRepository.findByPurchaseId(purchaseId)

    fun update(accessToken: String, purchaseItemId: Long, quantity: Double, productDto: ProductDTO) {

        val item = findById(purchaseItemId, accessToken)
        val product = productService.getOrCreate(productDto, item.purchase.users.accessToken)
        save(item.copy(quantity = quantity, product = product))
    }

    private fun save(purchaseItem: PurchaseItem): PurchaseItem =
        purchaseItemRepository.save(purchaseItem)

    fun delete(id: Long, accessToken: String) {
        purchaseItemRepository.delete(findById(id, accessToken))
    }

    private fun checkPermission(
        item: PurchaseItem, user: Users
    ) {
        if (item.purchase.users.id != user.id)
            throw UnauthorizedException("Solicitação não autorizada")
    }
}
