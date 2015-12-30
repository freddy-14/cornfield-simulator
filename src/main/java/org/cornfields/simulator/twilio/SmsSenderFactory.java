package org.cornfields.simulator.twilio;

import com.twilio.sdk.TwilioRestClient;
import org.cornfields.simulator.config.TwilioConfig;

public class SmsSenderFactory {

  private final TwilioConfig     config;
  private final TwilioRestClient client;

  public SmsSenderFactory(TwilioConfig config) {
    this.config = config;
    this.client = new TwilioRestClient(config.getAccountSid(), config.getAuthToken());
  }

  public SmsSender create() {
    return new SmsSender(
        client.getAccount().getSmsFactory(),
        config.getPhoneNumber()
    );
  }

}
