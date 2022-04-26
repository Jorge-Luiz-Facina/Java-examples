package org.example.form.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends Exception {
    private HttpStatus httpStatus;
    private Object error;

    public CustomException(HttpStatus httpStatus, Object error) {
        this.httpStatus = httpStatus;
        this.error = error;
    }
}
