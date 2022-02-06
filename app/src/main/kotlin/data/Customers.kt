package data

import java.time.LocalDateTime
import java.util.*

data class CustomerCreateDto(
    val first_name: String,
    val last_name: String,
    val email: String?
)


data class CustomerGetDto(
    val id: UUID
)

data class CustomerResultDto(
    val id: UUID,
    val cart_id: UUID,
)

data class CustomerDto(
    val id: UUID,
    val first_name: String,
    val last_name: String,
    val email: String?,
    val cart_id: UUID,
    val created_At: LocalDateTime,
    val updated_At: LocalDateTime?
)

data class CustomersListDto(
    val limit: Int = 20,
    val offset: Int = 0,
    val customers_id: List<UUID>?
)