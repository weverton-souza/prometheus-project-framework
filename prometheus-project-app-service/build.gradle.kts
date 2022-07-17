import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.springdoc.openapi-gradle-plugin") version "1.3.4"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "com.prometheus.project"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17


repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2021.0.3"

dependencies {
	implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.7.0")
	implementation ("org.springframework.boot:spring-boot-starter-web:2.7.0")
	implementation ("org.springframework.boot:spring-boot-starter-log4j2")
	implementation ("org.apache.logging.log4j:log4j-slf4j-impl")

	implementation ("org.springframework.cloud:spring-cloud-starter")
	implementation ("org.springframework.cloud:spring-cloud-starter-config")
	implementation ("org.springframework.cloud:spring-cloud-starter-bootstrap")

	implementation ("io.jsonwebtoken:jjwt:0.9.1")
	implementation ("com.google.code.gson:gson:2.9.0")
//	implementation ("org.springframework.boot:spring-boot-starter-security")
	implementation ("org.springframework.security:spring-security-web:5.7.1")
	implementation ("org.springframework.security:spring-security-core:5.7.1")
	implementation ("org.springframework.security:spring-security-config:5.7.1")

	implementation ("org.springdoc:springdoc-openapi-data-rest:1.6.9")
	implementation ("org.springdoc:springdoc-openapi-ui:1.6.9")
	implementation ("org.springdoc:springdoc-openapi-kotlin:1.6.9")
	implementation ("io.springfox:springfox-swagger2:3.0.0")
	implementation ("io.springfox:springfox-swagger-ui:3.0.0")
	implementation ("org.apache.commons:commons-lang3:3.12.0")
	implementation ("javax.validation:validation-api:2.0.1.Final")

	// Database implementations
	runtimeOnly ("com.h2database:h2")
	runtimeOnly ("org.postgresql:postgresql")
	implementation ("org.flywaydb:flyway-core:8.5.13")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")

	implementation (project (":prometheus-project-core-service"))

	testImplementation ("org.springframework.security:spring-security-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

configurations.all {
	exclude(mapOf("module" to "spring-boot-starter-logging"))
}

tasks.bootJar {
	enabled = true
}

tasks.getByName<Jar>("jar") {
	enabled = true
}
