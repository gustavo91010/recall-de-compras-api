package com.ajudaqui.recall_de_compras_v3.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.Columns
import java.math.BigDecimal

@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val name: String,
    val brand: String,
    @Column(name = "measuret_unit")
    val measureUnit: String,
    val price: BigDecimal = BigDecimal.ZERO,
    val observation: String? = "",


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "access_token", referencedColumnName = "access_token", nullable = false)
    @JsonIgnore
    val users: Users
)
