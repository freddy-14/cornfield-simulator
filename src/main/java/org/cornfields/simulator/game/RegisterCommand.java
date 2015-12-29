package org.cornfields.simulator.game;

/**
 * Created by freddy on 12/29/15.
 */
public class RegisterCommand extends Command {

  String alias;

  public RegisterCommand(Type type, String user, String argument) {
    super(type, user);
    this.alias = argument;
  }

  public String getAlias() {
    return alias;
  }

}
