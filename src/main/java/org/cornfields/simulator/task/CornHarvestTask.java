package org.cornfields.simulator.task;

import org.cornfields.simulator.game.CornfieldMap;
import org.cornfields.simulator.game.FarmerDatabase;
import org.cornfields.simulator.model.Farmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class CornHarvestTask extends RunnableTask {

  private static final Logger log = LoggerFactory.getLogger(CornHarvestTask.class);

  private final FarmerDatabase farmerDatabase;
  private final CornfieldMap   cornfieldMap;

  private long totalHarvestCount = 0l;

  public CornHarvestTask(FarmerDatabase farmerDatabase, CornfieldMap cornfieldMap) {
    super("harvest-cornfields");
    this.farmerDatabase = farmerDatabase;
    this.cornfieldMap   = cornfieldMap;
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
  public void run() {
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
  }

}
