package com.ajudaqui.recall_de_compras_v3.service

import com.ajudaqui.recall_de_compras_v3.entity.Product
import com.ajudaqui.recall_de_compras_v3.entity.PurchaseItem
import com.ajudaqui.recall_de_compras_v3.exception.NotFoundException
import com.ajudaqui.recall_de_compras_v3.repository.PurchaseItemRepository
import java.math.BigDecimal
import org.springframework.stereotype.Service

@Service
class PurchaseItemService(
        private var purchaseItemRepository: PurchaseItemRepository,
        private var purchaseService: PurchaseService,
        private var productService: ProductService,
) {

  fun create(
          purchaseId: Long,
          nome: String,
          brand: String,
          unit: String,
          price: String,
          quantity: Double,
  ): PurchaseItem {
    var purchase = purchaseService.findById(purchaseId)
    purchaseService.findById(purchaseId)
    var product =
            Product(
                    name = nome,
                    brand = brand,
                    measuret_unit = unit,
                    price = BigDecimal(price),
                    users = purchase.users
            )
    return purchaseItemRepository.save(
            PurchaseItem(product = product, quantity = quantity, purchase = purchase)
    )
  }

  fun findById(id: Long): PurchaseItem =
          purchaseItemRepository.findById(id).orElseThrow {
            throw NotFoundException("Item não encontrado")
          }

  fun findByPurchase(purchaseId: Long): List<PurchaseItem> =
          purchaseItemRepository.findByPurchaseId(purchaseId)

  // fun findByPurchase(purchaseId:Long):List<PurchaseItem>=
  //           purchaseItemRepository.findByPurchase(purchaseId)

}
