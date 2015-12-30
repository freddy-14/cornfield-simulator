package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.Command;
import org.cornfields.simulator.command.RegisterCommand;
import org.cornfields.simulator.command.TravelCommand;
import org.cornfields.simulator.model.Farmer;
import org.cornfields.simulator.model.SmsResponse;

import java.util.Optional;

public class Simulator {

  private final FarmerDatabase farmerDatabase;
  private final CornfieldMap   cornfieldMap;

  public Simulator(FarmerDatabase farmerDatabase, CornfieldMap cornfieldMap) {
    this.farmerDatabase = farmerDatabase;
    this.cornfieldMap   = cornfieldMap;
  }

  public SmsResponse process(Command command) throws CommandNotAllowedException {
    Optional<Farmer> farmer = farmerDatabase.get(command.getFarmerId());
    if (command.getType() != Command.Type.REGISTER && !farmer.isPresent()) {
      throw new CommandNotAllowedException(command, "farmer not registered");
    }

    switch (command.getType()) {
      case REGISTER:
        farmerDatabase.register((RegisterCommand) command);
        return new SmsResponse(command.getFarmerId(), "ok");

      case TRAVEL:
        cornfieldMap.travel((TravelCommand) command);
        return new SmsResponse(command.getFarmerId(), "ok");

      case CORN:
        // todo: return a count of the farmers corn instead
        return new SmsResponse(command.getFarmerId(), "ok");

      case UNREGISTER:
        farmerDatabase.unregister(command.getFarmerId());
        return new SmsResponse(command.getFarmerId(), "goodbye");

      default:
        throw new IllegalArgumentException("I don't know about " + command.getType());
    }
  }

}
