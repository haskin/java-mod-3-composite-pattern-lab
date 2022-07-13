package logger;

public class Logger {
    private static final String MESSAGE_TEMPLATE = "%s::%s";

    private int lineNumber = 1;

    private static Logger logger;

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void log(String message) {
        System.out.println(String.format(MESSAGE_TEMPLATE, lineNumber++, message));
    }
}
