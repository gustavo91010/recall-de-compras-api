package com.ajudaqui.recall_de_compras_v3.service

import com.ajudaqui.recall_de_compras_v3.entity.Purchase
import com.ajudaqui.recall_de_compras_v3.repository.PurchaseRepository
import org.springframework.stereotype.Service

@Service
class PurchaseService(
        private var purchaseRepository: PurchaseRepository,
        private var userService: UsersService
// private var purchaseService: PurchaseService

) {

  fun create(userId: Long, name: String): Purchase {

    var user = userService.findById(userId)

    return purchaseRepository.save(Purchase(name = name, users = user))
  }
  fun findById(id: Long): Purchase =
          purchaseRepository.findById(id).orElseThrow {
            throw ClassNotFoundException("Compra não lcalizada")
          }
}
