package com.laterooms.completionsuggester;

import com.codahale.metrics.health.HealthCheckRegistry;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;
import com.laterooms.completionsuggester.resources.CompletionSuggesterResource;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * Created by chris on 31/07/15.
 */
public class CompletionSuggesterApplicationTest {
    private final Environment environment = mock(Environment.class);
    private final JerseyEnvironment jersey = mock(JerseyEnvironment.class);
    private final HealthCheckRegistry healthCheckRegistry = mock(HealthCheckRegistry.class);
    private final CompletionSuggesterApplication application = new CompletionSuggesterApplication();
    private final CompletionSuggesterConfiguration config = new CompletionSuggesterConfiguration();

    @Before
    public void setup() throws Exception {
        config.setTemplate("Hello, %s!");
        config.setDefaultName("Stranger");
        when(environment.healthChecks()).thenReturn(healthCheckRegistry);
        when(environment.jersey()).thenReturn(jersey);
    }

    @Test
    public void buildsAThingResource() throws Exception {
        application.run(config, environment);

        verify(jersey).register(isA(CompletionSuggesterResource.class));
    }
}