package com.prometheus.project.app.service.exceptions

class FieldExceptionDetails(
    private var field: String,
    private var fieldMessages: MutableList<String?>
)
