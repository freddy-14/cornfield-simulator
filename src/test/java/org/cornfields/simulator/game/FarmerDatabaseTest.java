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

  @Test
  public void testRegisterExistingFarmer() throws Exception {
    final FarmerDatabase farmers = new FarmerDatabase();

    farmers.register(new RegisterCommand("123", "CORN"));

    try {

      farmers.register(new RegisterCommand("123", "CORN"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

  @Test
  public void testRegisterExistingAlias() throws Exception {
    final FarmerDatabase farmers = new FarmerDatabase();

    farmers.register(new RegisterCommand("123", "CORN"));

    try {

      farmers.register(new RegisterCommand("456", "CORN"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

  @Test
  public void testRegister() throws Exception {
    final FarmerDatabase farmers = new FarmerDatabase();

    farmers.register(new RegisterCommand("123", "CORN"));
    assert farmers.get("123").isPresent();
  }

  @Test
  public void testUnregister() throws Exception {
    final FarmerDatabase farmers = new FarmerDatabase();

    farmers.register(new RegisterCommand("123", "CORN"));
    assert farmers.get("123").isPresent();
    farmers.unregister("123");
    assert !farmers.get("123").isPresent();
  }

}
