package com.ajudaqui.recall_de_compras_v3.repository

import com.ajudaqui.recall_de_compras_v3.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UsersRepository : JpaRepository<Users, Long> {

}
