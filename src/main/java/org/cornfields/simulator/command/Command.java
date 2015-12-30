package org.cornfields.simulator.command;

public class Command {

  public enum Type {
    REGISTER, TRAVEL, CORN, UNREGISTER
  }

  private final Type   type;
  private final String farmerId;

  public Command(Type type, String farmerId) {
    this.type     = type;
    this.farmerId = farmerId;
  }

  public Type getType() {
    return type;
  }

  public String getFarmerId() {
    return farmerId;
  }

}
