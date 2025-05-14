package com.ajudaqui.recall_de_compras_v3.controller

import com.ajudaqui.recall_de_compras_v3.entity.Purchase
import com.ajudaqui.recall_de_compras_v3.service.PurchaseService
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/purchase")
class PurchaseController(private val purchaseService: PurchaseService) {

  private val logger = LoggerFactory.getLogger(PurchaseController::class.java)

  @Transactional
  @PostMapping("/new/{name}")
  fun create(
          @RequestHeader("Authorization") accessToken: String,
          @PathVariable name: String
  ): ResponseEntity<Purchase> {
    logger.info("[POST] | /v1/purchase/new?$name")
    return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.create(accessToken, name))
  }
}
