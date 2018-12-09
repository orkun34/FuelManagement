package com.lombardi.fuelmng.common.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class FuelMngExceptionController extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public final ResponseEntity<?> handleFileParsing(FileParsingException exception) {

        ExceptionHandlerResponse error = new ExceptionHandlerResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorCode(HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<?> internalException(InternalException exception) {

        ExceptionHandlerResponse error = new ExceptionHandlerResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<?> invalidMonthException(InvalidMonthException exception) {

        ExceptionHandlerResponse error = new ExceptionHandlerResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorCode(HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<ExceptionHandlerResponse> handleJsonParsing(JsonParsingException exception) {

        ExceptionHandlerResponse error = new ExceptionHandlerResponse();
        error.setErrorMessage("Not parsable object");
        error.setErrorCode(HttpStatus.UNPROCESSABLE_ENTITY.toString());

        return new ResponseEntity(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(BeanValidationException.class)
    public final ResponseEntity<Object> handleBeanValidation(BeanValidationException ex) {
        ExceptionHandlerResponse error = new ExceptionHandlerResponse();
        error.setErrorMessage(ex.getMessage());
        error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.toString());

        return new ResponseEntity(error, HttpStatus.NOT_ACCEPTABLE);
    }


}
