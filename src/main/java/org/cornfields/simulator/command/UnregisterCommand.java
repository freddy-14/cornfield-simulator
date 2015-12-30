package org.cornfields.simulator.command;

public class UnregisterCommand extends Command {

  public UnregisterCommand(String farmerId) {
    super(Type.UNREGISTER, farmerId);
  }

}
