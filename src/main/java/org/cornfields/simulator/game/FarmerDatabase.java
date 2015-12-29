package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.RegisterCommand;

public class FarmerDatabase {

  public void register(RegisterCommand registration) throws CommandNotAllowedException {
    if (registration.getAlias().isEmpty() || registration.getAlias().length() > 4) {
      throw new CommandNotAllowedException(registration, "alias must be 4 characters or less");
    }
  }

}
