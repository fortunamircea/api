package service

import data.CategoryDto
import data.CategoryCreateDto
import data.CategoryListDto
import web.Result
import java.util.*

interface CategoriesService {
    fun add(dto: CategoryCreateDto): Result.Object<UUID>
    fun list(dto: CategoryListDto): Result.Page<CategoryDto>
    fun get(id: UUID): Result.Object<CategoryDto>
}