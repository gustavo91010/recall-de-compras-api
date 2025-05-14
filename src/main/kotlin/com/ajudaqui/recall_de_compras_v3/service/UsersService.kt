package com.ajudaqui.recall_de_compras_v3.service

import com.ajudaqui.recall_de_compras_v3.entity.Users
import com.ajudaqui.recall_de_compras_v3.exception.NotFoundException
import com.ajudaqui.recall_de_compras_v3.repository.UsersRepository
import java.util.UUID
import kotlin.toString
import org.springframework.stereotype.Service

@Service
class UsersService(private val usersRepository: UsersRepository) {

  fun create(name: String): Users =
          usersRepository.save(Users(name = name, accessToken = UUID.randomUUID().toString()))

  fun findByAccessToken(accessToken: String): Users =
          usersRepository.findByAccessToken(accessToken).orElseThrow {
            NotFoundException("Usuário nao encontrado")
          }
  fun findById(userId: Long): Users =
          usersRepository.findById(userId).orElseThrow {
            NotFoundException("Usuário nao encontrado")
          }
}
