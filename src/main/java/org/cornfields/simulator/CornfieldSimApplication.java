package org.cornfields.simulator;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.cornfields.simulator.game.CommandFactory;
import org.cornfields.simulator.game.CommandProcessor;
import org.cornfields.simulator.health.DumbCheck;
import org.cornfields.simulator.resource.SmsRespondingResource;

public class CornfieldSimApplication extends Application<CornConfig> {

  @Override
  public String getName() {
    return "cornfield-simulator";
  }

  @Override
  public void run(CornConfig config, Environment environment) throws Exception {
    CommandFactory        commandFactory   = new CommandFactory();
    CommandProcessor      commandProcessor = new CommandProcessor();
    SmsRespondingResource smsResponder     = new SmsRespondingResource(commandFactory, commandProcessor);

    environment.healthChecks().register("dumb", new DumbCheck());

    environment.jersey().register(new CornExceptionMappers.CommandNotAllowed());
    environment.jersey().register(smsResponder);
  }

  public static void main(String[] args) throws Exception {
    new CornfieldSimApplication().run(args);
  }

}
