package org.cornfields.simulator.command;

import org.junit.Test;

import java.util.Optional;

public class CommandFactoryTest {

  @Test
  public void testBadCommandType() {
    final CommandFactory factory = new CommandFactory();
    final Optional<Command> command = factory.create("555", "BADTYPE");

    assert !command.isPresent();
  }

  @Test
  public void testCommandTypeRegisterNoArgument() {
    final CommandFactory    factory = new CommandFactory();
    final Optional<Command> command = factory.create("555", "REGISTER");

    assert !command.isPresent();
  }

  @Test
  public void testCommandTypeRegister() {
    final CommandFactory    factory = new CommandFactory();
    final Optional<Command> command = factory.create("555", "REGISTER leaf");

    assert command.isPresent();
  }

  @Test
  public void testCommandTypeTravel() {
    final CommandFactory    factory = new CommandFactory();
    final Optional<Command> command = factory.create("555", "TRAVEL 1");

    assert command.isPresent();
  }

  @Test
  public void testCommandTypeTravelNoArgument() {
    final CommandFactory    factory = new CommandFactory();
    final Optional<Command> command = factory.create("555", "TRAVEL");

    assert !command.isPresent();
  }

  @Test
  public void testCommandTypeUnregister() {
    final CommandFactory    factory = new CommandFactory();
    final Optional<Command> command = factory.create("555", "UNREGISTER");

    assert command.isPresent();
  }

}
