package service

import data.ProductCreateDto
import data.ProductsListDto
import data.ProductDto
import web.Result
import java.util.*

interface ProductsService {
    fun add(dto: ProductCreateDto): Result.Object<UUID>
    fun list(dto: ProductsListDto): Result.Page<ProductDto>
    fun get(id: UUID): Result.Object<ProductDto>
}