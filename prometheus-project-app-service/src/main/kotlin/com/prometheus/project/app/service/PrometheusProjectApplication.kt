package com.prometheus.project.app.service

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.ControllerAdvice

@SpringBootApplication
@ComponentScan(basePackages = ["com.prometheus.*"])
@ControllerAdvice(basePackages = ["com.prometheus.*"])
@OpenAPIDefinition(info = Info(
	title = "Prometheus Framework",
	version = "1.0",
	description = """The Prometheus API is a tool to help educational institutions. Providing them with assistance in financial, pedagogical, educational, human resources, students and library management.""")
)
@SecurityScheme(name = ":: Prometheus API ::", scheme = "basic", type = SecuritySchemeType.HTTP, `in` = SecuritySchemeIn.HEADER)
class PrometheusProjectApplication

fun main(args: Array<String>) {
	runApplication<PrometheusProjectApplication>(*args)
}
