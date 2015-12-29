package org.cornfields.simulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

public class Farmer {

  @NotEmpty private String farmerId;
  @NotEmpty private String alias;
  @Min(0)   private Long   cornfieldId;
  @Min(0)   private Long   corn;

  public Farmer(String farmerId, String alias, Long cornfieldId, Long corn) {
    this.farmerId    = farmerId;
    this.alias       = alias;
    this.cornfieldId = cornfieldId;
    this.corn        = corn;
  }

  @JsonProperty
  public String getFarmerId() {
    return farmerId;
  }

  @JsonProperty
  public String getAlias() {
    return alias;
  }

  public Long getCornfieldId() {
    return cornfieldId;
  }

  @JsonProperty
  public Long getCorn() {
    return corn;
  }

}
