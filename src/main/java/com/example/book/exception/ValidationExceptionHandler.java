package com.example.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZonedDateTime;
import java.util.*;

@ControllerAdvice// para gamitin sya ng controller
public class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody // para lumabas sa response
    public HashMap<String, Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String, List<String>> errorList = new HashMap<>();

        for (ObjectError err : allErrors) {
            String fieldError = ((FieldError)err).getField();

            if (errorList.containsKey(fieldError)) {
                errorList.get(fieldError).add(err.getDefaultMessage());
                Collections.sort(errorList.get(fieldError));
            } else {
                List<String> errors = new ArrayList<>();
                errors.add(err.getDefaultMessage());
                errorList.put(fieldError, errors);
            }
        }

        HashMap<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("errors", errorList);
        errorInfo.put("timestamp",ZonedDateTime.now());
        return errorInfo;
    }
}
