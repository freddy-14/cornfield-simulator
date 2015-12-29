package org.cornfields.simulator.game;

import org.cornfields.simulator.model.Farmer;

public interface FarmerDatabaseListener {

  public void onFarmerRegistered(Farmer farmer);

  public void onFarmerUnRegistered(Farmer farmer);

}
