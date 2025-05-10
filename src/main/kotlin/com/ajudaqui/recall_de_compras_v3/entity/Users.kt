package com.ajudaqui.recall_de_compras_v3.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.OneToMany
import jakarta.persistence.Id
@Entity
data class Users(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val name: String,
        val active: Boolean,
        val accessToken: String,
        @OneToMany(mappedBy = "users") @JsonIgnore val purchases: List<Purchase> = emptyList(),
) {}
