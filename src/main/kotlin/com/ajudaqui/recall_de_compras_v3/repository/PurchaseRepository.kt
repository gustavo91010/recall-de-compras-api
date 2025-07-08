package com.ajudaqui.recall_de_compras_v3.repository

import com.ajudaqui.recall_de_compras_v3.entity.Purchase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.util.Optionals
import java.util.Optional

interface PurchaseRepository : JpaRepository<Purchase, Long> {
    @Query(value = "SELECT p FROM Purchase p WHERE p.users.accessToken= :accessToken")
    fun allPurchsse(accessToken: String): List<Purchase>


    fun findByName(name: String): Optional<Purchase>
}
