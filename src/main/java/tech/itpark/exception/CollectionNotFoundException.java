package tech.itpark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Collection not found")
public class CollectionNotFoundException extends MovieException {
    public CollectionNotFoundException() {
        super();
    }

    public CollectionNotFoundException(String message) {
        super(message);
    }

    public CollectionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CollectionNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CollectionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
