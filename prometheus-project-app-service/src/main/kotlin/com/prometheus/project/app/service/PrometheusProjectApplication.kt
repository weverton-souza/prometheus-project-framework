package com.prometheus.project.app.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PrometheusProjectApplication

fun main(args: Array<String>) {
	runApplication<PrometheusProjectApplication>(*args)
}
