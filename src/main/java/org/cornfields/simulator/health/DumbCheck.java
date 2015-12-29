package org.cornfields.simulator.health;

import com.codahale.metrics.health.HealthCheck;

public class DumbCheck extends HealthCheck {

  @Override
  protected Result check() {
    return Result.healthy();
  }

}
