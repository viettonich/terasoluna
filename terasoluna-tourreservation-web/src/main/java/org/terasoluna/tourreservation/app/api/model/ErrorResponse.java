package org.terasoluna.tourreservation.app.api.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {

    private HttpStatus status;

    private String errorCode;

    private String message;

    @JsonInclude(Include.NON_NULL)
    private List<FieldError> fieldErrors = new ArrayList<>();

    public ErrorResponse(HttpStatus status, String errorCode, String message) {
        super();
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }

    public void addFieldError(FieldError fieldError) {
        fieldErrors.add(fieldError);
    }

}

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//class FieldError {
//    private String objectName;
//    private String fileName;
//    private String message;
//}