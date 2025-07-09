package com.ajudaqui.recall_de_compras_v3.controller

import com.ajudaqui.recall_de_compras_v3.service.PurchaseService
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
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
    ) = ResponseEntity.status(HttpStatus.CREATED)
        .body(purchaseService.create(accessToken, name))


    @GetMapping("/")
    fun findAll(
        @RequestHeader("Authorization") accessToken: String
    ) = ResponseEntity.ok(purchaseService.allPurchases(accessToken))

    @GetMapping("/{name}")
    fun findByName(
        @PathVariable ("name")name: String, @RequestHeader("Authorization") accessToken: String
    ) = ResponseEntity.ok(purchaseService.findByName(name))

}
