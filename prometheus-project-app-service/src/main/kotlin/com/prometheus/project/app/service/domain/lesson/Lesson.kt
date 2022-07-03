package com.prometheus.project.app.service.domain.lesson

import com.prometheus.project.app.service.domain.exam.Exam
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
class Lesson(

    @Id var id: String = UUID.randomUUID().toString(),

    var title: String,

    var mainDescription: String,

    var quote: String,

    var secondDescription: String,

    @ManyToMany(fetch = FetchType.LAZY)
    var exam: List<Exam>

)
