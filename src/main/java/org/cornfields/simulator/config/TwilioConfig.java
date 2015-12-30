package org.cornfields.simulator.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class TwilioConfig {

  @NotEmpty private String accountSid;
  @NotEmpty private String authToken;
  @NotEmpty private String phoneNumber;

  public TwilioConfig() { }

  public TwilioConfig(String accountSid, String authToken, String phoneNumber) {
    this.accountSid  = accountSid;
    this.authToken   = authToken;
    this.phoneNumber = phoneNumber;
  }

  @JsonProperty
  public String getAccountSid() {
    return accountSid;
  }

  @JsonProperty
  public String getAuthToken() {
    return authToken;
  }

  @JsonProperty
  public String getPhoneNumber() {
    return phoneNumber;
  }

}
