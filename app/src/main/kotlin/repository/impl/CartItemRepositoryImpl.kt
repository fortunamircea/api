package repository.impl

import data.CartItemCreateDto
import data.CartItemDto
import data.CartItemListDto
import data.CartItemUpdateDto
import org.jooq.DSLContext
import org.jooq.impl.DSL.asterisk
import org.springframework.stereotype.Repository
import repository.CartItemRepository
import java.util.*
import persistence.db.tables.CartItem.CART_ITEM as item

@Repository
class CartItemRepositoryImpl(private val dsl: DSLContext) : CartItemRepository {
    override fun add(dto: CartItemCreateDto): UUID {
        return dsl.insertInto(item)
            .set(item.CART_ID, dto.cart_id)
            .set(item.PRODUCT_ID, dto.product_id)
            .set(item.QUANTITY, dto.quantity).returning().fetchOne()!![item.ID]
    }

    override fun exists(cartItemId: UUID): Boolean {
        return dsl.fetchExists(dsl.select(item.ID).from(item).where(item.ID.eq(cartItemId)))
    }

    override fun update(dto: CartItemUpdateDto) {
        dsl.update(item).set(item.QUANTITY, dto.quantity).where(item.ID.eq(dto.id))
            .and(item.CART_ID.eq(dto.cart_id)).execute()
    }

    override fun delete(id: UUID) {
        dsl.delete(item).where(item.ID.eq(id)).execute()
    }


    override fun list(dto: CartItemListDto): Pair<List<CartItemDto>, Long> {
        return dsl.select(asterisk()).from(item).where(item.CART_ID.eq(dto.cart_id))
            .orderBy(item.CREATED_AT.desc())
            .limit(dto.limit).offset(dto.offset).fetch {
                CartItemDto(
                    id = it[item.ID],
                    cart_id = it[item.CART_ID],
                    product_id = it[item.PRODUCT_ID],
                    quantity = it[item.QUANTITY],
                    created_at = it[item.CREATED_AT],
                    updated_at = it[item.UPDATED_AT]
                )
            } to dsl.fetchCount(dsl.select(item.ID).from(item).where(item.CART_ID.eq(dto.cart_id))).toLong()
    }

}