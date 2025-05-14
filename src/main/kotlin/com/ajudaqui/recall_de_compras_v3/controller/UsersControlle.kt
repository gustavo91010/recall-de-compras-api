package com.ajudaqui.recall_de_compras_v3.controller

import com.ajudaqui.recall_de_compras_v3.entity.Users
import com.ajudaqui.recall_de_compras_v3.service.UsersService
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
class UsersController(private val usersService: UsersService) {

  private val logger = LoggerFactory.getLogger(UsersController::class.java)

  @Transactional
  @PostMapping("/register/{name}")
  fun create(@PathVariable name: String): ResponseEntity<Users> {
    logger.info("[POST] | /v1/users/register/$name")
    return ResponseEntity.status(HttpStatus.CREATED).body(usersService.create(name))
  }
}
