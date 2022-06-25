package com.prometheus.project.app.service.resource

import com.google.gson.GsonBuilder
import com.prometheus.project.app.service.domain.AuthRequest
import com.prometheus.project.app.service.domain.AuthTO
import com.prometheus.project.app.service.domain.UserAuth
import com.prometheus.project.app.service.enumeration.USER_RESOURCE
import com.prometheus.project.app.service.enumeration.UserType
import com.prometheus.project.app.service.reponse.ResponseTO
import com.prometheus.project.app.service.request.UserAuthRequest
import com.prometheus.project.app.service.security.jwt.JwtConfiguration
import com.prometheus.project.app.service.service.UserAuthServiceImpl
import com.prometheus.project.app.service.service.UserServiceImpl
import io.swagger.v3.oas.annotations.Operation
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

@CrossOrigin
@RestController
@RequestMapping("/user-auth")
class UserAuthResourceImpl(private val userAuthService: UserAuthServiceImpl,
                           private val userService: UserServiceImpl,
                           private val authenticationManager: AuthenticationManager,
                           private val jwtConfiguration: JwtConfiguration) {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val gson = GsonBuilder().setPrettyPrinting().create()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(method = "POST", tags = [USER_RESOURCE], description = ":: CREATE A NEW USER AUTH ::")
    fun register(@RequestBody userAuthRequest: UserAuthRequest): ResponseTO {
        logger.info("""${object {}.javaClass.enclosingMethod.name}(INIT):: User Auth: 
              |${gson.toJson(userAuthRequest)}""".trimMargin())

        val createdUserAuth: UserAuth = userService.createUser(userAuthRequest.toDomain())

        return ResponseTO(createdUserAuth, HttpStatus.CREATED.name, HttpStatus.CREATED.reasonPhrase)
    }

    @PostMapping("/sign-in")
    @Operation(method = "GET", tags = [USER_RESOURCE], description = ":: FIND ALL USER AUTH ::")
    fun signIn(@RequestBody authRequest: AuthRequest): ResponseTO {
        val authentication: Authentication = authenticationManager.authenticate (
            UsernamePasswordAuthenticationToken( authRequest.username, authRequest.password )
        )
        SecurityContextHolder.getContext().authentication = authentication

        val userAuth = userAuthService.loadUserByUsername(authRequest.username)

        val token: String = this.jwtConfiguration.generateToken(userAuth)

        val authUser = AuthTO(userAuth.id, userAuth.nickname, UserType.ADMIN, userAuth.username, token)

        logger.info("""${object {}.javaClass.enclosingMethod.name}(INIT):: User Auth - Sign IN: 
              |${gson.toJson(userAuth)}""".trimMargin())

        return ResponseTO(authUser, HttpStatus.OK.name, HttpStatus.OK.reasonPhrase)
    }

}
