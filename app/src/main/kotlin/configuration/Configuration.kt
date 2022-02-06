package configuration

import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.PropertySource

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = ["web", "service", "repository"])
@Import(
    SecurityConfiguration::class,
    WebMvcConfiguration::class,
    PersistenceConfiguration::class,
    SwaggerConfiguration::class
)
@PropertySource("classpath:application.properties")
@ConfigurationPropertiesScan(basePackages = ["properties"])
class Configuration