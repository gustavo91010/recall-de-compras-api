package com.ajudaqui.recall_de_compras_v3.dto

import com.ajudaqui.recall_de_compras_v3.entity.PurchaseItem
import java.math.BigDecimal
import java.time.LocalDateTime

class ResponsePurchaseDTO(
    val id: Long?,
    val name: String,
    val totalItens: Int,
    val totalValue: BigDecimal,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime,
    val itens: MutableList<PurchaseItem> = mutableListOf(),
    ) {
}
