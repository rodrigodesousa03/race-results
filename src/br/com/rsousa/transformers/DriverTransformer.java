package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.iracing.DriverSession;

import java.util.List;

public class DriverTransformer {
    public static Driver toDriver(br.com.rsousa.pojo.ams.Driver amsDriver, int position, String raceTimeFormatted, List<Driver> drivers) {
        Driver driver = new Driver();
        driver.setName(amsDriver.getName());
        driver.setBestLap(amsDriver.getBestLapTime());
        driver.setLaps(amsDriver.getLaps());
        driver.setPosition(position);
        driver.setRaceTimeFormatted(raceTimeFormatted);
        driver.setTeam(getTeamName(amsDriver.getName(), amsDriver.getTeamName(), drivers));

        return driver;
    }

    public static Driver toDriver(DriverSession iRacingDriver, int position, String raceTimeFormatted, List<Driver> drivers) {
        Driver driver = new Driver();
        driver.setName(iRacingDriver.getName());
        driver.setBestLap(iRacingDriver.getFastLap());
        driver.setLaps(iRacingDriver.getCompletedLaps());
        driver.setPosition(position);
        driver.setRaceTimeFormatted(raceTimeFormatted);
        driver.setTeam(getTeamName(iRacingDriver.getName(), "Independente", drivers));

        return driver;
    }

    private static String getTeamName(String driverName, String teamName, List<Driver> drivers) {
        return drivers.stream().filter(d -> driverName.trim().equals(d.getName()))
                .map(d -> d.getTeam())
                .findAny()
                .orElse(teamName);
    }
}
