package org.cornfields.simulator;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.cornfields.simulator.command.CommandFactory;
import org.cornfields.simulator.game.CornfieldMap;
import org.cornfields.simulator.game.FarmerDatabase;
import org.cornfields.simulator.game.Simulator;
import org.cornfields.simulator.health.DumbCheck;
import org.cornfields.simulator.resource.SmsRespondingResource;
import org.cornfields.simulator.task.CornGrowTask;
import org.cornfields.simulator.task.CornHarvestTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CornApplication extends Application<CornConfig> {

  private static final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

  @Override
  public String getName() {
    return "cornfield-simulator";
  }

  @Override
  public void run(CornConfig config, Environment environment) throws Exception {
    FarmerDatabase farmers    = new FarmerDatabase();
    CornfieldMap   cornfields = new CornfieldMap();
    Simulator      simulator  = new Simulator(farmers, cornfields);

    farmers.addListener(cornfields);

    CommandFactory        commandFactory = new CommandFactory();
    SmsRespondingResource smsResponder   = new SmsRespondingResource(commandFactory, simulator);

    environment.healthChecks().register("dumb", new DumbCheck());
    environment.jersey().register(new CornExceptionMappers.CommandNotAllowed());
    environment.jersey().register(smsResponder);

    CornGrowTask    growTask    = new CornGrowTask(cornfields);
    CornHarvestTask harvestTask = new CornHarvestTask(farmers, cornfields);

    environment.admin().addTask(growTask);
    environment.admin().addTask(harvestTask);

    executor.scheduleAtFixedRate(
        growTask, 0, config.getCornGrowIntervalMinutes(), TimeUnit.MINUTES
    );
    executor.scheduleAtFixedRate(
        harvestTask, 1, config.getCornHarvestIntervalMinutes(), TimeUnit.MINUTES
    );
  }

  public static void main(String[] args) throws Exception {
    new CornApplication().run(args);
  }

}
