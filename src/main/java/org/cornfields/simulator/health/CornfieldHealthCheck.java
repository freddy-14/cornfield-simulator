package org.cornfields.simulator.health;

import com.codahale.metrics.health.HealthCheck;
import org.cornfields.simulator.game.CornfieldMap;

public class CornfieldHealthCheck extends HealthCheck {

  private final CornfieldMap cornfields;

  public CornfieldHealthCheck(CornfieldMap cornfields) {
    this.cornfields = cornfields;
  }

  @Override
  protected Result check() {
    if (!cornfields.getCornfields().isEmpty()) {
      return Result.healthy();
    } else {
      return Result.unhealthy("no cornfields on map!");
    }
  }

}
