package config.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = { "dev.all_things.reference.app.repository" })
public class SpringDataConfiguration
{


}
