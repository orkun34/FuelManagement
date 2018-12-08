package com.lombardi.fuelmng.common.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FuelMngExceptionController {


    @ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> handleMissingElement(MissingElementException exception) {

        ExceptionHandlerResponse error = new ExceptionHandlerResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorCode(HttpStatus.UNPROCESSABLE_ENTITY.toString());

        return new ResponseEntity(error,HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> handleFileParsing(FileParsingException exception) {

        ExceptionHandlerResponse error = new ExceptionHandlerResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorCode(HttpStatus.BAD_REQUEST.toString());

        return new ResponseEntity(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> handleJsonParsing(JsonParsingException exception) {

        ExceptionHandlerResponse error = new ExceptionHandlerResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorCode(HttpStatus.UNPROCESSABLE_ENTITY.toString());

        return new ResponseEntity(error,HttpStatus.UNPROCESSABLE_ENTITY);
    }


}
