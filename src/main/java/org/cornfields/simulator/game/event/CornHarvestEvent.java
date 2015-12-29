package org.cornfields.simulator.game.event;

import org.cornfields.simulator.game.CornfieldMap;
import org.cornfields.simulator.game.FarmerDatabase;
import org.cornfields.simulator.model.Farmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.Callable;

public class CornHarvestEvent implements Callable<Long> {

  private static final Logger log = LoggerFactory.getLogger(CornHarvestEvent.class);

  private final CornfieldMap   cornfieldMap;
  private final FarmerDatabase farmerDatabase;

  private long totalHarvestCount = 0l;

  public CornHarvestEvent(CornfieldMap cornfieldMap, FarmerDatabase farmerDatabase) {
    this.cornfieldMap   = cornfieldMap;
    this.farmerDatabase = farmerDatabase;
  }

  private void shareCornWithFarmer(String farmerId, long cornCount) {
    Optional<Farmer> farmer = farmerDatabase.get(farmerId);
    if (farmer.isPresent()) {
      farmer.get().addCorn(cornCount);
      log.info("shared " + cornCount + " corn from harvest with " + farmer.get().getAlias());
    } else {
      log.error("what happened to farmer " + farmerId + "?");
    }
  }

  @Override
  public Long call() {
    cornfieldMap.getCornfields().forEach(cornfield -> {
      log.info("harvesting corn from " + cornfield.getId());

      /*
      todo:
        harvest all the corn from the cornfield
        figure out how many farmers are on the cornfield
        split the harvested corn between farmers
        use 'shareCornWithFarmer()' method
        add to the totalHarvestCount
       */
    });

    log.info("corn harvest event yielded " + totalHarvestCount + " corn");
    return totalHarvestCount;
  }

}
