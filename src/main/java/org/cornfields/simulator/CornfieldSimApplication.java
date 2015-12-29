package org.cornfields.simulator;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.cornfields.simulator.health.DumbCheck;
import org.cornfields.simulator.resource.CornResource;

public class CornfieldSimApplication extends Application<CornConfig> {

  @Override
  public String getName() {
    return "cornfield-simulator";
  }

  @Override
  public void run(CornConfig config, Environment environment) throws Exception {
    environment.healthChecks().register("dumb", new DumbCheck());
    environment.jersey().register(new CornResource());
  }

  public static void main(String[] args) throws Exception {
    new CornfieldSimApplication().run(args);
  }

}
