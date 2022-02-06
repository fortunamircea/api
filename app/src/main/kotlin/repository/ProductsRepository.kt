package repository

import data.ProductCreateDto
import data.ProductsListDto
import data.ProductDto
import java.util.*

interface ProductsRepository {
    fun add(dto: ProductCreateDto): UUID
    fun list(dto: ProductsListDto): Pair<List<ProductDto>, Long>
    fun get(id: UUID): ProductDto
    fun exist(id:UUID):Boolean
}