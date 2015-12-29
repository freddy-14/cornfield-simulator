package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.TravelCommand;
import org.junit.Test;

public class CornfieldMapTest {

  @Test
  public void testTravelToUnknownCornfield() {
    final CornfieldMap cornfields = new CornfieldMap();

    try {

      cornfields.travel(new TravelCommand("123", "9001"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

}
