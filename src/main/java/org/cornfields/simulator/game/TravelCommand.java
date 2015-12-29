package org.cornfields.simulator.game;

public class TravelCommand extends Command {

  final Long cornfieldId;

  public TravelCommand(Type type, String farmerId, String argument) {
    super(type, farmerId);

    this.cornfieldId = Long.parseLong(argument);
  }

  public Long getCornfieldId() {
    return cornfieldId;
  }

}
