package service

import data.CreateProductDto
import data.GetProductDto
import data.ListProductsDto
import data.ProductDto
import web.Result
import java.util.*

interface ProductsService {
    fun add(dto: CreateProductDto): Result.Object<UUID>
    fun list(dto: ListProductsDto): Result.Page<ProductDto>
    fun get(dto: GetProductDto): Result.Object<ProductDto>
}