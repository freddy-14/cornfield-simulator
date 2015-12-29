package org.cornfields.simulator.game;

public class CommandNotAllowedException extends Exception {

  public CommandNotAllowedException(String message) {
    super(message);
  }

  public CommandNotAllowedException(String message, Throwable cause) {
    super(message, cause);
  }

}
