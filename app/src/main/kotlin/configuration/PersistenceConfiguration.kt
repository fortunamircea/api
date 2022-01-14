package configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import liquibase.integration.spring.SpringLiquibase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration
class PersistenceConfiguration(
    @Value("\${jdbc.database.url}") private val url: String,
    @Value("\${jdbc.database.driverClassName}") private val jdbcDriverClassName: String,
    @Value("\${jdbc.database.username}") private val jdbcUsername: String,
    @Value("\${jdbc.database.password}") private val jdbcPassword: String
) {

    @Bean
    fun dataSource(): DataSource = HikariDataSource(HikariConfig().apply {
        jdbcUrl = url
        driverClassName = jdbcDriverClassName
        username = jdbcUsername
        password = jdbcPassword
    })

    @Bean
    fun namedParameterJdbcTemplate(): NamedParameterJdbcTemplate = NamedParameterJdbcTemplate(dataSource())

    @Bean
    fun liquibase(source: DataSource): SpringLiquibase = SpringLiquibase().apply {
        changeLog = "classpath:/migrations/changelog.xml"
        dataSource = source
    }
}