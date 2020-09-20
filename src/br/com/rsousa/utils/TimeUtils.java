package br.com.rsousa.utils;

public class TimeUtils {
    public static Long toMilliseconds(String time) {
        long milliseconds = 0;

        if (time == null || time.trim().isEmpty()) {
            return milliseconds;
        }

        if (!time.contains(":")) {
            return getMilliseconds(time);
        }

        String[] minutes = time.split(":");

        milliseconds = Long.parseLong(minutes[0]) * 60000;
        milliseconds += getMilliseconds(minutes[1]);

        return milliseconds;
    }

    private static long getMilliseconds(String time) {
        if (time.substring(time.indexOf(".") + 1).length() > 3) {
            time = time.substring(0, time.length() - 1);
        }

        return Long.parseLong(time.replace(".",""));
    }
}
