package org.cornfields.simulator.command;

public class TravelCommand extends Command {

  final Long cornfieldId;

  public TravelCommand(String farmerId, String argument) {
    super(Type.TRAVEL, farmerId);

    this.cornfieldId = Long.parseLong(argument);
  }

  public Long getCornfieldId() {
    return cornfieldId;
  }

}
