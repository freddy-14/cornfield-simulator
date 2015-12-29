package org.cornfields.simulator.game;

/**
 * Created by freddy on 12/29/15.
 */
public class TravelCommand extends Command {

  final Integer cornfieldId;

  public TravelCommand(Type type, String user, String argument) {
    super(type, user);
    this.cornfieldId = Integer.parseInt(argument);
  }

  public Integer getCornfieldId() {
    return cornfieldId;
  }

}
