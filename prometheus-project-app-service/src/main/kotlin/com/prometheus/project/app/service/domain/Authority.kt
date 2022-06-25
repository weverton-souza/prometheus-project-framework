package com.prometheus.project.app.service.domain

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Authority (

    @Id var id: String = UUID.randomUUID().toString(),

    val role: String
)
