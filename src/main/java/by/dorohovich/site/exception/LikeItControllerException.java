package by.dorohovich.site.exception;

/**
 * Created by User on 01.02.2017.
 */
public class LikeItControllerException extends RuntimeException {
    public LikeItControllerException() {
    }

    public LikeItControllerException(String message) {
        super(message);
    }

    public LikeItControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public LikeItControllerException(Throwable cause) {
        super(cause);
    }
}
