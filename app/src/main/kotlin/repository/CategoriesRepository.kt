package repository

import data.CategoryDto
import data.CreateCategoryDto
import data.ListCategoryDto
import java.util.*

interface CategoriesRepository {
    fun add(dto: CreateCategoryDto): UUID
    fun list(dto: ListCategoryDto): Pair<List<CategoryDto>, Long>
    fun get(id: UUID): CategoryDto
    fun exists(id:UUID):Boolean
}