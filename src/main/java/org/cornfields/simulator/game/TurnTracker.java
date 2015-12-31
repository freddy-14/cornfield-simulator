package org.cornfields.simulator.game;

import org.cornfields.simulator.model.Farmer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TurnTracker implements FarmerDatabaseListener {

  private final Map<String, Boolean> turnMap = new ConcurrentHashMap<>();
  private final Object               txnLock = new Object();

  public boolean takeTurn(String farmerId) {
    synchronized (txnLock) {
      Boolean turn = turnMap.get(farmerId);
      turnMap.put(farmerId, Boolean.FALSE);

      return turn;
    }
  }

  public void resetTurns() {
    turnMap.keySet().stream().forEach(farmerId -> turnMap.put(farmerId, Boolean.TRUE));
  }

  @Override
  public void onFarmerRegistered(Farmer farmer) {
    turnMap.put(farmer.getFarmerId(), Boolean.TRUE);
  }

  @Override
  public void onFarmerUnRegistered(Farmer farmer) {
    turnMap.remove(farmer.getFarmerId());
  }

}
