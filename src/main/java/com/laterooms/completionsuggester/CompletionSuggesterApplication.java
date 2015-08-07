package com.laterooms.completionsuggester;

import com.laterooms.completionsuggester.health.TemplateHealthCheck;
import com.laterooms.completionsuggester.resources.CompletionSuggesterResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by chris on 31/07/15.
 */
public class CompletionSuggesterApplication extends Application<CompletionSuggesterConfiguration> {
    public static void main(String[] args) throws Exception {
        new CompletionSuggesterApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<CompletionSuggesterConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(CompletionSuggesterConfiguration configuration,
                    Environment environment) {
        final CompletionSuggesterResource resource = new CompletionSuggesterResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
