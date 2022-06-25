package com.prometheus.project.app.service.reponse

import org.springframework.data.domain.Sort

data class PageableTO(
        val sort: Sort?,
        val pageSize: Int?,
        val pageNumber: Int?,
        val offset: Long?,
        val isUnPaged: Boolean?,
        val isPaged: Boolean?
)
