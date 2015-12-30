package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.RegisterCommand;
import org.junit.Test;

public class FarmerDatabaseTest {

  @Test
  public void testRegisterAliasTooShort() {
    final FarmerDatabase farmers = new FarmerDatabase();

    try {

      farmers.register(new RegisterCommand("555", ""));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

  @Test
  public void testRegisterAliasTooLong() {
    final FarmerDatabase farmers = new FarmerDatabase();

    try {

      farmers.register(new RegisterCommand("555", "dogeeeeeee"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

  @Test
  public void testRegisterExistingFarmer() throws Exception {
    final FarmerDatabase farmers = new FarmerDatabase();

    farmers.register(new RegisterCommand("555", "CORN"));

    try {

      farmers.register(new RegisterCommand("555", "CORN"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

  @Test
  public void testRegisterExistingAlias() throws Exception {
    final FarmerDatabase farmers = new FarmerDatabase();

    farmers.register(new RegisterCommand("555", "CORN"));

    try {

      farmers.register(new RegisterCommand("666", "CORN"));
      assert false;

    } catch (CommandNotAllowedException e) {
      assert true;
    }
  }

  @Test
  public void testRegister() throws Exception {
    final FarmerDatabase farmers = new FarmerDatabase();

    farmers.register(new RegisterCommand("555", "CORN"));
    assert farmers.get("555").isPresent();
  }

  @Test
  public void testUnregister() throws Exception {
    final FarmerDatabase farmers = new FarmerDatabase();

    farmers.register(new RegisterCommand("555", "CORN"));
    assert farmers.get("555").isPresent();
    farmers.unregister("555");
    assert !farmers.get("555").isPresent();
  }

}
