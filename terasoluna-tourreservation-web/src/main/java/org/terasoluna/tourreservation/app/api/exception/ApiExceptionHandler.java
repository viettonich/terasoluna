package org.terasoluna.tourreservation.app.api.exception;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;
import org.terasoluna.tourreservation.app.api.model.ErrorResponse;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class, RuntimeException.class })
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setMessage(ex.getLocalizedMessage());
        errorResponse.setErrorCode("MSA-xxx");
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class })
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessage("Not Found.");
        errorResponse.setErrorCode("MSA-xxx");
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();

        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, "validation error", "MSA-XXX");
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            error.addFieldError(
                    new FieldError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return handleExceptionInternal(ex, processFieldErrors(fieldErrors), new HttpHeaders(), HttpStatus.BAD_REQUEST,
                request);
    }

    private ErrorResponse processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, "validation error", "MSA-XXX");
        for (org.springframework.validation.FieldError fieldError : fieldErrors) {
            error.addFieldError(
                    new FieldError(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return error;
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        if (body == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage(ex.getLocalizedMessage());
            return new ResponseEntity<>(errorResponse, headers, status);
        } else {
            return new ResponseEntity<>(body, headers, status);
        }

    }

}
