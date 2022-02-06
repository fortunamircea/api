package repository

import java.util.*

interface CartRepository {
    fun add(customerId: UUID): UUID
    fun get(customerId: UUID): UUID
}