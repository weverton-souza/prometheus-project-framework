import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.0"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
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
	implementation ("org.springframework.boot:spring-boot-starter-log4j2")
	implementation ("org.apache.logging.log4j:log4j-slf4j-impl")

	implementation ("org.springframework.cloud:spring-cloud-starter")
	implementation ("org.springframework.cloud:spring-cloud-starter-config")
	implementation ("org.springframework.cloud:spring-cloud-starter-bootstrap")

	implementation(project(":prometheus-project-core-service"))

	testImplementation("org.springframework.boot:spring-boot-starter-test")
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
