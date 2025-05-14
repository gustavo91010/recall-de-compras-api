package com.ajudaqui.recall_de_compras_v3.repository

import com.ajudaqui.recall_de_compras_v3.entity.Users
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UsersRepository : JpaRepository<Users, Long> {
  @Query(value = "SELECT * FROM users WHERE access_token= :accessToken", nativeQuery = true)
  fun findByAccessToken(@Param("accessToken") accessToken: String): Optional<Users>
}
