package com.ajudaqui.recall_de_compras_v3.service

import com.ajudaqui.recall_de_compras_v3.dto.ProductDTO
import com.ajudaqui.recall_de_compras_v3.entity.PurchaseItem
import com.ajudaqui.recall_de_compras_v3.exception.NotFoundException
import com.ajudaqui.recall_de_compras_v3.repository.PurchaseItemRepository
import org.springframework.stereotype.Service

@Service
class PurchaseItemService(
        private var purchaseItemRepository: PurchaseItemRepository,
        private var purchaseService: PurchaseService,
        private var productService: ProductService,
) {

  fun create(purchaseId: Long, quantity: Double, productDto: ProductDTO): PurchaseItem {
    var purchase = purchaseService.findById(purchaseId)

    var product = productService.getOrCreate(productDto, purchase.users.id!!)

    return save(
            PurchaseItem(product = product, quantity = quantity, purchase = purchase)
    )
  }
  fun findById(id: Long): PurchaseItem =
          purchaseItemRepository.findById(id).orElseThrow {
            throw NotFoundException("Item não encontrado")
          }

  fun findByPurchase(purchaseId: Long): List<PurchaseItem> =
          purchaseItemRepository.findByPurchaseId(purchaseId)

  fun update(purchaseItemId: Long, quantity: Double, productDto: ProductDTO) {

    val item = findById(purchaseItemId)
    var product = productService.getOrCreate(productDto, item.purchase.users.id!!)
    save(item.copy(quantity = quantity, product = product))
  }

  private fun save(purchaseItem: PurchaseItem): PurchaseItem =
          purchaseItemRepository.save(purchaseItem)

  // fun findByPurchase(purchaseId:Long):List<PurchaseItem>=
  //           purchaseItemRepository.findByPurchase(purchaseId)

}
