plugins {
    java
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.scrip"
version = "0.0.1-SNAPSHOT"
description = "api-gateway"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

// Tren de versiones compatible con Boot 3.3.x
extra["springCloudVersion"] = "2023.0.3"

dependencies {
    dependencies {
        implementation("org.springframework.cloud:spring-cloud-starter-gateway")
        implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

        implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

        // AÑADE ESTA LÍNEA DE LOGÍSTICA DE RED:
        implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}