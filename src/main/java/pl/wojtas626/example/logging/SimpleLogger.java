package pl.wojtas626.example.logging;

public class SimpleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("[SimpleLogger] " + message);
    }
}
