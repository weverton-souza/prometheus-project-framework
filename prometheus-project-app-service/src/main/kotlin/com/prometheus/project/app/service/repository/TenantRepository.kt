package com.prometheus.project.app.service.repository

import com.prometheus.project.app.service.domain.Tenant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TenantRepository: JpaRepository<Tenant, String>
