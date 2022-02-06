package repository

import data.CustomerCreateDto
import data.CustomerDto
import data.CustomersListDto
import java.util.*

interface CustomersRepository {
    fun add(dto: CustomerCreateDto): UUID
    fun list(dto: CustomersListDto): Pair<List<CustomerDto>, Long>
    fun get(id: UUID): CustomerDto?
    fun exists(id: UUID): Boolean
}