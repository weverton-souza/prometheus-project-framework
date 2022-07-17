package com.prometheus.project.app.service.domain

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Tenant(

    @Id var id: String = UUID.randomUUID().toString(),

    val key: String = UUID.randomUUID().toString(),

    val comment: String?

)
