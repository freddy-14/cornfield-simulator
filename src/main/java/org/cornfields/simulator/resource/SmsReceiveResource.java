package org.cornfields.simulator.resource;

import com.codahale.metrics.annotation.Timed;
import org.cornfields.simulator.model.SmsResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sms")
@Produces(MediaType.APPLICATION_JSON)
public class SmsReceiveResource {

  @GET
  @Timed
  public SmsResponse receive(@QueryParam("sourceNumber") String sourceNumber,
                             @QueryParam("message")      String message)
  {
    if (sourceNumber == null || message == null) {
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }

    return new SmsResponse(sourceNumber, "corn");
  }

}
