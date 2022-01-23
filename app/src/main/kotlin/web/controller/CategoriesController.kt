package web.controller

import data.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import service.CategoriesService
import web.Result
import java.util.*

@Tag(name = "Categories")
@RestController
@RequestMapping("/categories")
class CategoriesController(private val service:CategoriesService) {

    @Operation(summary = "Category list")
    @PostMapping("/list")
    fun list(@RequestBody dto: ListCategoryDto): Result.Page<CategoryDto> =
        service.list(dto)

    @Operation(summary = "Category get")
    @GetMapping("/get")
    fun get(@RequestBody dto: GetCategoryDto): Result.Object<CategoryDto> =
        service.get(dto)

    @Operation(summary = "Category create")
    @PostMapping("/create")
    fun create(@RequestBody dto: CreateCategoryDto): Result.Object<UUID> =
        service.add(dto)
}