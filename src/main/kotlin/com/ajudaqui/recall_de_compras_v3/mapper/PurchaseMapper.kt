package com.ajudaqui.recall_de_compras_v3.mapper

import com.ajudaqui.recall_de_compras_v3.dto.ResponsePurchaseDTO
import com.ajudaqui.recall_de_compras_v3.entity.Purchase
import java.math.BigDecimal
import java.math.RoundingMode

object PurchaseMapper {
    fun toPurchaseResponse(purchase: Purchase): ResponsePurchaseDTO {
        val totalValue = purchase.itens.sumOf { it.totalItem.toDouble() }
        val totalItens = purchase.itens.size

        return ResponsePurchaseDTO(
            id = purchase.id,
            name = purchase.name,
            createAt = purchase.createAt,
            updateAt = purchase.updateAt,
            totalItens = totalItens,
            totalValue = BigDecimal(totalValue).setScale(2, RoundingMode.HALF_UP),
            itens = purchase.itens

        )
    }

    fun toListPurchaseResponse(purchases: List<Purchase>): List<ResponsePurchaseDTO> =
        purchases.map { toPurchaseResponse(it) }
}
