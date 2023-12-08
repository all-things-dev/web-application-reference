package config.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Provides Hibernate configuration for database persistence.
 * Also enables transaction management for services.
 */
@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = { "dev.all_things.reference.common.model", "dev.all_things.reference.app.model" })
public class HibernateConfiguration
{

}
