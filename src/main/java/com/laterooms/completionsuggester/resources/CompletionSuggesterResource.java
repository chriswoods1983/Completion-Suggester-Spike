package com.laterooms.completionsuggester.resources;

/**
 * Created by chris on 31/07/15.
 */

import com.codahale.metrics.annotation.Timed;
import com.laterooms.completionsuggester.core.Suggestions;
import com.laterooms.completionsuggester.core.Suggester;
import org.apache.lucene.search.suggest.Lookup;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/s")
@Produces(MediaType.APPLICATION_JSON)
public class CompletionSuggesterResource {


    @GET
    @Timed
    public Suggestions get(@QueryParam("q") String queryText) {


        Suggester suggester = new Suggester();

        List<Lookup.LookupResult> l = null;

        try {

            l = suggester.findBy(queryText);
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<String> suggestions = new ArrayList<String>();

        assert l != null;

        for (Lookup.LookupResult result : l) {
            suggestions.add(result.key.toString());

        }


        return new Suggestions(suggestions);
    }
}
