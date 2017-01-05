package by.dorohovich.site.exception;

/**
 * Created by User on 25.12.2016.
 */
public class ConnectionProducerException extends Exception {
    public ConnectionProducerException() {
    }

    public ConnectionProducerException(String message) {
        super(message);
    }

    public ConnectionProducerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionProducerException(Throwable cause) {
        super(cause);
    }
}
