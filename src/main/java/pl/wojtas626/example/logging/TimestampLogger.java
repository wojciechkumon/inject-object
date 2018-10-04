package pl.wojtas626.example.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampLogger implements Logger {
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS");

    @Override
    public void log(String message) {
        System.out.println("[TimestampLogger] " + DATE_FORMAT.format(new Date()) + " " + message);
    }
}
