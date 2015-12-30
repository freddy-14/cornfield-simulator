package org.cornfields.simulator.twilio;

import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.cornfields.simulator.model.SmsMessage;

import java.util.ArrayList;
import java.util.List;

public class SmsSender {

  private final SmsFactory smsFactory;
  private final String     fromNumber;

  public SmsSender(SmsFactory smsFactory, String fromNumber) {
    this.smsFactory = smsFactory;
    this.fromNumber = fromNumber;
  }

  public void send(SmsMessage message) throws TwilioRestException {
    List<NameValuePair> params = new ArrayList<>();

    params.add(new BasicNameValuePair("To",   message.getDestinationNumber()));
    params.add(new BasicNameValuePair("From", fromNumber));
    params.add(new BasicNameValuePair("Body", message.getMessage()));

    smsFactory.create(params);
  }

}
