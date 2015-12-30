package org.cornfields.simulator.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CornConfig extends Configuration {

  @Valid
  @NotNull
  private TwilioConfig twilio;

  @Min(1) private Long cornGrowIntervalMinutes;
  @Min(1) private Long cornHarvestIntervalMinutes;

  @JsonProperty
  public TwilioConfig getTwilio() {
    return twilio;
  }

  @JsonProperty
  public Long getCornGrowIntervalMinutes() {
    return cornGrowIntervalMinutes;
  }

  @JsonProperty
  public Long getCornHarvestIntervalMinutes() {
    return cornHarvestIntervalMinutes;
  }

}
