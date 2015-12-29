package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.RegisterCommand;
import org.cornfields.simulator.model.Farmer;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class FarmerDatabase {

  private static final Long INITIAL_CORNFIELD = -1l;
  private static final Long INITIAL_CORN      = 100l;

  private final Map<String, Farmer> farmers = new ConcurrentHashMap<>();
  private final Object              txnLock = new Object();

  private Farmer initFarmer(RegisterCommand registration) {
    return new Farmer(
        registration.getFarmerId(),
        registration.getAlias(),
        INITIAL_CORNFIELD,
        INITIAL_CORN
    );
  }

  private boolean aliasRegistered(String alias) {
    return farmers.keySet()
                  .stream()
                  .map(farmers::get)
                  .anyMatch(farmer -> farmer.getAlias().equals(alias));
  }

  public void register(RegisterCommand registration) throws CommandNotAllowedException {
    if (registration.getAlias().isEmpty() || registration.getAlias().length() > 4) {
      throw new CommandNotAllowedException(registration, "alias must be 4 characters or less");
    }

    synchronized (txnLock) {
      if (farmers.containsKey(registration.getFarmerId())) {
        throw new CommandNotAllowedException(registration, "you are already registered");
      } else if (aliasRegistered(registration.getAlias())) {
        throw new CommandNotAllowedException(registration, "alias already taken");
      } else {
        farmers.put(registration.getFarmerId(), initFarmer(registration));
      }
    }
  }

  public void unregister(String farmerId) {
    farmers.remove(farmerId);
  }

  public Optional<Farmer> get(String farmerId) {
    return Optional.ofNullable(farmers.get(farmerId));
  }

}
