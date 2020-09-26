import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("com.google.cloud.tools.jib") version "2.5.0"
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.spring") version "1.3.72"
}

group = "com"
version = "0.0.1"

val main_class by extra("com.springcassandra.SpringcassandraApplicationKt")

repositories {
    mavenCentral()
}

dependencies {

    //Spring
    implementation("org.springframework.boot:spring-boot-starter-data-cassandra")
    implementation("org.springframework.boot:spring-boot-starter-web")

    //Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    //Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //Logging
    implementation("io.github.microutils:kotlin-logging:1.7.8")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

jib {
    container {
        ports = listOf("8080")
        mainClass = main_class

        // good defauls intended for Java 8 (>= 8u191) containers
        jvmFlags = listOf(
            "-Dspring.profiles.active=docker"
        )
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
