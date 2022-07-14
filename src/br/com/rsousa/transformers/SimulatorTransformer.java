package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.Session;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface SimulatorTransformer {
    Session processQualify(File file, List<Driver> driverTeams, boolean dnfRigido, boolean isSeletiva) throws FileNotFoundException, UnsupportedEncodingException;

    Session processRace(File file, List<Driver> driverTeams, boolean dnfRigido) throws FileNotFoundException;

    default boolean isDriver(String driver) {
        return !driver.isEmpty()
                && !driver.contains("Diretor")
                && !driver.contains("Comentarista")
                && !driver.contains("Narrador")
                && !driver.contains("ellevenTV");
    }
}
