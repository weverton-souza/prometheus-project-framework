package com.prometheus.project.app.service.exceptions

import com.prometheus.project.app.service.exceptions.ExceptionDetails
import com.prometheus.project.app.service.exceptions.FieldExceptionDetails

class ValidationExceptionDetails(
    private var fieldExceptionDetails: List<FieldExceptionDetails>,
    private var timestamp: Long,
    private var developerMessage: String
) : ExceptionDetails()
