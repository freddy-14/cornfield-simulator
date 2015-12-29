package org.cornfields.simulator.game;

public class Command {

  public enum Type {
    REGISTER, TRAVEL, CORN
  }

  private final Type type;
  private final String user;

  public Command(Type type, String user) {
    this.type = type;
    this.user = user;
  }

  public Type getType() {
    return type;
  }

  public String getUser() {
    return user;
  }

}
