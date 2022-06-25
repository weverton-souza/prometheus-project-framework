package com.prometheus.project.app.service.interfaces

import com.prometheus.project.app.service.abstracts.AbstractTO

interface Domain {
    fun toData(): AbstractTO
}
