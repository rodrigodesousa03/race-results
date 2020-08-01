package br.com.rsousa.formatter;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.Session;
import br.com.rsousa.pojo.SessionType;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SessionFormatter {
    private static final DateTimeFormatter MINUTE_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

    public static String format(Session session) {
        String resultStr = "";

        for (Driver driver : session.drivers()) {
            resultStr += resultLine(driver) + "\n";
        }

        if (session.type() == SessionType.QUALIFY) {
            return resultStr;
        }

        Driver driverBestLap = session.drivers().stream()
                .filter(d -> d.isBestLap())
                .findFirst()
                .orElse(null);

        resultStr += "\nVolta mais rápida: " + driverBestLap.getName() + ", "
                + formatSeconds(driverBestLap.getBestLap());

        if (driverBestLap.isHattrick()) {
            if (driverBestLap.isGrandChelem()) {
                resultStr += "\nHattrick & Grand Chelem para " + driverBestLap.getName();
            } else {
                resultStr += "\nHattrick para " + driverBestLap.getName();
            }
        }

        return resultStr;
    }

    private static String formatSeconds(String time) {
        String[] bestLapTime = time.split("\\.");

        Integer totalSeconds = Integer.parseInt(bestLapTime[0]);
        String milliseconds = bestLapTime[1].substring(0, 3);

        String bestLapTimeFormatted = LocalTime.MIN.plusSeconds(totalSeconds).format(MINUTE_FORMATTER) + "."
                + milliseconds;
        return bestLapTimeFormatted;
    }

    private static String resultLine(Driver driver) {
        return driver.getPosition() + " " + driver.getName().trim() + " (" + driver.getTeam().trim() + "), " + driver.getRaceTimeFormatted();
    }

}
