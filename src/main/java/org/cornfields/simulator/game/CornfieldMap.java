package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.TravelCommand;

public class CornfieldMap {

  public void travel(TravelCommand travel) throws CommandNotAllowedException {
    if (travel.getCornfieldId() < 1 || travel.getCornfieldId() > 10) {
      throw new CommandNotAllowedException(travel, "unknown cornfield, valid numbers are 1-10");
    }
  }

}
