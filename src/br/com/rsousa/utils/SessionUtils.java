package br.com.rsousa.utils;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.DriverStatus;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;

public class SessionUtils {
    public static boolean moveUpPosition(Session session, Driver driver) {
        if (driver.getPosition() == 1) {
            return false;
        }

        int driverOriginalPosition = driver.getPosition();
        int futurePosition = driver.getPosition() - 1;

        Driver nextDriver = driverForPosition(session, futurePosition);
        nextDriver.setPosition(driverOriginalPosition);
        driver.setPosition(futurePosition);

        return true;
    }

    public static boolean moveDownPosition(Session session, Driver driver) {
        int driverOriginalPosition = driver.getPosition();
        int futurePosition = driver.getPosition() + 1;

        Driver nextDriver = driverForPosition(session, futurePosition);

        if (nextDriver == null) {
            return false;
        }

        driver.setPosition(futurePosition);
        nextDriver.setPosition(driverOriginalPosition);

        return true;
    }

    public static void moveLastPosition(Session session, Driver driver) {
        if (driver.getStatus() != DriverStatus.LAST_POSITION) {
            driver.setStatus(DriverStatus.LAST_POSITION);

            boolean moveDriver = true;

            while (moveDriver) {
                moveDriver = moveDownPosition(session, driver);
            }
        } else {
            driver.setStatus(DriverStatus.FINISHED);
        }
    }

    public static void disqualify(Session session, Driver driver) {
        if (driver.getStatus() != DriverStatus.DISQUALIFIED) {
            moveLastPosition(session, driver);

            driver.setStatus(DriverStatus.DISQUALIFIED);
        } else {
            driver.setStatus(DriverStatus.FINISHED);
        }
    }

    public static void didNotFinished(Driver driver) {
        if (driver.getStatus() != DriverStatus.DID_NOT_FINISH) {
            driver.setStatus(DriverStatus.DID_NOT_FINISH);
        } else {
            driver.setStatus(DriverStatus.FINISHED);
        }
    }

    private static Driver driverForPosition(Session session, int position) {
        return session.drivers().stream()
                .filter(d -> d.getPosition() == position)
                .findFirst()
                .orElse(null);
    }

    public static Session duplicateRace(Session session) {
        if (session == null) {
            return null;
        }

        Session duplicatedSession = new Session(SessionType.RACE);
        session.drivers().forEach(d -> duplicatedSession.addDriver(duplicate(d)));

        return duplicatedSession;
    }

    private static Driver duplicate(Driver driver) {
        Driver duplicatedDriver = new Driver();
        duplicatedDriver.setPosition(driver.getPosition());
        duplicatedDriver.setBestLap(driver.getBestLap());
        duplicatedDriver.setBestLap(driver.isBestLap());
        duplicatedDriver.setLaps(driver.getLaps());
        duplicatedDriver.setName(driver.getName());
        duplicatedDriver.setPoleposition(driver.isPoleposition());
        duplicatedDriver.setRaceTimeFormatted(driver.getRaceTimeFormatted());
        duplicatedDriver.setTeam(driver.getTeam());
        duplicatedDriver.setGrandChelem(driver.isGrandChelem());
        duplicatedDriver.setBestLapSeconds(driver.getBestLapSeconds());
        duplicatedDriver.setHattrick(driver.isHattrick());
        duplicatedDriver.setLicensePoints(driver.getLicensePoints());
        duplicatedDriver.setStatus(driver.getStatus());

        return duplicatedDriver;
    }
}
