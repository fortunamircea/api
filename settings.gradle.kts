pluginManagement {

    plugins {
        kotlin("jvm") version "1.5.20"
        kotlin("plugin.spring") version "1.5.20"
        kotlin("kapt") version "1.5.20"
        id("org.springframework.boot") version "2.5.2"
    }
}

rootProject.name = "api"

include("app")
include("db")


