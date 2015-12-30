package org.cornfields.simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.Min;

public class CornConfig extends Configuration {

  @Min(1) private Long cornGrowIntervalMinutes;
  @Min(1) private Long cornHarvestIntervalMinutes;

  @JsonProperty
  public Long getCornGrowIntervalMinutes() {
    return cornGrowIntervalMinutes;
  }

  @JsonProperty
  public Long getCornHarvestIntervalMinutes() {
    return cornHarvestIntervalMinutes;
  }

}
