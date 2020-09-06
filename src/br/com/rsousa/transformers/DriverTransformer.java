package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.acc.LeaderBoardLine;
import br.com.rsousa.pojo.assetto.Result;
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
        driver.setName(getDriverName(iRacingDriver, drivers));
        driver.setBestLap(iRacingDriver.getFastLap());
        driver.setLaps(iRacingDriver.getCompletedLaps());
        driver.setPosition(position);
        driver.setRaceTimeFormatted(raceTimeFormatted);
        driver.setTeam(getTeamName(iRacingDriver.getName(), "Independente", drivers));

        return driver;
    }

    public static Driver toDriver(Result assettoDriver, int position, int laps, String raceTimeFormatted, List<Driver> drivers) {
        Driver driver = new Driver();
        driver.setName(assettoDriver.getDriverName());
        driver.setBestLapSeconds(assettoDriver.getBestLap());
        driver.setLaps(laps);
        driver.setPosition(position);
        driver.setRaceTimeFormatted(raceTimeFormatted);
        driver.setTeam(getTeamName(assettoDriver.getDriverName(), "Independente", drivers));

        return driver;
    }

    public static Driver toDriver(LeaderBoardLine assettoDriver, int position, int laps, String raceTimeFormatted, List<Driver> drivers) {
        Driver driver = new Driver();

        String driverName = assettoDriver.getCurrentDriver().getFirstName() + " " + assettoDriver.getCurrentDriver().getLastName();

        driver.setName(driverName);
        driver.setBestLapSeconds(assettoDriver.getTiming().getBestLap());
        driver.setLaps(laps);
        driver.setPosition(position);
        driver.setRaceTimeFormatted(raceTimeFormatted);
        driver.setTeam(getTeamName(driverName, "Independente", drivers));

        return driver;
    }

    private static String getTeamName(String driverName, String teamName, List<Driver> drivers) {
        return drivers.stream().filter(d -> driverName.trim().equals(d.getName()))
                .map(Driver::getTeam)
                .findAny()
                .orElse(teamName);
    }

    private static String getDriverName(DriverSession driver, List<Driver> drivers) {
        return drivers.stream().filter(d -> driver.getId().equals(d.getId()))
                .map(Driver::getName)
                .findAny()
                .orElse(driver.getName());
    }
}
