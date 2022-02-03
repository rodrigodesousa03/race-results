package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;

import java.io.File;
import java.util.List;

public class EmptyTransformer implements SimulatorTransformer {
    @Override
    public Session processQualify(File file, List<Driver> driverTeams) {
        return new Session(SessionType.QUALIFY);
    }

    @Override
    public Session processRace(File file, List<Driver> driverTeams) {
        return new Session(SessionType.RACE);
    }
}
