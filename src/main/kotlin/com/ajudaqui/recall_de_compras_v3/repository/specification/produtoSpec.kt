package com.ajudaqui.recall_de_compras_v3.repository.specification

import com.ajudaqui.recall_de_compras_v3.entity.Product
import com.ajudaqui.recall_de_compras_v3.entity.Users
import org.springframework.data.jpa.domain.Specification

fun productSpec(
    userId: Long,
    name: String?,
    brand: String?,
    measureUnit: String?
): Specification<Product> {
    return Specification { product, _, builder ->
        val predicates = mutableListOf(
            builder.equal(product.get<Users>("users").get<Long>("id"), userId)
        )

        name?.takeIf { it.isNotBlank() }?.let {
            predicates.add(builder.equal(product.get<String>("name"), it))
        }

        brand?.takeIf { it.isNotBlank() }?.let {
            predicates.add(builder.equal(product.get<String>("brand"), it))
        }

        measureUnit?.takeIf { it.isNotBlank() }?.let {
            predicates.add(builder.equal(product.get<String>("measureUnit"), it))
        }

        builder.and(*predicates.toTypedArray())
    }
}
