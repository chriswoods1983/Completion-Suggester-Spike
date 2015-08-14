package com.laterooms.completionsuggester.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by cwoods on 11/08/2015.
 */
public class Suggestion {

    private String displayText;
    private List<String> payload;

    public Suggestion(String displayText, List<String> payload) {
        this.displayText = displayText;
        this.payload = payload;
    }

    @JsonProperty
    public List<String> getPayload() {
        return payload;
    }

    @JsonProperty
    public String getDisplayText() {
        return displayText;
    }
}
