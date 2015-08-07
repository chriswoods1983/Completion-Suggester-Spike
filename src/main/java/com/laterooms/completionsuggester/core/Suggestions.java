package com.laterooms.completionsuggester.core;

/**
 * Created by chris on 31/07/15.
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Suggestions {
    private List<String> suggestions;

    public Suggestions() {
        // Jackson deserialization
    }

    public Suggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    @JsonProperty("d")
    public List<String> getData() {
        return suggestions;
    }
}
