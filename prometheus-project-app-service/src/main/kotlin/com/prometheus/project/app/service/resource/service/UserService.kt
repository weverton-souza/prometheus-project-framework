package com.prometheus.project.app.service.resource.service

import com.prometheus.project.app.service.domain.UserAuth
import com.prometheus.project.app.service.repository.UserAuthRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserServiceImpl(val userAuthRepository: UserAuthRepository, val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    fun createUser(userAuth: UserAuth): UserAuth {
        val encodedPassword: String = bCryptPasswordEncoder.encode(userAuth.password)
        userAuth.password = encodedPassword

        return userAuthRepository.save(userAuth)
    }

}
