package dev.all_things.reference;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

/**
 * Provides start-up configuration of test classes annotated with {@link WebMvcTest}.
 */
@SpringBootApplication(scanBasePackages = { "config", "dev.all_things" })
public class TestApplication
{

}
