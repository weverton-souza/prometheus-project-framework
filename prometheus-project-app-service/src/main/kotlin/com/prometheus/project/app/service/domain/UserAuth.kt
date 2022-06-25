package com.prometheus.project.app.service.domain

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
class UserAuth(

    @Id var id: String = UUID.randomUUID().toString(),

    private var  username: String,

    private var password: String,

    var  nickname: String,

    var tenantId: String,

    private var isAccountNonExpired: Boolean = true,

    private var isAccountNonLocked: Boolean = true,

    private var isCredentialsNonExpired: Boolean = true,

    private var isEnabled: Boolean = true,

    @OneToMany(fetch = FetchType.LAZY)
    private var authorities: MutableList<Authority>

): UserDetails {

    override fun getAuthorities() = this.authorities.map { a -> SimpleGrantedAuthority(a.role) }.toMutableList()

    override fun getPassword(): String = this.password

    fun setPassword(password: String) {
        this.password = password
    }

    override fun getUsername():String = this.username

    override fun isAccountNonExpired() =  this.isAccountNonExpired

    override fun isAccountNonLocked() = this.isAccountNonLocked

    override fun isCredentialsNonExpired() = isCredentialsNonExpired

    override fun isEnabled() = this.isEnabled

}
