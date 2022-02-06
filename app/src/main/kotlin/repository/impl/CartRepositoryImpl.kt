package repository.impl

import exceptions.APINotFoundException
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import repository.CartRepository
import java.util.*
import persistence.db.Tables.CART as cart

@Repository
class CartRepositoryImpl(private val dsl: DSLContext) : CartRepository {
    override fun add(customerId: UUID): UUID {
        return dsl.insertInto(cart).set(cart.CUSTOMERS_ID, customerId).returning().fetchOne()!![cart.ID]
    }

    override fun get(customerId: UUID): UUID {
        return dsl.select(cart.ID).from(cart).where(cart.CUSTOMERS_ID.eq(customerId)).fetchOne()?.get(cart.ID)
            ?: throw APINotFoundException("No cart found for provide id")
    }
}