import com.github.gradle.node.NodeExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.gradle.node.yarn.task.YarnTask


plugins {
    id("org.springframework.boot") version "2.6.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"
    id("jacoco")
    id("org.sonarqube") version "3.3"
    id("com.github.node-gradle.node") version "3.2.1"
}

group = "de.chasenet"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

//Error with spring and kotest. See: https://github.com/kotest/kotest/issues/2782
extra["kotlin-coroutines.version"] = "1.6.0"

repositories {
    mavenCentral()
}

dependencies {
    val ktorVersion = "1.6.7"
    val kotestVersion = "5.1.0"

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.session:spring-session-core")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
    runtimeOnly("org.postgresql:postgresql")

    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")

    // Kotest
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.0")

    //Ktor Client
    testImplementation("io.ktor:ktor-client-core:$ktorVersion")
    testImplementation("io.ktor:ktor-client-serialization:$ktorVersion")
    testImplementation("io.ktor:ktor-client-cio:$ktorVersion")
    testImplementation("io.ktor:ktor-client-jackson:$ktorVersion")
    testImplementation("io.ktor:ktor-client-logging:$ktorVersion")

    testImplementation("org.testcontainers:postgresql:1.16.3")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

sonarqube {
    properties {
        property("sonar.projectKey", "Chase22_knst-registration")
        property("sonar.organization", "chase22")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

node {
    version.set("16.14.0")
    npmVersion.set("8.3.1")
    distBaseUrl.set("https://nodejs.org/dist")
    download.set(true)
    nodeProjectDir.set(file("$rootDir/src/webapp/knst-frontend"))
    workDir.set(file("${project.buildDir}/nodejs"))
}

val yarnBuild by tasks.registering(YarnTask::class) {
    args.set(listOf("build"))
    workingDir.set(project.extensions.getByType(NodeExtension::class).nodeProjectDir)
    inputs.files(
        fileTree(mapOf(
            "dir" to "$rootDir/src/webapp/",
            "include" to "*",
            "exclude" to listOf("$rootDir/src/webapp/*/build/", "$rootDir/src/webapp/*/node_modules/")
        )).files
    )
    outputs.dirs(
        listOf("$buildDir/dist/webapp")
    )
}