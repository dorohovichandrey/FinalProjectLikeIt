package by.dorohovich.site.exception;

/**
 * Created by User on 18.11.2016.
 */
public class NoSuchElemInEnumException extends RuntimeException {
    public NoSuchElemInEnumException() {
    }

    public NoSuchElemInEnumException(String message) {
        super(message);
    }

    public NoSuchElemInEnumException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchElemInEnumException(Throwable cause) {
        super(cause);
    }


}
