package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.TravelCommand;
import org.junit.Test;

public class CornfieldMapTest {

  @Test
  public void testTravelToUnknownCornfield() {
    final CornfieldMap cornfieldMap = new CornfieldMap();

    try {

      cornfieldMap.travel(new TravelCommand("555", "9001"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

  @Test
  public void testTravelToCornfield() throws Exception {
    final CornfieldMap cornfieldMap = new CornfieldMap();

    cornfieldMap.travel(new TravelCommand("555", "8"));

    assert cornfieldMap.getFarmersOn(8l).size() == 1;
    assert cornfieldMap.getFarmersOn(8l).contains("555");
  }

  @Test
  public void testTravelBetweenCornfields() throws Exception {
    final CornfieldMap cornfieldMap = new CornfieldMap();

    cornfieldMap.travel(new TravelCommand("555", "8"));

    assert cornfieldMap.getFarmersOn(8l).size() == 1;
    assert cornfieldMap.getFarmersOn(8l).contains("555");

    cornfieldMap.travel(new TravelCommand("555", "9"));

    assert cornfieldMap.getFarmersOn(8l).size() == 0;
    assert cornfieldMap.getFarmersOn(9l).size() == 1;
    assert cornfieldMap.getFarmersOn(9l).contains("555");
  }

}
