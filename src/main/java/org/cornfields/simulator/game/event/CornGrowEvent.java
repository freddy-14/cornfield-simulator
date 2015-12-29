package org.cornfields.simulator.game.event;

import org.cornfields.simulator.game.CornfieldMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class CornGrowEvent implements Callable<Long> {

  private static final Logger log = LoggerFactory.getLogger(CornGrowEvent.class);

  private final CornfieldMap cornfieldMap;
  private long totalGrowCount = 0l;

  public CornGrowEvent(CornfieldMap cornfieldMap) {
    this.cornfieldMap = cornfieldMap;
  }

  @Override
  public Long call() {
    cornfieldMap.getCornfields().forEach(cornfield -> {
      log.info("growing corn on field " + cornfield.getId());

      /*
      todo:
        grow corn on the cornfield
        add to the totalGrowCount
       */

      log.info("cornfield " + cornfield.getId() + " now has " + cornfield.countCorn() + " corn");
    });

    log.info("corn grow event yielded " + totalGrowCount + " corn");
    return totalGrowCount;
  }

}
