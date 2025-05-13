package com.ajudaqui.recall_de_compras_v3.dto

import com.ajudaqui.recall_de_compras_v3.entity.Product
import com.ajudaqui.recall_de_compras_v3.entity.Users
import java.math.BigDecimal

data class ProductDTO(
        var name: String,
        var brand: String,
        val measureUnit: String,
        val price: BigDecimal = BigDecimal.ZERO,
) {

  fun toProduct(users: Users): Product {
    return Product(
        null,
        name,
        brand,
        measureUnit,
        price,
        "",
        users
    )
  }
}
