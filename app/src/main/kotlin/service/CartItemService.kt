package service

import data.CartItemCreateDto
import data.CartItemDto
import data.CartItemListDto
import data.CartItemUpdateDto
import web.Result
import java.util.*

interface CartItemService {

    fun add(dto: CartItemCreateDto): Result.Object<UUID>
    fun update(dto: CartItemUpdateDto): Result.Ok
    fun delete(id: UUID): Result.Ok
    fun list(dto: CartItemListDto): Result.Page<CartItemDto>
}