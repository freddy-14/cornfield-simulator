package org.cornfields.simulator.twilio;

import com.twilio.sdk.verbs.Message;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.TwiMLResponse;
import org.cornfields.simulator.model.SmsMessage;

public class TwilioTranslator {

  public TwiMLResponse translate(SmsMessage message) {
    TwiMLResponse response = new TwiMLResponse();
    Message       body     = new Message(message.getMessage());

    try {

      response.append(body);

    } catch (TwiMLException e) {
      return null;
    }

    return response;
  }

}
