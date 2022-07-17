package com.prometheus.project.app.service.configuration

import java.util.UUID

class UUIDConverter {

    companion object {
        fun toPlainString(uuid: UUID): String = "_${uuid.toString().replace("-", "")}"
    }
}
