package com.prometheus.project.app.service.exceptions

class ResourceNotFoundException(

    private var timestamp: Long? = null,
    private var developerMessage: String? = null

) : RuntimeException()
