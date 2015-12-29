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

    assert cornfieldMap.getCornfield(8l).getFarmers().size() == 1;
    assert cornfieldMap.getCornfield(8l).getFarmers().contains("555");
  }

  @Test
  public void testTravelBetweenCornfields() throws Exception {
    final CornfieldMap cornfieldMap = new CornfieldMap();

    cornfieldMap.travel(new TravelCommand("555", "8"));

    assert cornfieldMap.getCornfield(8l).getFarmers().size() == 1;
    assert cornfieldMap.getCornfield(8l).getFarmers().contains("555");

    cornfieldMap.travel(new TravelCommand("555", "9"));

    assert cornfieldMap.getCornfield(8l).getFarmers().size() == 0;
    assert cornfieldMap.getCornfield(9l).getFarmers().size() == 1;
    assert cornfieldMap.getCornfield(9l).getFarmers().contains("555");
  }

}
