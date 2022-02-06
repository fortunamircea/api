package repository

import data.CartItemCreateDto
import data.CartItemDto
import data.CartItemListDto
import data.CartItemUpdateDto
import java.util.*

interface CartItemRepository {
    fun add(dto: CartItemCreateDto): UUID
    fun exists(cartItemId: UUID): Boolean
    fun update(dto: CartItemUpdateDto)
    fun delete(id: UUID)
    fun list(dto: CartItemListDto): Pair<List<CartItemDto>,Long>
}