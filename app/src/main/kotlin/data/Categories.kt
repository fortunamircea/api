package data

import java.time.LocalDateTime
import java.util.*

data class CategoryCreateDto(
    val type: String
)


data class CategoryGetDto(
    val id: UUID
)

data class CategoryDto(
    val id: UUID,
    val type: String,
    val created_At: LocalDateTime,
    val updated_At: LocalDateTime?
)

data class CategoryListDto(
    val limit: Int = 20,
    val offset: Int = 0,
    val categories_id: List<UUID>?
)