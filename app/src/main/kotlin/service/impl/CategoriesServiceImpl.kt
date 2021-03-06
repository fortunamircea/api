package service.impl

import data.CategoryDto
import data.CategoryCreateDto
import data.CategoryListDto
import exceptions.APINotFoundException
import org.springframework.stereotype.Service
import repository.CategoriesRepository
import service.CategoriesService
import web.PagingDto
import web.Result
import java.util.*

@Service
class CategoriesServiceImpl(private val repository: CategoriesRepository) : CategoriesService {
    override fun add(dto: CategoryCreateDto): Result.Object<UUID> =
        Result.Object(repository.add(dto))

    override fun list(dto: CategoryListDto): Result.Page<CategoryDto> {
        val response = repository.list(dto)
        return Result.Page(response.first, PagingDto(dto.offset, dto.limit, response.second))
    }


    override fun get(id: UUID): Result.Object<CategoryDto> =
        Result.Object(repository.get(id) ?: throw APINotFoundException("No category found for provided id"))

}