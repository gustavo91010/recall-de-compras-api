package com.ajudaqui.recall_de_compras_v3.service

import com.ajudaqui.recall_de_compras_v3.entity.Purchase
import com.ajudaqui.recall_de_compras_v3.exception.MessageException
import com.ajudaqui.recall_de_compras_v3.repository.PurchaseRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PurchaseService(
    private var purchaseRepository: PurchaseRepository,
    private var userService: UsersService
) {

    fun create(accessToken: String, name: String): Purchase {

        val user = userService.findByAccessToken(accessToken)
        purchaseRepository.findByName(name).ifPresent { throw MessageException("compra já registrada.") }
        return purchaseRepository.save(Purchase(name = name, users = user))
    }

    fun findById(id: Long): Purchase =
        purchaseRepository.findById(id).orElseThrow {
            throw MessageException("Compra não localizada")
        }

    fun findByName(name: String): Purchase =
        purchaseRepository.findByName(name).orElseThrow {
            throw MessageException("Compra não localizada")
        }

    fun allPurchases(accessToken: String): List<Purchase> =
        purchaseRepository.allPurchsse(accessToken)

    fun changeName(purchaseId: Long, name: String): Purchase
    = update(findById(purchaseId).copy(name = name))


    fun update(purchase: Purchase): Purchase
    = purchaseRepository.save(purchase.copy(updateAt = LocalDateTime.now()))

}
