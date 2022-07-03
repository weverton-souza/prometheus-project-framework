package com.prometheus.project.app.service.domain.exam

import com.prometheus.project.app.service.domain.exam.enumeration.GraduationType
import com.prometheus.project.app.service.domain.exam.enumeration.KnowledgeAreasTypes
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Exam (

    @Id var id: String = UUID.randomUUID().toString(),

    var title: String,

    var examNumber: Int,

    var knowledgeAreasType: KnowledgeAreasTypes,

    var graduationType: GraduationType

)
