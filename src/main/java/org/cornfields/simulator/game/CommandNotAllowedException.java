package org.cornfields.simulator.game;

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
