package org.cornfields.simulator.game;

import org.cornfields.simulator.CommandNotAllowedException;
import org.cornfields.simulator.command.TravelCommand;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class CornfieldMap {

  private final Map<Long, Cornfield> cornfields = new ConcurrentHashMap<>();
  private final Object               txnLock    = new Object();

  public CornfieldMap() {
    LongStream.range(1, 11)
              .forEach(l -> cornfields.put(l, new Cornfield()));
  }

  private Optional<Cornfield> findFieldFarmedBy(String farmerId) {
    return cornfields.keySet()
                     .stream()
                     .map(cornfields::get)
                     .filter(cornfield -> cornfield.getFarmers().contains(farmerId))
                     .findAny();
  }

  public List<Long> getCornfieldIds() {
    return cornfields.keySet().stream().collect(Collectors.toList());
  }

  public void travel(TravelCommand travel) throws CommandNotAllowedException {
    if (travel.getCornfieldId() < 1 || travel.getCornfieldId() > 10) {
      throw new CommandNotAllowedException(travel, "unknown cornfield, valid numbers are 1-10");
    }

    synchronized (txnLock) {
      Optional<Cornfield> fromField = findFieldFarmedBy(travel.getFarmerId());

      if (fromField.isPresent()) {
        fromField.get().removeFarmer(travel.getFarmerId());
      }

      cornfields.get(travel.getCornfieldId())
                .addFarmer(travel.getFarmerId());
    }
  }

  public List<String> getFarmersOn(Long cornfieldId) {
    Optional<Cornfield> cornfield = Optional.ofNullable(cornfields.get(cornfieldId));

    if (!cornfield.isPresent()) {
      throw new IllegalArgumentException("unknown cornfield id: " + cornfield);
    } else {
      return cornfield.get().getFarmers();
    }
  }

  private static class Cornfield {
    private final List<String> farmers = new LinkedList<>();

    protected void addFarmer(String farmerId) {
      farmers.add(farmerId);
    }

    protected void removeFarmer(String farmerId) {
      farmers.remove(farmerId);
    }

    protected List<String> getFarmers() {
      return farmers;
    }
  }

}
