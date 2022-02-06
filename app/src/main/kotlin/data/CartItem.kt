package data

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class CartItemCreateDto(
    val product_id: UUID,
    val cart_id: UUID,
    val quantity: BigDecimal
)

data class CartItemUpdateDto(
    val id: UUID,
    val product_id: UUID,
    val cart_id: UUID,
    val quantity: BigDecimal
)

data class CartItemDto(
    val id: UUID,
    val product_id: UUID,
    val cart_id: UUID,
    val quantity: BigDecimal,
    val created_at: LocalDateTime,
    val updated_at: LocalDateTime?,
)

data class CartItemDeleteDto(
    val id: UUID
)

data class CartItemListDto(
    val limit: Int = 20,
    val offset: Int = 0,
    val cart_id: UUID
)