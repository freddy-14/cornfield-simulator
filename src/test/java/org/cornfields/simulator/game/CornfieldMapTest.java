package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.TravelCommand;
import org.junit.Test;
import org.mockito.Mockito;

public class CornfieldMapTest {

  @Test
  public void testTravelToUnknownCornfield() {
    final TurnTracker  turnTracker  = Mockito.mock(TurnTracker.class);
    final CornfieldMap cornfieldMap = new CornfieldMap(turnTracker);

    Mockito.when(turnTracker.takeTurn(Mockito.anyString())).thenReturn(Boolean.TRUE);

    try {

      cornfieldMap.travel(new TravelCommand("555", "9001"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

  @Test
  public void testTravelToCornfield() throws Exception {
    final TurnTracker  turnTracker  = Mockito.mock(TurnTracker.class);
    final CornfieldMap cornfieldMap = new CornfieldMap(turnTracker);

    Mockito.when(turnTracker.takeTurn(Mockito.anyString())).thenReturn(Boolean.TRUE);

    cornfieldMap.travel(new TravelCommand("555", "8"));

    assert cornfieldMap.getCornfield(8l).getFarmers().size() == 1;
    assert cornfieldMap.getCornfield(8l).getFarmers().contains("555");
  }

  @Test
  public void testTravelBetweenCornfields() throws Exception {
    final TurnTracker  turnTracker  = Mockito.mock(TurnTracker.class);
    final CornfieldMap cornfieldMap = new CornfieldMap(turnTracker);

    Mockito.when(turnTracker.takeTurn(Mockito.anyString())).thenReturn(Boolean.TRUE);

    cornfieldMap.travel(new TravelCommand("555", "8"));

    assert cornfieldMap.getCornfield(8l).getFarmers().size() == 1;
    assert cornfieldMap.getCornfield(8l).getFarmers().contains("555");

    cornfieldMap.travel(new TravelCommand("555", "9"));

    assert cornfieldMap.getCornfield(8l).getFarmers().size() == 0;
    assert cornfieldMap.getCornfield(9l).getFarmers().size() == 1;
    assert cornfieldMap.getCornfield(9l).getFarmers().contains("555");
  }

  @Test
  public void testTravelNoTurnsRemaining() {
    final TurnTracker  turnTracker  = Mockito.mock(TurnTracker.class);
    final CornfieldMap cornfieldMap = new CornfieldMap(turnTracker);

    Mockito.when(turnTracker.takeTurn(Mockito.anyString())).thenReturn(Boolean.FALSE);

    try {

      cornfieldMap.travel(new TravelCommand("555", "8"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

}
