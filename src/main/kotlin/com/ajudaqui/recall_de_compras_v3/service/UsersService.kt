package com.ajudaqui.recall_de_compras_v3.service

import com.ajudaqui.recall_de_compras_v3.entity.Users
import com.ajudaqui.recall_de_compras_v3.exception.NotFoundException
import com.ajudaqui.recall_de_compras_v3.repository.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService(private val usersRepository: UsersRepository) {

  fun findById(userId: Long): Users =
          usersRepository.findById(userId).orElseThrow {
            NotFoundException("Usuário nao encontrado")
          }
}
