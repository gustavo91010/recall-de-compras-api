package com.ajudaqui.recall_de_compras_v3.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class Test {
    @GetMapping
    fun test(): Response = Response("lala")
}

data class Response(
    val response: String,
)
