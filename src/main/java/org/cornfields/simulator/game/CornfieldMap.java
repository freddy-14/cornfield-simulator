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
              .forEach(l -> cornfields.put(l, new Cornfield(l)));
  }

  private Optional<Cornfield> findFieldFarmedBy(String farmerId) {
    return cornfields.keySet()
                     .stream()
                     .map(cornfields::get)
                     .filter(cornfield -> cornfield.farmers.contains(farmerId))
                     .findAny();
  }

  public Cornfield getCornfield(Long cornfieldId) {
    return cornfields.get(cornfieldId);
  }

  public List<Cornfield> getCornfields() {
    return cornfields.keySet()
                     .stream()
                     .map(cornfields::get)
                     .collect(Collectors.toList());
  }

  public void travel(TravelCommand travel) throws CommandNotAllowedException {
    if (travel.getCornfieldId() < 1 || travel.getCornfieldId() > 10) {
      throw new CommandNotAllowedException(travel, "unknown cornfield, valid numbers are 1-10");
    }

    synchronized (txnLock) {
      Optional<Cornfield> fromField = findFieldFarmedBy(travel.getFarmerId());

      if (fromField.isPresent()) {
        fromField.get().farmers.remove(travel.getFarmerId());
      }

      cornfields.get(travel.getCornfieldId())
                .farmers.add(travel.getFarmerId());
    }
  }

  public static class Cornfield {

    private final List<String> farmers = new LinkedList<>();
    private final Long id;
    private long corn;

    protected Cornfield(Long id) {
      this.id = id;
    }

    public Long getId() {
      return id;
    }

    public List<String> getFarmers() {
      return farmers.stream().collect(Collectors.toList());
    }

    public long countCorn() {
      return corn;
    }

    public void growCorn(long growCount) {
      corn += growCount;
    }

    public long harvestCorn() {
      long harvestCount = corn;
      corn = 0l;
      return harvestCount;
    }

  }
}
