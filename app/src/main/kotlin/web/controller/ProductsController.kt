package web.controller

import data.CreateProductDto
import data.GetProductDto
import data.ListProductsDto
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

    @Operation(summary = "Product list")
    @PostMapping("/list")
    fun list(@RequestBody dto: ListProductsDto): Result.Page<ProductDto> =
        service.list(dto)

    @Operation(summary = "Product get")
    @GetMapping("/get")
    fun get(@RequestBody dto: GetProductDto): Result.Object<ProductDto> =
        service.get(dto)

    @Operation(summary = "Product create")
    @PostMapping("/create")
    fun create(@RequestBody dto: CreateProductDto): Result.Object<UUID> =
        service.add(dto)
}