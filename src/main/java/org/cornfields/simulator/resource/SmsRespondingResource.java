package org.cornfields.simulator.resource;

import com.codahale.metrics.annotation.Timed;
import org.cornfields.simulator.command.Command;
import org.cornfields.simulator.command.CommandFactory;
import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.game.Simulator;
import org.cornfields.simulator.model.SmsMessage;
import org.cornfields.simulator.twilio.SmsSender;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/sms")
@Produces(MediaType.APPLICATION_JSON)
public class SmsRespondingResource {

  private final CommandFactory commands;
  private final Simulator      simulator;
  private final SmsSender      smsSender;

  public SmsRespondingResource(CommandFactory commands, Simulator simulator, SmsSender smsSender) {
    this.commands  = commands;
    this.simulator = simulator;
    this.smsSender = smsSender;
  }

  @GET
  @Timed
  public SmsMessage respond(@NotEmpty @QueryParam("sourceNumber") String sourceNumber,
                            @NotEmpty @QueryParam("message")      String message)
      throws CommandNotAllowedException
  {
    Optional<Command> command = commands.create(sourceNumber, message);

    if (!command.isPresent()) {
      return new SmsMessage(sourceNumber, "unknown command or bad command arguments");
    } else {
      return simulator.process(command.get());
    }
  }

}
