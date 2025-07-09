package com.ajudaqui.recall_de_compras_v3.repository

import com.ajudaqui.recall_de_compras_v3.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface ProductRepository : JpaRepository<Product, Long>, JpaSpecificationExecutor <Product> {

    @Query(value = "SELECT * FROM product WHERE userId= :userId ", nativeQuery = true)
    fun findProduct(userId: Long): List<Product>
//
//    @Query(value = "SELECT * FROM product WHERE userId= :userId AND name= :name", nativeQuery = true)
//    fun findProduct(userId: Long, name: String): List<Product>
//
//    @Query(value = "SELECT * FROM product WHERE userId= :userId AND name= :name AND brand= :brand", nativeQuery = true)
//    fun findProduct(userId: Long, name: String, brand: String): List<Product>
//
//    //    @Query(value="SELECT * FROM product WHERE userId= :userId AND name= :name AND brand= :brand AND measuret_unit= :measuret_unit", nativeQuery= true)
//    @Query(value = "SELECT p FROM Product p WHERE p.users.id= :userId AND name= :name AND brand= :brand AND measuret_unit= :measuret_unit")
//    fun findProduct(userId: Long, name: String, brand: String, measuret_unit: String): List<Product>

    fun findByBrand(brand: String): List<Product>
    // fun findByName(accessToken: String, name: String): List<Product>
}
