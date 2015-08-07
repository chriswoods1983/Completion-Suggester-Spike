package com.laterooms.completionsuggester;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by chris on 31/07/15.
 */
public class CompletionSuggesterConfiguration extends Configuration {
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty
    public String getIndexLocation() {
        return indexLocation;
    }
    @JsonProperty
    public void setIndexLocation(String indexLocation) {
        this.indexLocation = indexLocation;
    }

    @NotEmpty
    private String indexLocation;

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

}
