package tech.itpark.exception;

public class MovieException extends RuntimeException {

    public MovieException() {
        super();
    }

    public MovieException(String message) {
        super(message);
    }

    public MovieException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieException(Throwable cause) {
        super(cause);
    }

    protected MovieException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
