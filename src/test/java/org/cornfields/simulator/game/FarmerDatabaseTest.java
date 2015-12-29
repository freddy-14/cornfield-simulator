package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.RegisterCommand;
import org.junit.Test;

public class FarmerDatabaseTest {

  @Test
  public void testRegisterAliasTooShort() {
    final FarmerDatabase farmers = new FarmerDatabase();

    try {

      farmers.register(new RegisterCommand("123", ""));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

  @Test
  public void testRegisterAliasTooLong() {
    final FarmerDatabase farmers = new FarmerDatabase();

    try {

      farmers.register(new RegisterCommand("123", "dogeeeeeee"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

}
