package data

import java.time.LocalDateTime
import java.util.*

data class CreateCategoryDto(
    val type: String
)


data class GetCategoryDto(
    val id: UUID
)

data class CategoryDto(
    val id: UUID,
    val type: String,
    val created_At: LocalDateTime,
    val updated_At: LocalDateTime?
)

data class ListCategoryDto(
    val limit: Int = 20,
    val offset: Int = 0,
    val categoryIds: List<UUID>?
)