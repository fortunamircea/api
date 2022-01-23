package repository

import data.CreateProductDto
import data.GetProductDto
import data.ListProductsDto
import data.ProductDto
import java.util.*

interface ProductsRepository {
    fun add(dto: CreateProductDto): UUID
    fun list(dto: ListProductsDto): Pair<List<ProductDto>, Long>
    fun get(id:UUID): ProductDto?
}