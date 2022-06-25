package com.prometheus.project.core.service.util

import java.util.UUID

class Context(private val tenantId: UUID?, private val userId: UUID?) {
    fun getTenantId(): UUID? = tenantId

    fun getUserId(): UUID? = userId
}
