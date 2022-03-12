package org.terasoluna.tourreservation.app.api.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private HttpStatus status;

    private String errorCode;

    private MessageSource messageSource;

    private Object[] objects;

    public ApplicationException(HttpStatus status, String errorCode, Object... objects) {
        super();
        this.status = status;
        this.errorCode = errorCode;
        this.objects = objects;
    }

    @Override
    public String getMessage() {
        return messageSource.getMessage(errorCode, objects, Locale.ENGLISH);
    }

}
