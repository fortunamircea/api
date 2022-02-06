package repository.impl

import data.CustomerCreateDto
import data.CustomerDto
import data.CustomersListDto
import exceptions.APINotFoundException
import org.jooq.Condition
import org.jooq.DSLContext
import org.jooq.impl.DSL.asterisk
import org.springframework.stereotype.Repository
import repository.CustomersRepository
import java.util.*
import persistence.db.Tables.CUSTOMERS as customers

@Repository
class CustomersRepositoryImpl(
    private val dsl: DSLContext
) : CustomersRepository {
    override fun add(dto: CustomerCreateDto): UUID =
        dsl.insertInto(customers).set(customers.FIRST_NAME, dto.first_name)
            .set(customers.LAST_NAME, dto.last_name).set(customers.EMAIL, dto.email).returning(customers.ID)
            .fetchOne()!![customers.ID]


    override fun list(dto: CustomersListDto): Pair<List<CustomerDto>, Long> {
        val condition = mutableListOf<Condition>().apply {
            dto.customers_id?.let { if (it.isNotEmpty()) add(customers.ID.`in`(it)) }
        }
        return dsl.select(asterisk()).from(customers)
            .where(condition)
            .orderBy(customers.CREATED_AT.desc())
            .limit(dto.limit)
            .offset(dto.offset).fetch {
                CustomerDto(
                    id = it[customers.ID],
                    first_name = it[customers.FIRST_NAME],
                    last_name = it[customers.LAST_NAME],
                    email = it[customers.EMAIL],
                    cart_id = it[customers.CART_ID],
                    created_At = it[customers.CREATED_AT],
                    updated_At = it[customers.UPDATED_AT]
                )
            } to
                dsl.fetchCount(dsl.select(customers.ID).from(customers).where(condition)).toLong()
    }

    override fun get(id: UUID): CustomerDto =
        dsl.select(asterisk()).from(customers).where(customers.ID.eq(id)).fetchOne {
            CustomerDto(
                id = it[customers.ID],
                first_name = it[customers.FIRST_NAME],
                last_name = it[customers.LAST_NAME],
                email = it[customers.EMAIL],
                cart_id = it[customers.CART_ID],
                created_At = it[customers.CREATED_AT],
                updated_At = it[customers.UPDATED_AT]
            )
        } ?: throw APINotFoundException("No category found for provided id")

    override fun exists(id: UUID): Boolean =
        dsl.fetchExists(dsl.select(customers.ID).from(customers).where(customers.ID.eq(id)))
}