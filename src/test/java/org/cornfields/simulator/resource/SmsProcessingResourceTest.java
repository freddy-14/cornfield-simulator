package org.cornfields.simulator.resource;

import org.cornfields.simulator.game.Command;
import org.cornfields.simulator.game.CommandFactory;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.WebApplicationException;
import java.util.Optional;

public class SmsProcessingResourceTest {

  @Test
  public void testErrorOnBadCommand() {
    final CommandFactory        commands    = Mockito.mock(CommandFactory.class);
    final SmsProcessingResource smsResource = new SmsProcessingResource(commands);

    Mockito.when(commands.create(Mockito.any(), Mockito.any()))
           .thenReturn(Optional.<Command>empty());

    try {

      smsResource.process("123", "whatever");
      assert false;

    } catch (WebApplicationException e) {
      assert true;
    }
  }

}
