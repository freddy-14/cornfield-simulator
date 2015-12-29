package org.cornfields.simulator.game;

import java.util.Optional;

public class CommandFactory {

  public Optional<Command> create(String userNumber, String message) {
    if (message.toUpperCase().startsWith("REGISTER")) {
      return Optional.of(new Command(Command.Type.REGISTER));
    } else if (message.toUpperCase().startsWith("TRAVEL")) {
      return Optional.of(new Command(Command.Type.TRAVEL));
    } else {
      return Optional.empty();
    }
  }

}
