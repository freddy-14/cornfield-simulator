package org.cornfields.simulator.game;

public class RegisterCommand extends Command {

  private final String alias;

  public RegisterCommand(Type type, String farmerId, String argument) {
    super(type, farmerId);

    this.alias = argument;
  }

  public String getAlias() {
    return alias;
  }

}
