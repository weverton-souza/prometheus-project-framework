package com.prometheus.project.app.service.service

import com.prometheus.project.app.service.domain.UserAuth
import com.prometheus.project.app.service.repository.UserAuthRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserAuthServiceImpl(val userAuthRepository: UserAuthRepository): UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserAuth {
        return userAuthRepository.findByUsername(username)
            .orElseThrow { UsernameNotFoundException("User with the specified username wasn't found") }
    }

}
