package com.ajudaqui.recall_de_compras_v3.repository

import com.ajudaqui.recall_de_compras_v3.entity.Purchase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PurchaseRepository : JpaRepository<Purchase, Long> {

  @Query(value = "SELECT * FROM purchase WHERE user_id= :userId", nativeQuery = true)
  fun allPurchasse(userId: Long): List<Purchase>
}
