plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("application")
    kotlin("jvm")
    kotlin("plugin.spring")
}


group = "api.app"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11


dependencies {
    implementation(project(":db"))

    implementation("com.google.guava:guava:31.0.1-jre")
}