package com.ajudaqui.recall_de_compras_v3.service

import com.ajudaqui.recall_de_compras_v3.dto.ProductDTO
import com.ajudaqui.recall_de_compras_v3.entity.Product
import com.ajudaqui.recall_de_compras_v3.exception.MessageException
import com.ajudaqui.recall_de_compras_v3.exception.NotFoundException
import com.ajudaqui.recall_de_compras_v3.exception.UnauthorizedException
import com.ajudaqui.recall_de_compras_v3.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
        private val productRepository: ProductRepository,
        private val usersService: UsersService
) {

  fun getOrCreate(productDto: ProductDTO, userId: Long):Product {
    var found = findProduct(userId, productDto.name, productDto.brand, productDto.measureUnit)
    return found.firstOrNull() ?: create(userId, productDto)

  }

  fun create(
          userId: Long,
          productDTO: ProductDTO,
  ): Product {
    productDTO.let {
      if (findProduct(userId, it.name, it.brand, it.measureUnit).isNotEmpty()) {
        throw MessageException("Produto já regsitrado")
      }
    }
    return save(productDTO.toProduct(usersService.findById(userId)))
  }

  fun save(product: Product): Product = productRepository.save(product)

  fun findProduct(userId: Long, name: String, brand: String, measureUnit: String): List<Product> {

    return when {
      name.isBlank() && brand.isBlank() && measureUnit.isBlank() ->
              productRepository.findProduct(userId)
      name.isNotBlank() && brand.isBlank() && measureUnit.isBlank() ->
              productRepository.findProduct(userId, name)
      name.isNotBlank() && brand.isNotBlank() && measureUnit.isBlank() ->
              productRepository.findProduct(userId, name, brand)
      else -> productRepository.findProduct(userId, name, brand, measureUnit)
    }
  }

  fun findById(id: Long): Product =
          productRepository.findById(id).orElseThrow { NotFoundException("Produto não localizado") }

  fun update(userId: Long, productId: Long, productDTO: ProductDTO): Product {
    val product = findById(productId)
    if (product.users.id != userId) {
      throw UnauthorizedException("Solicitação não autorizada")
    }
    val uploadProduct =
            product.copy(
                    name = productDTO.name,
                    brand = productDTO.brand,
                    measuret_unit = productDTO.measureUnit,
                    price = productDTO.price
            )

    return save(uploadProduct)
  }
}
