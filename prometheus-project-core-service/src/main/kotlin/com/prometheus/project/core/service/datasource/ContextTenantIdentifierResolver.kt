package com.prometheus.project.core.service.datasource

import com.google.gson.GsonBuilder
import com.prometheus.project.core.service.util.Context
import com.prometheus.project.core.service.util.ContextHolder
import com.prometheus.project.core.service.util.UUIDConverter
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import java.util.Objects.nonNull
import java.util.UUID
import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestAttributes
import org.springframework.web.context.request.RequestContextHolder

@Component
class ContextTenantIdentifierResolver: CurrentTenantIdentifierResolver {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val gson = GsonBuilder().setPrettyPrinting().create()

    override fun resolveCurrentTenantIdentifier(): String {
        val context: Context? = ContextHolder().context

        logger.info("""${object {}.javaClass.enclosingMethod.name}(INIT):: 
            |Context: ${gson.toJson(context)}""".trimMargin())

        if (context != null && nonNull(context.getTenantId()) && nonNull(context.getUserId())) {

            val requestAttributes: RequestAttributes? = RequestContextHolder.getRequestAttributes()
            if (nonNull(requestAttributes)) {
                val tenantId =
                    requestAttributes?.getAttribute(MultiTenant.TENANT_KEY, RequestAttributes.SCOPE_REQUEST)!! as UUID

                logger.info("""${object {}.javaClass.enclosingMethod.name}(End):: A valid Tenant was provided Tenant: $tenantId!""")

                return toSchemaString(tenantId)
            }
        }

        logger.info("""${object {}.javaClass.enclosingMethod.name}(End):: No valid Tenant was provided. In this case Default Tenant will be used!""")

        return  MultiTenant.DEFAULT_TENANT
    }

    private fun toSchemaString(tenantId: UUID): String = "_" + UUIDConverter().toPlainString(tenantId)

    override fun validateExistingCurrentSessions(): Boolean = true

}
