package com.ajudaqui.recall_de_compras_v3.repository

import com.ajudaqui.recall_de_compras_v3.entity.PurchaseItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PurchaseItemRepository : JpaRepository<PurchaseItem, Long> {
  @Query(value = "SELECT * FROM purchase_item WHERE purchase_id= :purchaseId", nativeQuery = true)
  fun findByPurchaseId(purchaseId: Long): List<PurchaseItem>
}
