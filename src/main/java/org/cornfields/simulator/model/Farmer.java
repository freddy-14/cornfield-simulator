package org.cornfields.simulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

public class Farmer {

  @NotEmpty private String farmerId;
  @NotEmpty private String alias;
  @Min(0)   private Long   corn;

  public Farmer(String farmerId, String alias, Long corn) {
    this.farmerId = farmerId;
    this.alias    = alias;
    this.corn     = corn;
  }

  @JsonProperty
  public String getFarmerId() {
    return farmerId;
  }

  @JsonProperty
  public String getAlias() {
    return alias;
  }

  @JsonProperty
  public Long countCorn() {
    return corn;
  }

  public void addCorn(long corn) {
    this.corn += corn;
  }

  public void takeCorn(long corn) {
    this.corn -= corn;
  }

}
