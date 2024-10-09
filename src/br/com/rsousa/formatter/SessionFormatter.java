package br.com.rsousa.formatter;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.DriverStatus;
import br.com.rsousa.pojo.Event;
import br.com.rsousa.pojo.Session;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SessionFormatter {
    private static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

    public static String format(Session session) {
        StringBuilder resultStr = new StringBuilder();

        for (Driver driver : session.drivers()) {
            resultStr.append(resultLine(driver, session.isSelective())).append("\n");
        }

        Driver driverBestLap = session.drivers().stream()
                .filter(Driver::isBestLap)
                .findFirst()
                .orElse(null);

        if (driverBestLap != null) {
            resultStr.append("\nVolta mais rápida: ").append(driverBestLap.getName()).append(", ");

            resultStr.append(formatSeconds(driverBestLap.getBestLapMilliseconds()));

            if (driverBestLap.isHattrick() && driverBestLap.getPosition() == 1) {
                if (driverBestLap.isGrandChelem()) {
                    resultStr.append("\nHattrick & Grand Chelem para ").append(driverBestLap.getName());
                } else {
                    resultStr.append("\nHattrick para ").append(driverBestLap.getName());
                }
            }
        }

        return resultStr.toString();
    }

    public static String format(List<Session> sessions) {
        StringBuilder resultStr = new StringBuilder();

        for (Session session : sessions) {
            resultStr.append(format(session)).append("\n\n");
        }

        return resultStr.toString();
    }

    public static String toSheets(Session session, String category, String circuit) {
        StringBuilder resultStr = new StringBuilder();

        for (Driver driver: session.drivers()) {
            resultStr.append(driver.positionText()).append(";")
            .append(category).append(";")
            .append(circuit).append(";")
            .append(driver.getName()).append(";")
            .append(driver.getTeamStatistics().trim()).append(";")
            .append(driver.getLicensePoints()).append(";")
            .append(driver.isPolePosition() ? "SIM" : "-").append(";")
            .append(driver.isBestLap() ? "SIM" : "-").append(";")
            .append(driver.isHattrick() ? "SIM" : "-").append(";")
            .append(driver.isGrandChelem() ? "SIM" : "-").append(";")
            .append("-").append(";")
            .append("-").append("\n");
        }

        return resultStr.toString();
    }

    public static String toSheets(List<Session> sessions, String category, String circuit) {
        StringBuilder resultStr = new StringBuilder();

        for (Session session : sessions) {
            resultStr.append(toSheets(session, category, circuit));
        }

        return resultStr.toString();
    }

    public static String toSheetsResults(Event event) {
        StringBuilder resultStr = new StringBuilder();

        List<Session> sessions = new ArrayList<>();
        sessions.add(event.getQualifySession());
        sessions.addAll(event.getRaceSessions());

        var sessionCount = 1;

        for (Session session : sessions) {
            var totalLines = sessionCount != 2 ? 51 : 60;

            for (int i = 0; i < totalLines; i++) {
                Driver driver = null;

                if (i < session.drivers().size()) {
                    driver = session.drivers().get(i);
                } else {
                    resultStr.append("\n");
                    continue;
                }

                resultStr.append(driver.getCarNumber()).append(";");
                resultStr.append(driver.getName()).append(";");
                resultStr.append(driver.getTeam().trim()).append(";");
                resultStr.append(driver.getRaceTimeFormatted()).append(";");
                resultStr.append(driver.getLicensePoints()).append(";");
                resultStr.append(driver.getAverageLap()).append(";");
                resultStr.append(driver.getBestLap()).append(";");
                resultStr.append(driver.getLaps()).append(";");
                resultStr.append(driver.getIncidents()).append("\n");
            }

            if (event.getRaceSessions().indexOf(session) < event.getRaceSessions().size() - 1) {
                resultStr.append(";;").append("RACE ").append(sessionCount).append("\n");
                resultStr.append("#;DRIVER;TEAM;INTERVAL;PEN;AV TIME;BEST LAP;LAPS;INC;PTS").append("\n");
            }

            sessionCount++;
        }

        return resultStr.toString();
    }

    private static String formatSeconds(Long time) {
        String bestLapTime = String.valueOf(time);

        int totalSeconds = 0;

        if (bestLapTime.length() > 3) {
            totalSeconds = Integer.parseInt(bestLapTime.substring(0, bestLapTime.length() - 3));
        }

        String milliseconds = bestLapTime.substring(bestLapTime.length() - 3);

        return LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
                + milliseconds;
    }

    private static String resultLine(Driver driver, boolean isSelective) {
        String team = isSelective ? " " : " (" + driver.getTeam().trim() + "), ";

        String timeFormatted = !driver.getRaceTimeFormatted().trim().isEmpty() ? driver.getRaceTimeFormatted() : "sem tempo";

        if (driver.getStatus() == DriverStatus.FINISHED) {
            return driver.getPosition() + " " + driver.getName().trim() + team + timeFormatted + driver.getStatus().fullText();
        }

        if (driver.getStatus() == DriverStatus.DID_NOT_FINISH) {
            return driver.getPosition() + " " + driver.getName().trim() + team + driver.getStatus().fullText() + " ("+driver.getLaps()+")";
        }

        if (driver.getStatus() == DriverStatus.DISQUALIFIED) {
            return "- " + driver.getName().trim() + team + driver.getStatus().fullText();
        }

        return driver.getPosition() + " " + driver.getName().trim() + team + driver.getStatus().fullText();
    }

}
