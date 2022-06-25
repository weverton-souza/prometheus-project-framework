package com.prometheus.project.app.service.reponse

import com.prometheus.project.app.service.domain.AuthTO
import org.springframework.http.HttpStatus

data class ResponseTO(
    val content: Any?,
    val status: String = HttpStatus.OK.name,
    val message: String = HttpStatus.OK.reasonPhrase,
    val wasSuccessfully: Boolean = true
)

data class ResponseAuthTO(
    val content: AuthTO?,
    val mainRoute: String,
    val status: String?,
    val message: String?,
    val wasSuccessfully: Boolean = true
)

data class ResponseError(
        val content: Any? = null,
        val status: Int,
        val message: String?,
        val developerMessage: String? = null,
        val wasSuccessfully: Boolean = false
)
