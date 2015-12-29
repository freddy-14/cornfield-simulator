package org.cornfields.simulator.game;

import org.junit.Test;

public class CommandProcessorTest {

  @Test
  public void testRegisterAliasTooLong() {
    final CommandProcessor processor = new CommandProcessor();

    try {

      processor.process(new RegisterCommand(Command.Type.REGISTER, "123", "dogeeeee"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

  @Test
  public void testTravelToUnknownCornfield() {
    final CommandProcessor processor = new CommandProcessor();

    try {

      processor.process(new TravelCommand(Command.Type.TRAVEL, "123", "9001"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

}
