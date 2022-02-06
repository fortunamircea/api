package service.impl

import data.CustomerCreateDto
import data.CustomerDto
import data.CustomersListDto
import exceptions.APINotFoundException
import org.springframework.stereotype.Service
import repository.CustomersRepository
import service.CustomersService
import web.PagingDto
import web.Result
import java.util.*

@Service
class CustomersServiceImpl(private val repository: CustomersRepository) : CustomersService {
    override fun add(dto: CustomerCreateDto): Result.Object<UUID> =
        Result.Object(repository.add(dto))

    override fun list(dto: CustomersListDto): Result.Page<CustomerDto> {
        val response = repository.list(dto)
        return Result.Page(response.first, PagingDto(dto.offset, dto.limit, response.second))
    }
    override fun get(id: UUID): Result.Object<CustomerDto> =
        Result.Object(repository.get(id) ?: throw APINotFoundException("No customer found for provided id"))

}