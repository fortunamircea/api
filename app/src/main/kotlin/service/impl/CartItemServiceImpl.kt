package service.impl

import data.CartItemCreateDto
import data.CartItemDto
import data.CartItemListDto
import data.CartItemUpdateDto
import exceptions.APINotFoundException
import org.springframework.stereotype.Service
import repository.CartItemRepository
import repository.ProductsRepository
import service.CartItemService
import web.PagingDto
import web.Result
import java.util.*

@Service
class CartItemServiceImpl(
    private val cartItemRepository: CartItemRepository,
    private val productsRepository: ProductsRepository
) : CartItemService {
    override fun add(dto: CartItemCreateDto): Result.Object<UUID> {
        return Result.Object(cartItemRepository.add(dto))
    }

    override fun update(dto: CartItemUpdateDto): Result.Ok {
        if (!cartItemRepository.exists(dto.cart_id)) throw APINotFoundException("No cart found for provided id")
        if (!productsRepository.exist(dto.product_id)) throw APINotFoundException("No product found for provided id")
        val quantity = productsRepository.get(dto.product_id).units
        if (quantity == null || quantity < dto.quantity) throw APINotFoundException("Provided quantity exceeded available stocks")
        cartItemRepository.update(dto)
        return Result.Ok
    }

    override fun delete(id: UUID): Result.Ok {
        if (!cartItemRepository.exists(id)) throw APINotFoundException("No cart item found for provide id")
        cartItemRepository.delete(id)
        return Result.Ok
    }

    override fun list(dto: CartItemListDto): Result.Page<CartItemDto> {
        val response = cartItemRepository.list(dto)
        return Result.Page(response.first, PagingDto(dto.offset, dto.limit, response.second))
    }
}