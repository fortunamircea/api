package web.controller

import data.CustomerCreateDto
import data.CustomerDto
import data.CustomerGetDto
import data.CustomersListDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import service.CustomersService
import web.Result
import java.util.*

@Tag(name = "Customers")
@RestController
@RequestMapping("/customers")
class CustomersController(private val service: CustomersService) {

    @Operation(summary = "Customers list")
    @PostMapping("/list")
    fun list(@RequestBody dto: CustomersListDto): Result.Page<CustomerDto> =
        service.list(dto)

    @Operation(summary = "Customer get")
    @GetMapping("/get")
    fun get(@RequestBody dto: CustomerGetDto): Result.Object<CustomerDto> =
        service.get(dto.id)

    @Operation(summary = "Category create")
    @PostMapping("/create")
    fun create(@RequestBody dto: CustomerCreateDto): Result.Object<UUID> =
        service.add(dto)
}