package com.prometheus.project.app.service.repository

import com.prometheus.project.app.service.domain.Authority
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository: JpaRepository<Authority, String>
