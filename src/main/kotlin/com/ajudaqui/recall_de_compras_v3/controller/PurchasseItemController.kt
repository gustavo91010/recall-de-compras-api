package com.ajudaqui.recall_de_compras_v3.controller

import com.ajudaqui.recall_de_compras_v3.service.PurchaseItemService
import com.ajudaqui.recall_de_compras_v3.dto.ProductDTO
import com.ajudaqui.recall_de_compras_v3.entity.PurchaseItem

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.http.ResponseEntity


@RestController
@RequestMapping("/v1/purchasse-item")
class PurchasseItemController(private val purchaseItemService: PurchaseItemService) {
    private val logger = LoggerFactory.getLogger(PurchasseItemController::class.java)


    @PostMapping("/create/{purchaseId}")
    fun createItem(
        @PathVariable purchaseId: Long,
        @RequestParam quantity: Double,
        @RequestBody productDtop: ProductDTO
    ): ResponseEntity<PurchaseItem> {

        return try {
            require(quantity > 0) { "Quantidade deve ser maior que zero" }
            ResponseEntity.ok(purchaseItemService.create(purchaseId, quantity, productDtop))
        } catch (ex: IllegalArgumentException) {
            logger.warn("Erro de validação: ${ex.message}")
            ResponseEntity.badRequest().build()
        } catch (ex: NoSuchElementException) {
            logger.error("Compra ou usuário não encontrado: ${ex.message}")
            ResponseEntity.notFound().build()
        }
    }
}



