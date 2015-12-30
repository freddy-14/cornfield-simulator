package org.cornfields.simulator.command;

public class TravelCommand extends Command {

  final Long cornfieldId;

  public TravelCommand(String farmerId, String argument) {
    super(Type.TRAVEL, farmerId);

    Long parsed;

    try {
      parsed = Long.parseLong(argument);
    } catch (NumberFormatException e) {
      parsed = -1l;
    }

    this.cornfieldId = parsed;
  }

  public Long getCornfieldId() {
    return cornfieldId;
  }

}
