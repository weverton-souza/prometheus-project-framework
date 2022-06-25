package com.prometheus.project.app.service.repository

import com.prometheus.project.app.service.domain.UserAuth
import java.util.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserAuthRepository : JpaRepository<UserAuth, String> {
    fun findByUsername(username: String): Optional<UserAuth>

}
