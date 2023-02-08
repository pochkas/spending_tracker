package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ExpenseException extends UserFacingException {

    public ExpenseException(String message, Long id) {
        super(message + " Id: " + id);

    }


}
