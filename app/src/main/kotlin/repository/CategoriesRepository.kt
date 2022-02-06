package repository

import data.CategoryDto
import data.CategoryCreateDto
import data.CategoryListDto
import java.util.*

interface CategoriesRepository {
    fun add(dto: CategoryCreateDto): UUID
    fun list(dto: CategoryListDto): Pair<List<CategoryDto>, Long>
    fun get(id: UUID): CategoryDto?
    fun exists(id: UUID): Boolean
}