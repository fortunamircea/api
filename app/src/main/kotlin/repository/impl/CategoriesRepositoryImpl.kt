package repository.impl

import data.CategoryDto
import data.CategoryCreateDto
import data.CategoryListDto
import exceptions.APINotFoundException
import org.jooq.Condition
import org.jooq.DSLContext
import org.jooq.impl.DSL.asterisk
import org.springframework.stereotype.Repository
import repository.CategoriesRepository
import java.util.*
import persistence.db.Tables.CATEGORIES as categories

@Repository
class CategoriesRepositoryImpl(
    private val dsl: DSLContext
) : CategoriesRepository {
    override fun add(dto: CategoryCreateDto): UUID =
        dsl.insertInto(categories).set(categories.TYPE, dto.type).returning(categories.ID).fetchOne()!![categories.ID]


    override fun list(dto: CategoryListDto): Pair<List<CategoryDto>, Long> {
        val condition = mutableListOf<Condition>().apply {
            dto.categories_id?.let { if (it.isNotEmpty()) add(categories.ID.`in`(it)) }
        }
        return dsl.select(asterisk()).from(categories)
            .where(condition)
            .orderBy(categories.CREATED_AT.desc())
            .limit(dto.limit)
            .offset(dto.offset).fetch {
                CategoryDto(
                    id = it[categories.ID],
                    type = it[categories.TYPE],
                    created_At = it[categories.CREATED_AT],
                    updated_At = it[categories.UPDATED_AT]
                )
            } to
                dsl.fetchCount(dsl.select(categories.ID).from(categories).where(condition)).toLong()
    }

    override fun get(id: UUID): CategoryDto =
        dsl.select(asterisk()).from(categories).where(categories.ID.eq(id)).fetchOne {
            CategoryDto(
                id = it[categories.ID],
                type = it[categories.TYPE],
                created_At = it[categories.CREATED_AT],
                updated_At = it[categories.UPDATED_AT]
            )
        } ?: throw APINotFoundException("No category found for provided id")

    override fun exists(id: UUID): Boolean =
        dsl.fetchExists(dsl.select(categories.ID).from(categories).where(categories.ID.eq(id)))
}