package com.ajudaqui.recall_de_compras_v3.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

@Entity
data class Product(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val name: String,
        val brand: String,
        val measuret_unit: String,
        val price: BigDecimal = BigDecimal.ZERO,
        val observation: String? = "",
        @ManyToOne @JoinColumn(name = "user_id", nullable = false) @JsonIgnore val users: Users
) {}
