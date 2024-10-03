package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.acc.LeaderBoardLine;
import br.com.rsousa.pojo.assetto.Result;
import br.com.rsousa.pojo.iracing.csv.DriverSession;
import br.com.rsousa.utils.TimeUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DriverTransformer {
    public static Driver toDriver(br.com.rsousa.pojo.ams.Driver amsDriver, int position, String raceTimeFormatted, Double driverTotalTime, List<Driver> drivers) {
        Driver driver = new Driver();
        driver.setName(amsDriver.getName());
        driver.setBestLap(amsDriver.getBestLapTime());
        driver.setBestLapMilliseconds(TimeUtils.toMilliseconds(amsDriver.getBestLapTime()));
        driver.setRaceTimeMilliseconds(TimeUtils.toMilliseconds(amsDriver.getFinishTime()));
        driver.setLaps(amsDriver.getLaps());
        driver.setPosition(position);
        driver.setRaceTimeFormatted(raceTimeFormatted);
        driver.setTeam(getTeamName(amsDriver.getName(), amsDriver.getTeamName(), drivers));
        driver.setTeamStatistics(getTeamStatisticsName(amsDriver.getName(), amsDriver.getTeamName(), drivers));
        driver.setDriverTotalTime(driverTotalTime);

        return driver;
    }

    public static Driver toDriver(DriverSession iRacingDriver, int position, String raceTimeFormatted, List<Driver> drivers) {
        Driver driver = new Driver();
        driver.setName(getDriverName(iRacingDriver, drivers));
        driver.setBestLap(iRacingDriver.getFastLap());
        driver.setLaps(iRacingDriver.getCompletedLaps());
        driver.setPosition(position);
        driver.setRaceTimeFormatted(raceTimeFormatted);
        driver.setBestLapMilliseconds(TimeUtils.toMilliseconds(iRacingDriver.getFastLap()));
        driver.setTeam(getTeamName(iRacingDriver, drivers));
        driver.setTeamStatistics(getTeamStatisticsName(iRacingDriver, drivers));

        return driver;
    }

    public static Driver toDriver(Result assettoDriver, int position, int laps, String raceTimeFormatted, List<Driver> drivers) {
        Driver driver = new Driver();
        driver.setName(assettoDriver.getDriverName());
        driver.setBestLapMilliseconds(assettoDriver.getBestLap());
        driver.setRaceTimeMilliseconds(assettoDriver.getTotalTime());
        driver.setLaps(laps);
        driver.setPosition(position);
        driver.setRaceTimeFormatted(raceTimeFormatted);
        driver.setTeam(getTeamName(assettoDriver.getDriverName(), "Independente", drivers));
        driver.setTeamStatistics(getTeamStatisticsName(assettoDriver.getDriverName(), "Independente", drivers));

        return driver;
    }

    public static Driver toDriver(LeaderBoardLine assettoDriver, int position, int laps, String raceTimeFormatted, List<Driver> drivers) {
        Driver driver = new Driver();

        String driverName = assettoDriver.getCurrentDriver().getFirstName() + " " + assettoDriver.getCurrentDriver().getLastName();

        driver.setName(driverName);
        driver.setBestLapMilliseconds(assettoDriver.getTiming().getBestLap());
        driver.setRaceTimeMilliseconds(assettoDriver.getTiming().getTotalTime());
        driver.setLaps(laps);
        driver.setPosition(position);
        driver.setRaceTimeFormatted(raceTimeFormatted);
        driver.setTeam(getTeamName(driverName, "Independente", drivers));
        driver.setTeamStatistics(getTeamStatisticsName(driverName, "Independente", drivers));

        return driver;
    }

    public static Driver toDriver(br.com.rsousa.pojo.ams2.Result result, int position, String raceTimeFormatted, List<Driver> drivers) {
        Driver driver = new Driver();
        driver.setName(result.getName().replaceAll("\\[.*?\\]", "").trim());
        driver.setBestLapMilliseconds(Long.valueOf(result.getAttributes().getFastestLapTime()));
        driver.setRaceTimeMilliseconds(Long.valueOf(result.getAttributes().getTotalTime()));
        driver.setLaps(result.getAttributes().getLap());
        driver.setPosition(position);
        driver.setRaceTimeFormatted(raceTimeFormatted);
        driver.setTeam(getTeamName(result.getName(), "Independente", drivers));
        driver.setTeamStatistics(getTeamStatisticsName(result.getName(), "Independente", drivers));

        return driver;
    }

    public static Driver toDriver(br.com.rsousa.pojo.ams2.Event event, String driverName, String raceTimeFormatted, List<Driver> drivers) {
        Driver driver = new Driver();
        driver.setName(driverName.replaceAll("\\[.*?\\]", "").trim());
        driver.setRaceTimeMilliseconds(Long.valueOf(event.getTime()));
        driver.setPosition(0);
        driver.setRaceTimeFormatted(raceTimeFormatted);
        driver.setTeam(getTeamName(driverName, "Independente", drivers));
        driver.setTeamStatistics(getTeamStatisticsName(driverName, "Independente", drivers));

        return driver;
    }

    public static Driver toDriver(br.com.rsousa.pojo.iracing.json.Result iracingDriver, Long laps, String raceTimeFormatted, List<Driver> drivers) {
        Driver driver = new Driver();
        driver.setName(getDriverName(iracingDriver, drivers));
        driver.setBestLapMilliseconds(iracingDriver.getBestLapTime()/10);
        driver.setRaceTimeMilliseconds(iracingDriver.getLapsComplete() * iracingDriver.getAverageLap());
        driver.setLaps(laps.intValue());
        driver.setPosition(iracingDriver.getFinishPositionInClass()+1);
        driver.setRaceTimeFormatted(raceTimeFormatted);
        driver.setTeam(getTeamName(iracingDriver, drivers));
        driver.setTeamStatistics(getTeamStatisticsName(iracingDriver, drivers));
        driver.setCarNumber(Integer.parseInt(iracingDriver.getLivery().getCarNumber()));
        driver.setIncidents(iracingDriver.getIncidents());
        driver.setAverageLap("-");
        driver.setBestLap("-");
        driver.setCarClassId(iracingDriver.getCarClassId());

        if (iracingDriver.getAverageLap() > 0) {
            driver.setAverageLap(formatMilliseconds(iracingDriver.getAverageLap()));
        }

        if (iracingDriver.getBestLapTime() > 0) {
            driver.setBestLap(formatMilliseconds(iracingDriver.getBestLapTime()));
        }

        return driver;
    }

    private static String getTeamName(String driverName, String teamName, List<Driver> drivers) {
        return drivers.stream().filter(d -> driverName.trim().equals(d.getName()))
                .map(Driver::getTeam)
                .findAny()
                .orElse(teamName);
    }

    private static String getTeamStatisticsName(String driverName, String teamName, List<Driver> drivers) {
        return drivers.stream().filter(d -> driverName.trim().equals(d.getName()))
                .map(Driver::getTeamStatistics)
                .findAny()
                .orElse(teamName);
    }

    private static String getDriverName(br.com.rsousa.pojo.iracing.json.Result driver, List<Driver> drivers) {
        return drivers.stream().filter(d -> driver.getCustId().toString().equals(d.getId()))
                .map(Driver::getName)
                .findAny()
                .orElse(driver.getDisplayName());
    }

    private static String getDriverName(DriverSession driver, List<Driver> drivers) {
        return drivers.stream().filter(d -> driver.getId().equals(d.getId()))
                .map(Driver::getName)
                .findAny()
                .orElse(driver.getName());
    }


    private static String getTeamName(DriverSession driver, List<Driver> drivers) {
        return drivers.stream().filter(d -> driver.getId().equals(d.getId()))
                .map(Driver::getTeam)
                .findAny()
                .orElse("Independente");
    }

    private static String getTeamStatisticsName(DriverSession driver, List<Driver> drivers) {
        return drivers.stream().filter(d -> driver.getId().equals(d.getId()))
                .map(Driver::getTeamStatistics)
                .findAny()
                .orElse("Independente");
    }

    private static String getTeamName(br.com.rsousa.pojo.iracing.json.Result driver, List<Driver> drivers) {
        return drivers.stream().filter(d -> driver.getCustId().toString().equals(d.getId()))
                .map(Driver::getTeam)
                .findAny()
                .orElse("Independente");
    }

    private static String getTeamStatisticsName(br.com.rsousa.pojo.iracing.json.Result driver, List<Driver> drivers) {
        return drivers.stream().filter(d -> driver.getCustId().toString().equals(d.getId()))
                .map(Driver::getTeamStatistics)
                .findAny()
                .orElse("Independente");
    }

    private static String formatMilliseconds(long milliseconds) {
        milliseconds = milliseconds/10;

        int totalSeconds = (int) (milliseconds / 1000);
        int millis = (int) (milliseconds % 1000);

        LocalTime time = LocalTime.MIN.plusSeconds(totalSeconds).plusNanos(millis * 1_000_000);

        return time.format(DateTimeFormatter.ofPattern("m:ss.SSS"));
    }
}
