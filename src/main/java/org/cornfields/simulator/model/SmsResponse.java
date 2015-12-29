package org.cornfields.simulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class SmsResponse {

  @NotEmpty private String destinationNumber;
  @NotEmpty private String message;

  public SmsResponse() { }

  public SmsResponse(String destinationNumber, String message) {
    this.destinationNumber = destinationNumber;
    this.message           = message;
  }

  @JsonProperty
  public String getDestinationNumber() {
    return destinationNumber;
  }

  @JsonProperty
  public String getMessage() {
    return message;
  }

}
