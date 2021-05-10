package tech.itpark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Language not found")
public class LanguageNotFoundException extends MovieException {

    public LanguageNotFoundException(String message) {
        super(message);
    }

    public LanguageNotFoundException() {
    }

    public LanguageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public LanguageNotFoundException(Throwable cause) {
        super(cause);
    }

    protected LanguageNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
