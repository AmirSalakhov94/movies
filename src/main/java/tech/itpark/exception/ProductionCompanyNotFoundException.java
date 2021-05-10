package tech.itpark.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Company not found")
public class ProductionCompanyNotFoundException extends MovieException {
    public ProductionCompanyNotFoundException(String message) {
    }

    public ProductionCompanyNotFoundException() {
        super();
    }

    public ProductionCompanyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductionCompanyNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ProductionCompanyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
