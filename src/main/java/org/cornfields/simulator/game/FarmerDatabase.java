package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.RegisterCommand;
import org.cornfields.simulator.model.Farmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class FarmerDatabase {

  private static final Long INITIAL_CORN = 100l;

  private static final Logger log = LoggerFactory.getLogger(FarmerDatabase.class);

  private final Map<String, Farmer>           farmers   = new ConcurrentHashMap<>();
  private final Queue<FarmerDatabaseListener> listeners = new LinkedBlockingQueue<>();
  private final Object                        txnLock   = new Object();

  private Farmer initFarmer(RegisterCommand registration) {
    return new Farmer(
        registration.getFarmerId(),
        registration.getAlias(),
        INITIAL_CORN
    );
  }

  private boolean aliasRegistered(String alias) {
    return farmers.keySet()
                  .stream()
                  .map(farmers::get)
                  .anyMatch(farmer -> farmer.getAlias().equals(alias));
  }

  public void addListener(FarmerDatabaseListener listener) {
    listeners.add(listener);
  }

  public void removeListener(FarmerDatabaseListener listener) {
    listeners.remove(listener);
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
        Farmer farmer = initFarmer(registration);
        farmers.put(registration.getFarmerId(), farmer);
        listeners.forEach(listener -> listener.onFarmerRegistered(farmer));
      }
    }
  }

  public void unregister(String farmerId) {
    Optional<Farmer> farmer = Optional.ofNullable(farmers.remove(farmerId));
    if (farmer.isPresent()) {
      listeners.forEach(listener -> listener.onFarmerUnRegistered(farmer.get()));
    } else {
      log.warn("ignoring unregister of unknown farmer " + farmerId);
    }
  }

  public Optional<Farmer> get(String farmerId) {
    return Optional.ofNullable(farmers.get(farmerId));
  }

}
