package org.cornfields.simulator.twilio;

import com.google.common.base.Charsets;
import com.twilio.sdk.verbs.TwiMLResponse;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces({"text/xml", "application/xml"})
public class TwilioResponseWriter implements MessageBodyWriter<TwiMLResponse> {

  @Override
  public boolean isWriteable(Class<?>     aClass,
                             Type         type,
                             Annotation[] annotations,
                             MediaType    mediaType)
  {
    return TwiMLResponse.class.isAssignableFrom(aClass);
  }

  @Override
  public long getSize(TwiMLResponse twiMLResponse,
                      Class<?>      aClass,
                      Type          type,
                      Annotation[]  annotations,
                      MediaType     mediaType)
  {
    return twiMLResponse.toXML().getBytes(Charsets.UTF_8).length;
  }

  @Override
  public void writeTo(TwiMLResponse                  twiMLResponse,
                      Class<?>                       aClass,
                      Type                           type,
                      Annotation[]                   annotations,
                      MediaType                      mediaType,
                      MultivaluedMap<String, Object> multivaluedMap,
                      OutputStream                   outputStream)
      throws IOException, WebApplicationException
  {
    outputStream.write(twiMLResponse.toXML().getBytes(Charsets.UTF_8));
  }

}
