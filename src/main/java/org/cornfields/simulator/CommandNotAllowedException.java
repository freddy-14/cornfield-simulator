package org.cornfields.simulator;

import org.cornfields.simulator.command.Command;

public class CommandNotAllowedException extends Exception {

  private final Command command;

  public CommandNotAllowedException(Command command, String message) {
    super(message);
    this.command = command;
  }

  public Command getCommand() {
    return command;
  }

}
