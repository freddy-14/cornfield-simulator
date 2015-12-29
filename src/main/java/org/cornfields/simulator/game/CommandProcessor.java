package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.Command;
import org.cornfields.simulator.command.RegisterCommand;
import org.cornfields.simulator.command.TravelCommand;
import org.cornfields.simulator.model.SmsResponse;

public class CommandProcessor {

  public SmsResponse process(Command command) throws CommandNotAllowedException {
    switch (command.getType()) {
      case REGISTER:
        RegisterCommand register = (RegisterCommand) command;

        if (register.getAlias().isEmpty() || register.getAlias().length() > 4) {
          throw new CommandNotAllowedException(command, "alias must be 4 characters or less");
        } else {
          return new SmsResponse(command.getFarmerId(), "ok");
        }

      case TRAVEL:
        TravelCommand travel = (TravelCommand) command;

        if (travel.getCornfieldId() < 1 || travel.getCornfieldId() > 10) {
          throw new CommandNotAllowedException(command, "unknown cornfield, valid numbers are 1-10");
        } else {
          return new SmsResponse(command.getFarmerId(), "ok");
        }

      case CORN:
        return new SmsResponse(command.getFarmerId(), "ok");

      default:
        throw new IllegalArgumentException("I don't know about " + command.getType());
    }
  }

}
