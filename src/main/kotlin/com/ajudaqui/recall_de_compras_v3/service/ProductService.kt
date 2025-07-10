package com.ajudaqui.recall_de_compras_v3.service

import com.ajudaqui.recall_de_compras_v3.dto.ProductDTO
import com.ajudaqui.recall_de_compras_v3.entity.Product
import com.ajudaqui.recall_de_compras_v3.exception.MessageException
import com.ajudaqui.recall_de_compras_v3.exception.NotFoundException
import com.ajudaqui.recall_de_compras_v3.exception.UnauthorizedException
import com.ajudaqui.recall_de_compras_v3.repository.ProductRepository
import com.ajudaqui.recall_de_compras_v3.repository.specification.productSpec
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val usersService: UsersService
) {

    fun getOrCreate(productDto: ProductDTO, accessToken: String): Product {
        val found = findProduct(accessToken, productDto.name, productDto.brand, productDto.measureUnit)
        return found.firstOrNull() ?: create(accessToken, productDto)
    }

    fun create(
        accessToken: String,
        productDTO: ProductDTO,
    ): Product {

        productDTO.let {
            if (findProduct(accessToken, it.name, it.brand, it.measureUnit).isNotEmpty()) {
                throw MessageException("Produto já regsitrado")
            }
        }
        return save(productDTO.toProduct(usersService.findByAccessToken(accessToken)))
    }

    fun save(product: Product): Product = productRepository.save(product)

    fun findProduct(accessToken: String, name: String?=null, brand: String?=null, measureUnit: String?=null): List<Product> =
        productRepository.findAll(
            productSpec(
                usersService.findByAccessToken(accessToken).id!!,
                name,
                brand,
                measureUnit
            )
        )


    fun findByBrand(brand: String): List<Product> =
        productRepository.findByBrand(brand)

//    fun findByName(accessToken: String, name: String): List<Product> =
//        productRepository.findByName(accessToken,name);

    fun findById(id: Long): Product =
        productRepository.findById(id).orElseThrow { NotFoundException("Produto não localizado") }

    fun update(userId: Long, productId: Long, productDTO: ProductDTO): Product {
        val product = findById(productId)
//        if (product.users.id != userId) {
    //
//            throw UnauthorizedException("Solicitação não autorizada")
//        }
        val uploadProduct =
            product.copy(
                name = productDTO.name,
                brand = productDTO.brand,
                measureUnit = productDTO.measureUnit,
                price = productDTO.price
            )

        return save(uploadProduct)
    }
}
