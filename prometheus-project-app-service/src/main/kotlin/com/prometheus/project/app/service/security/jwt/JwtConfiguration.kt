package com.prometheus.project.app.service.security.jwt

import com.prometheus.project.app.service.domain.UserAuth
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Date
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtConfiguration {

    @Value("\${prometheus.security.jwt.secret-key}")
    private lateinit var secretKey: String

    private val logger = LoggerFactory.getLogger(javaClass)


    fun generateToken(userAuth: UserAuth): String {
        return Jwts.builder()
            .setSubject(userAuth.username)
            .claim("X-User", userAuth.id)
            .claim("X-Company-Tenant", userAuth.tenantId)
            .setExpiration(Date(System.currentTimeMillis() + 36000000))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun isValidToken(token: String): Boolean {
        val claims = getClaims(token)

        if (!claims.isNullOrEmpty()) {
            val username = claims.subject
            val expirationDate: Date? = claims.expiration
            val now = Date(System.currentTimeMillis())
            return username != null && expirationDate != null && now.before(expirationDate)
        }

        return false
    }

    private fun getClaims(token: String): Claims? {
        return try {
            Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).body

        } catch (e: Exception) {
            logger.info("""${object {}.javaClass.enclosingMethod.name}(INIT):: Context: ${e.message}""".trimMargin())

            return null
        }
    }

    fun getUserName(token: String): String? {
        val claims = getClaims(token)

        return claims?.subject
    }
}