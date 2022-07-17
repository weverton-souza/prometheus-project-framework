package com.prometheus.project.core.service.datasource

import com.prometheus.project.core.service.util.Context
import com.prometheus.project.core.service.util.ContextHolder
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import java.util.UUID
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MultiTenantFilter: Filter {

    @Value("\${prometheus.security.jwt.secret-key}")
    private lateinit var secretKey: String

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = request as HttpServletRequest

        val accessToken = req.getHeader(TenantResolverIdentifier.AUTHORIZATION)
        var tenantUUID: UUID? = null
        var userUUID: UUID? = null

        if (!accessToken.isNullOrEmpty()) {
            val claims: Claims? = getClaims(accessToken)

            tenantUUID = UUID.fromString(claims?.get(TenantResolverIdentifier.TENANT_KEY) as String?)
            userUUID = UUID.fromString(claims?.get(TenantResolverIdentifier.USER_KEY) as String?)
        }

        req.setAttribute(TenantResolverIdentifier.TENANT_KEY, tenantUUID)
        req.setAttribute(TenantResolverIdentifier.USER_KEY, userUUID)


        ContextHolder().context = Context(tenantUUID, userUUID)

        chain.doFilter(request, response)
    }

    private fun getClaims(token: String): Claims? {
        return try {
            Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).body
        } catch (e: Exception) {
            null
        }
    }
}
