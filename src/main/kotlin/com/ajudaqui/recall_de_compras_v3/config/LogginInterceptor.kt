package com.ajudaqui.recall_de_compras_v3.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor


class LogginInterceptor : HandlerInterceptor {
    private val logger = LoggerFactory.getLogger(LogginInterceptor::class.java)
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        println("chamou aqui")
        val params = request.parameterMap.entries.joinToString(", ") {
            "${it.key}=${it.value.joinToString()} | "
        }
        val textParams= params.ifEmpty { "" }
        val clientIp=request.remoteAddr
        logger.info("Request: [${request.method}] ${request.requestURI} | $textParams IP: $clientIp")
        return true;
    }
}
