package tech.itpark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Genre not found")
public class GenreNotFoundException extends MovieException {

    public GenreNotFoundException() {
        super();
    }

    public GenreNotFoundException(String message) {
        super(message);
    }

    public GenreNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenreNotFoundException(Throwable cause) {
        super(cause);
    }

    protected GenreNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
