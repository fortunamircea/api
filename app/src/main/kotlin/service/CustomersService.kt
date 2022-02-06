package service

import data.CustomerCreateDto
import data.CustomerDto
import data.CustomersListDto
import web.Result
import java.util.*

interface CustomersService {
    fun add(dto: CustomerCreateDto): Result.Object<UUID>
    fun list(dto: CustomersListDto):Result.Page<CustomerDto>
    fun get(id: UUID):  Result.Object<CustomerDto>
}