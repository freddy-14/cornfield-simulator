package org.cornfields.simulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class Corn {

  @NotNull  private Integer cornId;
  @NotEmpty private String corn;

  public Corn() { }

  public Corn(Integer cornId, String corn) {
    this.cornId = cornId;
    this.corn   = corn;
  }

  @JsonProperty
  public Integer getCornId() {
    return cornId;
  }

  @JsonProperty
  public String getCorn() {
    return corn;
  }

}
