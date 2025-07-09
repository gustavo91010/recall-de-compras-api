package com.ajudaqui.recall_de_compras_v3.controller

import com.ajudaqui.recall_de_compras_v3.dto.ProductDTO
import com.ajudaqui.recall_de_compras_v3.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/product")
class ProductController(private val productService: ProductService) {

    @PostMapping("/")
    fun create(
        @RequestHeader("Authorization") accessToken: String,
        @RequestBody producTDO: ProductDTO
    ) = ResponseEntity.status(HttpStatus.CREATED)
        .body(productService.create(accessToken, producTDO))

    @GetMapping("/")
    fun findAll(
        @RequestHeader("Authorization") accessToken: String,
    ) = ResponseEntity.ok( productService.findProduct(accessToken))

    @GetMapping("/name/{name}")
    fun findByName(
        @RequestHeader("Authorization") accessToken: String,
        @PathVariable("name") name:String
    ) = ResponseEntity.ok( productService.findByName(accessToken, name))
}
