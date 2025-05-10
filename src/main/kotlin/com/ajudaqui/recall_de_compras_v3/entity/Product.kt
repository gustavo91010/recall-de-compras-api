package com.ajudaqui.recall_de_compras_v3.entity

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
data class Product(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val name: String,
        val brand: String,
        val measuret_unit: String,
        val price: BigDecimal = BigDecimal.ZERO,
        val observation: String
) {}
