package com.prometheus.project.app.service.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.prometheus.project.app.service.domain.UserAuth
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


class JwtUsernameAndPasswordAuthenticationFilter: UsernamePasswordAuthenticationFilter() {

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {
        return try {
            val userLogin: UserAuth = ObjectMapper()
                    .readValue(request.inputStream, UserAuth::class.java)
            val authToken = UsernamePasswordAuthenticationToken(userLogin.username, userLogin.password)
            this.authenticationManager.authenticate(authToken)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

}