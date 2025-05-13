package com.ajudaqui.recall_de_compras_v3.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class Purchase(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        val name: String,
        @OneToMany(mappedBy = "purchase") val itens: MutableList<PurchaseItem> = mutableListOf(),
        val createAt: LocalDateTime = LocalDateTime.now(),
        val updateAt: LocalDateTime = LocalDateTime.now(),
        @ManyToOne @JoinColumn(name = "user_id") @JsonIgnore val users: Users
) {}
