package tech.itpark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Movie not found")
public class MovieNotFoundException extends MovieException {
    public MovieNotFoundException(String message) {
    }

    public MovieNotFoundException() {
    }

    public MovieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MovieNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
