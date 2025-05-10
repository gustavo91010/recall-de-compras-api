package com.ajudaqui.recall_de_compras_v3.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime
import jakarta.persistence.Id

@Entity
data class Purchase(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
        @OneToMany(mappedBy = "purchase") val itens: MutableList<PurchaseItem> = mutableListOf(),
        val createAt: LocalDateTime = LocalDateTime.now(),
        val updateAt: LocalDateTime = LocalDateTime.now(),
        @ManyToOne @JoinColumn(name = "user_id") @JsonIgnore val users: Users
) {}
