package com.prometheus.project.app.service.request

import com.prometheus.project.app.service.domain.Authority
import com.prometheus.project.app.service.domain.UserAuth

class UserAuthRequest(

    private val  username: String,

    private val password: String,

    private val  nickname: String,

    private val  tenantId: String,

    private val isAccountNonExpired: Boolean = true,

    private val isAccountNonLocked: Boolean = true,

    private val isCredentialsNonExpired: Boolean = true,

    private val isEnabled: Boolean,

    private val authorities: MutableList<AuthorityRequest>

) {

    fun toDomain(): UserAuth {
        return UserAuth(
            username = username,
            password = password,
            nickname = nickname,
            tenantId = tenantId,
            isAccountNonExpired = isAccountNonExpired,
            isAccountNonLocked = isAccountNonLocked,
            isCredentialsNonExpired = isCredentialsNonExpired,
            isEnabled = isEnabled,
            authorities = authorities.map { a -> Authority(id = a.id, role = a.role) }.toMutableList()
        )
    }

}
