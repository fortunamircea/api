package repository.impl

import data.CreateProductDto
import data.ListProductsDto
import data.ProductDto
import org.jooq.Condition
import org.jooq.DSLContext
import org.jooq.impl.DSL.asterisk
import org.springframework.stereotype.Repository
import repository.ProductsRepository
import java.util.*
import persistence.db.Tables.PRODUCTS as products

@Repository
class ProductsRepositoryImpl(private val dsl: DSLContext) : ProductsRepository {
    override fun add(dto: CreateProductDto): UUID {
        return dsl.insertInto(products)
            .set(products.TYPE, dto.type)
            .set(products.CATEGORY_ID, dto.category_id)
            .set(products.PRICE, dto.price).set(products.UNITS, dto.units).returning(products.ID)
            .fetchOne()!![products.ID]
    }

    override fun list(dto: ListProductsDto): Pair<List<ProductDto>, Long> {
        val conditions = mutableListOf<Condition>().apply {
            dto.productsIds?.let { if (it.isNotEmpty()) add(products.ID.`in`(it)) }
        }
        return dsl.select(asterisk())
            .from(products)
            .where(conditions)
            .orderBy(products.CREATED_AT.desc())
            .limit(dto.limit)
            .offset(dto.offset).fetch {
                ProductDto(
                    id = it[products.ID],
                    type = it[products.TYPE],
                    updated_At = it[products.UPDATED_AT],
                    created_At = it[products.CREATED_AT],
                    category_id = it[products.CATEGORY_ID],
                    units = it[products.UNITS],
                    price = it[products.PRICE]
                )
            } to
                dsl.fetchCount(dsl.select(products.ID).from(products).where(conditions)).toLong()
    }

    override fun get(id: UUID): ProductDto? =
        dsl.select(asterisk()).from(products).where(products.ID.eq(id)).fetchOne {
            ProductDto(
                id = it[products.ID],
                type = it[products.TYPE],
                updated_At = it[products.UPDATED_AT],
                created_At = it[products.CREATED_AT],
                category_id = it[products.CATEGORY_ID],
                units = it[products.UNITS],
                price = it[products.PRICE]
            )
        }


}


