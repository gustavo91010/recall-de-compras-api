package com.ajudaqui.recall_de_compras_v3.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class Users(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val name: String,
        val active: Boolean?= true,
        val accessToken: String,
        @OneToMany(mappedBy = "users") @JsonIgnore val purchases: List<Purchase> = emptyList(),
        @OneToMany(mappedBy = "users", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JsonIgnore
        val items: List<Product> = emptyList()
) {}
