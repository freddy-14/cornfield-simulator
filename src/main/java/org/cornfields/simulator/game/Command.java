package org.cornfields.simulator.game;

public class Command {

  public enum Type {
    REGISTER, TRAVEL
  }

  private final Type type;

  public Command(Type type) {
    this.type = type;
  }

}
