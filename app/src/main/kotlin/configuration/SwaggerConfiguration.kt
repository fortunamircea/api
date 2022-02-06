package configuration

import data.constants.HeaderProperty
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfiguration {

    @Bean
    fun publicApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("API")
            .pathsToMatch("/**")
            .build()
    }

    @Bean
    fun springOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components()
                    .addSecuritySchemes(
                        "bearerAuth",
                        SecurityScheme()
                            .type(SecurityScheme.Type.APIKEY)
                            .scheme("bearer")
                            .`in`(SecurityScheme.In.HEADER)
                            .name(HeaderProperty.APP_ACCESS_TOKEN)
                    )
            )
    }
}