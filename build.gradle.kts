import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springfoxVersion: String by project
val postgresqlVersion: String by project
val hikariVersion: String by project
val liquibaseVersion: String by project
val retrofitVersion: String by project
val moshiVersion: String by project

plugins {
    id("org.springframework.boot") version "2.6.2" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    id("application")
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("kapt") version "1.6.10"

}

repositories {
    mavenCentral()
}

allprojects {
    apply {
        plugin("kotlin")
        plugin("kotlin-spring")
        plugin("kotlin-kapt")
        plugin("io.spring.dependency-management")
    }

    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }

    sourceSets {
        main {
            java.setSrcDirs(listOf("src/main/kotlin", "src/main/java"))
            resources.setSrcDirs(listOf("src/main/resources"))
        }
        test {
            java.setSrcDirs(listOf("src/test/kotlin"))
            resources.setSrcDirs(listOf("src/test/resources"))
        }
    }

    dependencies {

        //kotlin
        implementation(kotlin("reflect"))

        // persistence
        implementation("org.postgresql:postgresql:$postgresqlVersion")
        implementation("com.zaxxer:HikariCP:$hikariVersion")
        implementation("org.liquibase:liquibase-core:$liquibaseVersion")

        // json
        implementation("com.fasterxml.jackson.core:jackson-databind")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")

        // spring
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-security")
        implementation("org.springframework.boot:spring-boot-starter-jooq")
        implementation("org.springframework.boot:spring-boot-starter-jdbc")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-validation")

        // api docs
        implementation("org.springdoc:springdoc-openapi-ui:1.6.3")

        // moshi
        implementation("com.squareup.moshi:moshi:$moshiVersion")
        implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion")
        kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")

        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.security:spring-security-test")
        testImplementation("org.dbunit:dbunit:2.7.2")
        testImplementation("com.github.springtestdbunit:spring-test-dbunit:1.3.0")
        testImplementation("com.github.database-rider:rider-spring:1.32.0")
        testImplementation("org.skyscreamer:jsonassert:1.5.0")
        testImplementation("org.reflections:reflections:0.10.2")
        testImplementation("junit:junit:4.13.2")
    }
}