package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.Event;

import java.io.File;
import java.util.List;

public interface SimulatorTransformer {

    Event processEvent(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) throws Exception;

    Session processQualify(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) throws Exception;

    Session processRace(File file, List<Driver> driverTeams, boolean hardDnf) throws Exception;

    Boolean processEvent();

    default boolean isDriver(String driver) {
        return !driver.isEmpty()
                && !driver.contains("Diretor")
                && !driver.contains("Comentarista")
                && !driver.contains("Narrador")
                && !driver.contains("ellevenTV");
    }
}
