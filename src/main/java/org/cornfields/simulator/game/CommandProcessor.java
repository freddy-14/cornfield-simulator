package org.cornfields.simulator.game;

import org.cornfields.simulator.model.SmsResponse;

public class CommandProcessor {

  public SmsResponse process(Command command) throws CommandNotAllowedException {
    return new SmsResponse(command.getFarmerId(), "ok");
  }

}
