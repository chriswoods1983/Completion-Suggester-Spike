package com.laterooms.completionsuggester.resources;

/**
 * Created by chris on 31/07/15.
*/

 import com.google.common.base.Optional;
 import com.codahale.metrics.annotation.Timed;
 import com.laterooms.completionsuggester.core.Saying;
 import com.laterooms.completionsuggester.core.Suggester;
 import org.apache.lucene.search.suggest.Lookup;

 import javax.ws.rs.GET;
 import javax.ws.rs.Path;
 import javax.ws.rs.Produces;
 import javax.ws.rs.QueryParam;
 import javax.ws.rs.core.MediaType;
 import java.io.IOException;
 import java.util.List;
 import java.util.concurrent.atomic.AtomicLong;

 @Path("/hello-world")
 @Produces(MediaType.APPLICATION_JSON)
 public class CompletionSuggesterResource {
 private final String template;
 private final String defaultName;
 private final AtomicLong counter;

 public CompletionSuggesterResource(String template, String defaultName) {
 this.template = template;
 this.defaultName = defaultName;
 this.counter = new AtomicLong();
 }

 @GET
 @Timed
 public Saying sayHello(@QueryParam("name") Optional<String> name) {


  Suggester s = new Suggester();

  List<Lookup.LookupResult> l = null;

  try {

   l = s.findBy(name.or(defaultName));
  } catch (IOException e) {
   e.printStackTrace();
  }



  String value = String.format(template, name.or(defaultName).toString());

  assert l != null;

for(Lookup.LookupResult result : l){
 value += result.key.toString() + ", ";

}



 return new Saying(counter.incrementAndGet(), value);
 }
 }
