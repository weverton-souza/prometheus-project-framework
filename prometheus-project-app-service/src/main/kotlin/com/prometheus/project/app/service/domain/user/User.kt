package com.prometheus.project.app.service.domain.user

import com.prometheus.project.app.service.abstracts.AbstractDomain
import com.prometheus.project.app.service.abstracts.AbstractTO
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(
    @Id
    override var id: String = UUID.randomUUID().toString(),

    val firstName: String? = null,

    val lastName: String? = null,

    val nickname: String? = null,

    val fullName: String? = null,

    val birthday: LocalDateTime? = null,

    val gender: String? = null

): AbstractDomain(id = id) {

    override fun toData(): AbstractTO = UserTO(
        id, firstName, lastName, nickname, fullName, birthday, gender
    )
}

data class UserTO(
    var id: String = UUID.randomUUID().toString(),

    val firstName: String? = null,

    val lastName: String? = null,

    val nickname: String? = null,

    val fullName: String? = null,

    val birthday: LocalDateTime? = null,

    val gender: String? = null

): AbstractTO() {
    override fun toDomain(): AbstractDomain = User(
        id, firstName, lastName, nickname, fullName, birthday, gender
    )
}
