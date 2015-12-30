package org.cornfields.simulator.resource;

import com.codahale.metrics.annotation.Timed;
import com.twilio.sdk.verbs.TwiMLResponse;
import org.cornfields.simulator.command.Command;
import org.cornfields.simulator.command.CommandFactory;
import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.game.Simulator;
import org.cornfields.simulator.model.SmsMessage;
import org.cornfields.simulator.twilio.TwilioTranslator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/sms")
@Produces(MediaType.TEXT_XML)
public class SmsRespondingResource {

  private final CommandFactory   commands;
  private final Simulator        simulator;
  private final TwilioTranslator translator;

  public SmsRespondingResource(CommandFactory   commands,
                               Simulator        simulator,
                               TwilioTranslator translator)
  {
    this.commands   = commands;
    this.simulator  = simulator;
    this.translator = translator;
  }

  @GET
  @Timed
  public TwiMLResponse respond(@NotEmpty @QueryParam("From") String sourceNumber,
                               @NotNull  @QueryParam("Body") String message)
      throws CommandNotAllowedException
  {
    Optional<Command> command = commands.create(sourceNumber, message);

    if (!command.isPresent()) {
      return translator.translate(
          new SmsMessage(sourceNumber, "unknown command or bad command arguments")
      );
    } else {
      return translator.translate(
          simulator.process(command.get())
      );
    }
  }

}
