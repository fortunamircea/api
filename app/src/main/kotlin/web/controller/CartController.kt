package web.controller

import data.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import service.CartItemService
import web.Result
import java.util.*

@Tag(name = "Cart")
@RestController
@RequestMapping("/cart")
class CartController(private val service: CartItemService) {

    @Operation(summary = "Cart list")
    @PostMapping("/list")
    fun list(@RequestBody dto: CartItemListDto): Result.Page<CartItemDto> =
        service.list(dto)

    @Operation(summary = "Cart delete")
    @DeleteMapping("/delete")
    fun delete (@RequestBody dto: CartItemDeleteDto): Result.Ok =
        service.delete(dto.id)

    @Operation(summary = "Cart add item")
    @PostMapping("/add")
    fun create(@RequestBody dto: CartItemCreateDto): Result.Object<UUID> =
        service.add(dto)

    @Operation(summary = "Cart update item")
    @PutMapping("/update")
    fun update(@RequestBody dto: CartItemUpdateDto): Result.Ok =
        service.update(dto)
}