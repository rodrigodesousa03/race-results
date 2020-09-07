package br.com.rsousa.formatter;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.DriverStatus;
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
            resultStr.append("\nVolta mais rápida: ").append(driverBestLap.getName()).append(", ");

            if (driverBestLap.getBestLap() != null) {
                resultStr.append(formatSeconds(driverBestLap.getBestLap()));
            } else {
                resultStr.append(formatSeconds(driverBestLap.getBestLapSeconds()));
            }

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

    public static String toSheets(Session session, String category, String circuit) {
        StringBuilder resultStr = new StringBuilder();

        for (Driver driver: session.drivers()) {
            resultStr.append(driver.positionText()).append(";")
            .append(category).append(";")
            .append(circuit).append(";")
            .append(driver.getName()).append(";")
            .append(driver.getTeam()).append(";")
            .append(0).append(";")
            .append(driver.isPoleposition() ? "SIM" : "-").append(";")
            .append(driver.isBestLap() ? "SIM" : "-").append(";")
            .append(driver.isHattrick() ? "SIM" : "-").append(";")
            .append(driver.isGrandChelem() ? "SIM" : "-").append(";")
            .append("-").append(";")
            .append("-").append("\n");
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

    private static String formatSeconds(int time) {
        String bestLapTime = String.valueOf(time);

        Integer totalSeconds;

        if (bestLapTime.length() > 3) {
            totalSeconds = Integer.parseInt(bestLapTime.substring(0, bestLapTime.length() - 3));
        } else {
            totalSeconds = 0;
        }

        String milliseconds = bestLapTime.substring(bestLapTime.length() - 3);

        String bestLapTimeFormatted = LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
                + milliseconds;

        return bestLapTimeFormatted;
    }

    private static String resultLine(Driver driver) {
        String timeFormatted = !driver.getRaceTimeFormatted().trim().isEmpty() ? driver.getRaceTimeFormatted() : "sem tempo";

        if (driver.getStatus() == DriverStatus.FINISHED) {
            return driver.getPosition() + " " + driver.getName().trim() + " (" + driver.getTeam().trim() + "), " + timeFormatted + driver.getStatus().fullText();
        }

        if (driver.getStatus() == DriverStatus.DID_NOT_FINISH) {
            return driver.getPosition() + " " + driver.getName().trim() + " (" + driver.getTeam().trim() + "), " + driver.getStatus().fullText() + " ("+driver.getLaps()+")";
        }

        if (driver.getStatus() == DriverStatus.DISQUALIFIED) {
            return "- " + driver.getName().trim() + " (" + driver.getTeam().trim() + "), " + driver.getStatus().fullText();
        }

        return driver.getPosition() + " " + driver.getName().trim() + " (" + driver.getTeam().trim() + "), " + driver.getStatus().fullText();
    }

}
