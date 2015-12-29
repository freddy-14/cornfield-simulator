package org.cornfields.simulator.resource;

import com.codahale.metrics.annotation.Timed;
import org.cornfields.simulator.model.Corn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/corn")
@Produces(MediaType.APPLICATION_JSON)
public class CornResource {

  public CornResource() { }

  @GET
  @Timed
  public Corn get(@QueryParam("cornId") Integer cornId) {
    if (cornId == null) {
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }

    return new Corn(cornId, "corn");
  }

}
