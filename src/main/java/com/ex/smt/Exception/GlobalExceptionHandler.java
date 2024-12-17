package com.ex.smt.Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    // we have a handler methods for specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExResponse> handleResNotFoundEx(ResourceNotFoundException ex){
        logger.error("Error : {}", ex.getMessage());
        ExResponse res = new ExResponse();
        res.setMessage(ex.getMessage());
        res.setStatus(HttpStatus.NOT_FOUND);
        res.setSuccess(false);
        return new ResponseEntity<>(res , HttpStatus.NOT_FOUND);
    }

    

    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ExResponse> handleBadApiReq(BadApiRequest ex){
        logger.error("Error : {}", ex.getMessage());
        ExResponse res = new ExResponse();
        res.setMessage(ex.getMessage());
        res.setStatus(HttpStatus.BAD_REQUEST);
        res.setSuccess(false);
        return new ResponseEntity<>(res , HttpStatus.BAD_REQUEST);
    }
}
