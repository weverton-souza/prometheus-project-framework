package com.prometheus.project.app.service.abstracts

import com.prometheus.project.app.service.interfaces.Domain
import org.springframework.data.annotation.Id

abstract class AbstractDomain(
        @Id open val id: String,
) : Domain
