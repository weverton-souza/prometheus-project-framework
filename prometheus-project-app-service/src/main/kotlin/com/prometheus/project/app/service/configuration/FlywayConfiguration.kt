package com.prometheus.project.app.service.configuration

import com.prometheus.project.app.service.configuration.TenantResolverIdentifier.DEFAULT_TENANT
import com.prometheus.project.app.service.repository.TenantRepository
import java.util.UUID
import javax.sql.DataSource
import org.flywaydb.core.Flyway
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class FlywayConfiguration {

    @Bean
    fun flyway(dataSource: DataSource): Flyway {
        val flyway: Flyway = Flyway.configure()
            .locations("db/migration/default")
            .dataSource(dataSource)
            .schemas(DEFAULT_TENANT)
            .load()
        flyway.migrate()
        return flyway
    }

    @Bean
    fun commandLineRunner(repository: TenantRepository, dataSource: DataSource): CommandLineRunner {
        return CommandLineRunner {
            repository.findAll().forEach { tenant ->
                val tenantId: UUID = UUID.fromString(tenant.key)

                val flyway: Flyway = Flyway.configure()
                    .locations("db/migration/tenants")
                    .dataSource(dataSource)
                    .schemas(UUIDConverter.toPlainString(tenantId))
                    .load()
                flyway.migrate()
            }
        }
    }
}
