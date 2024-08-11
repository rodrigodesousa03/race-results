package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.Event;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class EmptyTransformer implements SimulatorTransformer {

    @Override
    public Event processEvent(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) throws FileNotFoundException, UnsupportedEncodingException {
        return null;
    }

    @Override
    public Session processQualify(File file, List<Driver> driverTeams, boolean hardDnf, boolean isSelective) {
        return new Session(SessionType.QUALIFY, isSelective);
    }

    @Override
    public Session processRace(File file, List<Driver> driverTeams, boolean hardDnf) {
        return new Session(SessionType.RACE, false);
    }

    @Override
    public Boolean processEvent() {
        return false;
    }
}
