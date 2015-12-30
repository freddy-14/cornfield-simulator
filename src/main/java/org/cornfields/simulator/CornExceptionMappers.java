package org.cornfields.simulator;

import org.cornfields.simulator.command.Command;
import org.cornfields.simulator.model.SmsMessage;
import org.cornfields.simulator.twilio.TwilioTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CornExceptionMappers {

  private static final Logger log = LoggerFactory.getLogger(CornExceptionMappers.class);

  public static class CommandNotAllowed implements ExceptionMapper<CommandNotAllowedException> {
    private final TwilioTranslator translator;

    public CommandNotAllowed(TwilioTranslator translator) {
      this.translator = translator;
    }

    @Override
    public Response toResponse(CommandNotAllowedException e) {
      Command    command    = e.getCommand();
      SmsMessage smsMessage = new SmsMessage(command.getFarmerId(), e.getMessage());

      log.warn("stopped " + command.getFarmerId() + " from using " + command.getType() + " incorrectly");

      return Response.ok(
          translator.translate(smsMessage),
          MediaType.TEXT_XML
      ).build();
    }
  }

}
