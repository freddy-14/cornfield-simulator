package org.cornfields.simulator.command;

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
