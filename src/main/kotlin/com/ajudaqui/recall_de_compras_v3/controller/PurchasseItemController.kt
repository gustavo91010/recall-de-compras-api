package com.ajudaqui.recall_de_compras_v3.controller

import com.ajudaqui.recall_de_compras_v3.dto.ProductDTO
import com.ajudaqui.recall_de_compras_v3.entity.PurchaseItem
import com.ajudaqui.recall_de_compras_v3.service.PurchaseItemService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


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
; 
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

    @GetMapping("/{id}")
    fun getById(@RequestHeader("Authorization") accessToken: String, @PathVariable("id") id: Long) =
        ResponseEntity.ok(
            purchaseItemService.findById(id, accessToken)
        )


    @DeleteMapping("{id}")
    fun delete(
        @RequestHeader("Authorization") accessToken: String,
        @PathVariable("id") id: Long
    ): ResponseEntity<Void> {
        purchaseItemService.delete(id, accessToken)
        return ResponseEntity.noContent().build()
    }
}
