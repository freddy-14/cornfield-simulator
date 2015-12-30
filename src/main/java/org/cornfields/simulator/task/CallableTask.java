package org.cornfields.simulator.task;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;

import java.io.PrintWriter;
import java.util.concurrent.Callable;

public abstract class CallableTask<T> extends Task implements Callable<T> {

  public CallableTask(String name) {
    super(name);
  }

  @Override
  public void execute(ImmutableMultimap<String, String> immutableMultimap,
                      PrintWriter                       printWriter)
      throws Exception
  {
    call();
  }

}
