package org.cornfields.simulator.game;

import org.junit.Test;

import java.util.Optional;

public class CommandFactoryTest {

  @Test
  public void testBadCommandType() {
    final CommandFactory    factory = new CommandFactory();
    final Optional<Command> command = factory.create("123", "BADTYPE");

    assert !command.isPresent();
  }

  @Test
  public void testCommandTypeRegisterNoArgument() {
    final CommandFactory    factory = new CommandFactory();
    final Optional<Command> command = factory.create("123", "REGISTER");

    assert !command.isPresent();
  }

  @Test
  public void testCommandTypeRegister() {
    final CommandFactory    factory = new CommandFactory();
    final Optional<Command> command = factory.create("123", "REGISTER leaf");

    assert command.isPresent();
  }

  @Test
  public void testCommandTypeTravel() {
    final CommandFactory    factory = new CommandFactory();
    final Optional<Command> command = factory.create("123", "TRAVEL 1");

    assert command.isPresent();
  }

  @Test
  public void testCommandTypeTravelNoArgs() {
    final CommandFactory    factory = new CommandFactory();
    final Optional<Command> command = factory.create("123", "TRAVEL");

    assert !command.isPresent();
  }

}
