package configuration

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.PropertySource
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@SpringBootConfiguration
@EnableAutoConfiguration(exclude = [ErrorMvcAutoConfiguration::class, SolrAutoConfiguration::class])
@ComponentScan(basePackages = ["web", "service", "repository"])
@Import(
    SecurityConfiguration::class,
    WebMvcConfiguration::class,
    PersistenceConfiguration::class,
    SwaggerConfiguration::class
)
@PropertySource("classpath:application.properties")
@ConfigurationPropertiesScan(basePackages = ["properties"])
class Configuration {

    @Bean
    fun moshi(): Moshi =
        Moshi.Builder()
            .add(BigDecimalAdapter)
            .add(UuidAdapter)
            .add(KotlinJsonAdapterFactory())
            .add(LocalDateAdapter)
            .add(LocalDateTimeAdapter)
            .add(LocalTimeAdapter)
            .build()


    object BigDecimalAdapter {
        @FromJson
        fun fromJson(string: String) = BigDecimal(string)

        @ToJson
        fun toJson(value: BigDecimal) = value.toString()
    }

    object UuidAdapter {
        @FromJson
        fun fromJson(string: String) = UUID.fromString(string) as UUID

        @ToJson
        fun toJson(value: UUID) = value.toString()
    }

    object LocalDateAdapter {
        @FromJson
        fun fromJson(string: String): LocalDate = LocalDate.parse(string)

        @ToJson
        fun toJson(value: LocalDate): String = value.toString()
    }

    object LocalDateTimeAdapter {
        @FromJson
        fun fromJson(string: String): LocalDateTime = LocalDateTime.parse(string)

        @ToJson
        fun toJson(value: LocalDateTime): String = value.toString()

    }

    object LocalTimeAdapter {
        @FromJson
        fun fromJson(string: String): LocalTime = LocalTime.parse(string)

        @ToJson
        fun toJson(value: LocalTime) = value.toString()
    }
}