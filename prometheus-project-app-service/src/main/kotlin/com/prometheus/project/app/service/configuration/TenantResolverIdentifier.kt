package com.prometheus.project.app.service.configuration

object TenantResolverIdentifier {
    const val TENANT_KEY = "X-Company-Tenant"
    const val DEFAULT_TENANT = "public"
    const val USER_KEY = "X-User"
    const val AUTHORIZATION = "Authorization"
}
