package com.ajudaqui.recall_de_compras_v3.exception

data class ErrorResponse(
    val message: String,
    val timestamp: String = java.time.LocalDateTime.now().toString(),
    val details: String? = null,
    val errors: List<FieldErrorResponse>? = null
)

data class FieldErrorResponse(
    val field: String,
    val message: String
)

