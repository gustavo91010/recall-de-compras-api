package com.ajudaqui.recall_de_compras_v3.entity

import jakarta.persistence.*
import jakarta.persistence.Id
import java.math.BigDecimal
import java.math.RoundingMode

@Entity
data class PurchaseItem(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        @ManyToOne val product: Product,
        val quantity: Double? = 0.0,
        @ManyToOne @JoinColumn(name = "purchase_id") val purchase: Purchase,
) {
  val totalItem: BigDecimal
    get() = product.price.multiply(BigDecimal(quantity ?: 0.0)).setScale(2, RoundingMode.HALF_UP)
}
