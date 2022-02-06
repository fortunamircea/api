package web.controller

import data.ProductCreateDto
import data.ProductGetDto
import data.ProductsListDto
import data.ProductDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import service.ProductsService
import web.Result
import java.util.*

@Tag(name = "Products")
@RestController
@RequestMapping("/products")
class ProductsController(private val service: ProductsService) {

    @Operation(summary = "Products list")
    @PostMapping("/list")
    fun list(@RequestBody dto: ProductsListDto): Result.Page<ProductDto> =
        service.list(dto)

    @Operation(summary = "Product get")
    @GetMapping("/get")
    fun get(@RequestBody dto: ProductGetDto): Result.Object<ProductDto> =
        service.get(dto.id)

    @Operation(summary = "Product create")
    @PostMapping("/create")
    fun create(@RequestBody dto: ProductCreateDto): Result.Object<UUID> =
        service.add(dto)
}