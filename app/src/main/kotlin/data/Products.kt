package data

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class CreateProductDto(
    val type: String,
    val category_id: UUID,
    val price: BigDecimal?,
    val units: BigDecimal?
)

data class GetProductDto(
    val id: UUID
)

data class ProductDto(
    val id: UUID,
    val type: String,
    val category_id: UUID,
    val price: BigDecimal?,
    val units: BigDecimal?,
    val created_At: LocalDateTime,
    val updated_At: LocalDateTime?

)

data class ListProductsDto(
    val limit: Int = 20,
    val offset: Int = 0,
    val productsIds: List<UUID>?
)