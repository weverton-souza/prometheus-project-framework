package com.prometheus.project.app.service.reponse

import com.prometheus.project.app.service.reponse.PageableTO
import org.springframework.data.domain.Sort

data class PageInfoTO(
    val pageable: PageableTO?,
    val sort: Sort?,
    val totalElements: Long?,
    val totalPages: Int?,
    val last: Boolean?,
    val first: Boolean?,
    val size: Int?,
    val number: Int?,
    val numberOfElements: Int?,
    val empty: Boolean?
)
