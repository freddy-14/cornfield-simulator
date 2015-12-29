package org.cornfields.simulator.resource;

import org.cornfields.simulator.game.Command;
import org.cornfields.simulator.game.CommandFactory;
import org.cornfields.simulator.game.CommandNotAllowedException;
import org.cornfields.simulator.game.CommandProcessor;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.WebApplicationException;
import java.util.Optional;

public class SmsRespondingResourceTest {

  @Test
  public void testErrorOnBadCommand() {
    final CommandFactory        factory      = Mockito.mock(CommandFactory.class);
    final CommandProcessor      processor    = Mockito.mock(CommandProcessor.class);
    final SmsRespondingResource smsResponder = new SmsRespondingResource(factory, processor);

    Mockito.when(factory.create(Mockito.any(), Mockito.any()))
           .thenReturn(Optional.<Command>empty());

    try {

      smsResponder.respond("123", "whatever");
      assert false;

    } catch (WebApplicationException e) {
      assert true;
    } catch (CommandNotAllowedException e) {
      assert false;
    }
  }

}
