package org.cornfields.simulator.command;

import java.util.Optional;

public class CommandFactory {

  public Optional<Command> create(String sourceNumber, String message) {
    String[]         messageParts  = message.toUpperCase().split(" ");
    String           commandString = messageParts[0];
    Optional<String> argument      = Optional.empty();

    if(messageParts.length > 1) {
      argument = Optional.of(messageParts[1]);
    }

    if (commandString.equals("REGISTER") & argument.isPresent()) {
      return Optional.of(new RegisterCommand(sourceNumber, argument.get()));
    } else if (commandString.equals("TRAVEL") & argument.isPresent()) {
      return Optional.of(new TravelCommand(sourceNumber, argument.get()));
    } else if (commandString.equals("CORN")) {
      return Optional.of(new Command(Command.Type.CORN, sourceNumber));
    }

    return Optional.empty();
  }
}
