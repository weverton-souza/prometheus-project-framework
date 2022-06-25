package com.prometheus.project.app.service.enumeration

enum class EnvironmentType(private var environment: String) {

    STAGING("STG"),
    DEVELOPMENT("DEV"),
    PRODUCTION("PRD");

    open fun getEnvironment(): String = environment

}
