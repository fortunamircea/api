package service

import data.CategoryDto
import data.CreateCategoryDto
import data.GetCategoryDto
import data.ListCategoryDto
import web.Result
import java.util.*

interface CategoriesService {
    fun add(dto: CreateCategoryDto): Result.Object<UUID>
    fun list(dto: ListCategoryDto): Result.Page<CategoryDto>
    fun get(dto: GetCategoryDto): Result.Object<CategoryDto>
}