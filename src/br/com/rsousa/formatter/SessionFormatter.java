package br.com.rsousa.formatter;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SessionFormatter {
    private static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

    public static String format(Session session) {
        StringBuilder resultStr = new StringBuilder();

        for (Driver driver : session.drivers()) {
            resultStr.append(resultLine(driver)).append("\n");
        }

        Driver driverBestLap = session.drivers().stream()
                .filter(Driver::isBestLap)
                .findFirst()
                .orElse(null);

        if (driverBestLap != null) {
            resultStr.append("\nVolta mais rápida: ").append(driverBestLap.getName()).append(", ").append(formatSeconds(driverBestLap.getBestLap()));

            if (driverBestLap.isHattrick()) {
                if (driverBestLap.isGrandChelem()) {
                    resultStr.append("\nHattrick & Grand Chelem para ").append(driverBestLap.getName());
                } else {
                    resultStr.append("\nHattrick para ").append(driverBestLap.getName());
                }
            }
        }

        return resultStr.toString();
    }

    private static String formatSeconds(String time) {
        if (time.contains(":")) {
            return time;
        }

        String[] bestLapTime = time.split("\\.");

        int totalSeconds = Integer.parseInt(bestLapTime[0]);
        String milliseconds = bestLapTime[1].substring(0, 3);

        return LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
                + milliseconds;
    }

    private static String resultLine(Driver driver) {
        String timeFormatted = !driver.getRaceTimeFormatted().trim().isEmpty() ? driver.getRaceTimeFormatted() : "sem tempo";

        return driver.getPosition() + " " + driver.getName().trim() + " (" + driver.getTeam().trim() + "), " + timeFormatted;
    }

}
