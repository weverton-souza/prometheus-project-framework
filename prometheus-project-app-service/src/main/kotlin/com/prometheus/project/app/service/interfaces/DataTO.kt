package com.prometheus.project.app.service.interfaces

import com.prometheus.project.app.service.abstracts.AbstractDomain
import java.io.Serializable

interface DataTO : Serializable {
    fun toDomain(): AbstractDomain
}
