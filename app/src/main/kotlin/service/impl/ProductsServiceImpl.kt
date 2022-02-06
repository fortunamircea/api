package service.impl

import data.ProductCreateDto
import data.ProductsListDto
import data.ProductDto
import exceptions.APINotFoundException
import org.springframework.stereotype.Service
import repository.CategoriesRepository
import repository.ProductsRepository
import service.ProductsService
import web.PagingDto
import web.Result
import java.util.*

@Service
class ProductsServiceImpl(
    private val productsRepository: ProductsRepository,
    private val categoriesRepository: CategoriesRepository
) : ProductsService {
    override fun add(dto: ProductCreateDto): Result.Object<UUID> {
        if (!categoriesRepository.exists(dto.category_id)) {
            throw APINotFoundException("No category found for provided id")
        }
        return Result.Object(productsRepository.add(dto))
    }

    override fun list(dto: ProductsListDto): Result.Page<ProductDto> {
        val data = productsRepository.list(dto)
        return Result.Page(data.first, PagingDto(dto.offset, dto.limit, data.second))
    }

    override fun get(id: UUID): Result.Object<ProductDto> =
        Result.Object(productsRepository.get(id) ?: throw APINotFoundException("No product found for provided id"))
}