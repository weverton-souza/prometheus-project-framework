package com.prometheus.project.app.service.reponse

import com.prometheus.project.app.service.abstracts.AbstractDomain
import com.prometheus.project.app.service.abstracts.AbstractTO
import org.springframework.data.domain.Page

class PageResponseTO(
    val content: List<AbstractTO>?,
    val code: String?,
    val message: String?,
    val isPage: Boolean? = true,
    val pageInfo: PageInfoTO?) {

    class Builder(
            private var code: String? = null,
            private var message: String? = null,
            private var page: Page<out AbstractDomain>? = null
    ) {

        private var pageableTO: PageableTO? = null;
        private var pageInfo: PageInfoTO? = null;

        fun code(code: String) = apply { this.code = code }
        fun message(message: String) = apply { this.message = message }
        fun page(page: Page<out AbstractDomain>) = apply {
            this.page = page
            this.pageableTO = PageableTO(
                    page.pageable.sort,
                    page.pageable.pageSize,
                    page.pageable.pageNumber,
                    page.pageable.offset,
                    page.pageable.isUnpaged,
                    page.pageable.isPaged)

            this.pageInfo = PageInfoTO(
                    pageableTO,
                    page.sort,
                    page.totalElements,
                    page.totalPages,
                    page.isLast,
                    page.isFirst,
                    page.size,
                    page.number,
                    page.numberOfElements,
                    page.isEmpty)

        }

        fun build() = PageResponseTO(
                content = page?.map { it.toData() }?.toList(),
                code = code,
                message = message,
                isPage = true,
                pageInfo = pageInfo
        )
    }
}
