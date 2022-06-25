package com.prometheus.project.app.service.domain

import com.prometheus.project.app.service.enumeration.UserType
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@ApiModel(value = "Auth Request", description = "All details about auth request.")
data class AuthRequest(

        @field:[NotNull NotBlank]
        @ApiModelProperty(value = "User Name", notes = "The user email", required = true)
        val username: String,

        @field:[NotNull NotBlank]
        @ApiModelProperty(value = "User password", notes = "The user password", required = true)
        val password: String

)

@ApiModel(value = "Auth Response", description = "All details about auth response.")
data class AuthTO(

        @field:[NotNull NotBlank]
        @ApiModelProperty(value = "Nickname", notes = "The user nickname")
        val id: String,

        @field:[NotNull NotBlank]
        @ApiModelProperty(value = "Nickname", notes = "The user nickname")
        var nickname: String?,

        @ApiModelProperty(value = "User type", notes = "The user type")
        var userType: UserType?,

        @field:[NotNull NotBlank]
        @ApiModelProperty(value = "Username", notes = "The user email")
        var username: String?,

        @field:[NotNull NotBlank]
        @ApiModelProperty(value = "Token", notes = "The user token")
        var AccessToken: String?
)
