package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.RegisterCommand;
import org.cornfields.simulator.command.TravelCommand;
import org.junit.Test;

public class CommandProcessorTest {

  @Test
  public void testRegisterAliasTooShort() {
    final CommandProcessor processor = new CommandProcessor();

    try {

      processor.process(new RegisterCommand("123", ""));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

  @Test
  public void testRegisterAliasTooLong() {
    final CommandProcessor processor = new CommandProcessor();

    try {

      processor.process(new RegisterCommand("123", "dogeeeeeee"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

  @Test
  public void testTravelToUnknownCornfield() {
    final CommandProcessor processor = new CommandProcessor();

    try {

      processor.process(new TravelCommand("123", "9001"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

}
