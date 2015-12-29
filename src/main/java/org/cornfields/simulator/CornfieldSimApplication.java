package org.cornfields.simulator;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.cornfields.simulator.game.CommandFactory;
import org.cornfields.simulator.health.DumbCheck;
import org.cornfields.simulator.resource.SmsProcessingResource;

public class CornfieldSimApplication extends Application<CornConfig> {

  @Override
  public String getName() {
    return "cornfield-simulator";
  }

  @Override
  public void run(CornConfig config, Environment environment) throws Exception {
    CommandFactory        commands     = new CommandFactory();
    SmsProcessingResource smsProcessor = new SmsProcessingResource(commands);

    environment.healthChecks().register("dumb", new DumbCheck());
    environment.jersey().register(smsProcessor);
  }

  public static void main(String[] args) throws Exception {
    new CornfieldSimApplication().run(args);
  }

}
