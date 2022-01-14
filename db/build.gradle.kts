val postgresqlVersion: String by project

plugins {
    id("nu.studer.jooq") version "6.0.1"
}

dependencies {
    jooqGenerator("org.postgresql:postgresql:${postgresqlVersion}")
}

jooq {
    version.set(dependencyManagement.importedProperties["jooq.version"])

    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(false)

            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://127.0.0.1:5432/api"
                    user = "api"
                    password = "12345678"
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "public"
                        includes = ".*"
                        excludes = "databasechangelog.*"

                    }
                    generate.apply {
                        isDeprecated = false
                        isJavaTimeTypes = true
                    }
                    target.apply {
                        packageName = "persistence.db"
                        directory = "src/main/java"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}