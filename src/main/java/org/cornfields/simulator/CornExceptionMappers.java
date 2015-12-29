package org.cornfields.simulator;

import org.cornfields.simulator.game.Command;
import org.cornfields.simulator.game.CommandNotAllowedException;
import org.cornfields.simulator.model.SmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CornExceptionMappers {

  private static final Logger log = LoggerFactory.getLogger(CornExceptionMappers.class);

  public static class CommandNotAllowed implements ExceptionMapper<CommandNotAllowedException> {

    @Override
    public Response toResponse(CommandNotAllowedException e) {
      Command     command  = e.getCommand();
      SmsResponse response = new SmsResponse(command.getFarmerId(), e.getMessage());

      log.warn("stopped " + command.getFarmerId() + " from using " + command.getType() + " incorrectly");

      return Response.ok(response, MediaType.APPLICATION_JSON).build();
    }

  }
}
