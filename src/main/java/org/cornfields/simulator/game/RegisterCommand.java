package org.cornfields.simulator.game;

public class RegisterCommand extends Command {

  private final String alias;

  public RegisterCommand(String farmerId, String argument) {
    super(Type.REGISTER, farmerId);

    this.alias = argument;
  }

  public String getAlias() {
    return alias;
  }

}
