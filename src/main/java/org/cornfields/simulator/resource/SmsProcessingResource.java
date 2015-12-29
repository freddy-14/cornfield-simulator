package org.cornfields.simulator.resource;

import com.codahale.metrics.annotation.Timed;
import org.cornfields.simulator.game.Command;
import org.cornfields.simulator.game.CommandFactory;
import org.cornfields.simulator.model.SmsResponse;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/sms")
@Produces(MediaType.APPLICATION_JSON)
public class SmsProcessingResource {

  private final CommandFactory commands;

  public SmsProcessingResource(CommandFactory commands) {
    this.commands = commands;
  }

  @GET
  @Timed
  public SmsResponse process(@NotEmpty @QueryParam("sourceNumber") String sourceNumber,
                             @NotEmpty @QueryParam("message")      String message)
  {
    Optional<Command> command = commands.create(sourceNumber, message);
    if (!command.isPresent()) {
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }

    if(command.get().getType() == Command.Type.CORN) {
      return new SmsResponse(sourceNumber, "not corn");
    }

    return new SmsResponse(sourceNumber, "corn");
  }

}
