package org.cornfields.simulator.task;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;

import java.io.PrintWriter;

public abstract class RunnableTask extends Task implements Runnable {

  public RunnableTask(String name) {
    super(name);
  }

  @Override
  public void execute(ImmutableMultimap<String, String> immutableMultimap,
                      PrintWriter                       printWriter)
  {
    run();
  }

}
