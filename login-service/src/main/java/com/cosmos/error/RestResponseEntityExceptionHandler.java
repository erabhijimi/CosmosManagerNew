package com.cosmos.error;

import com.cosmos.pojo.ErrorEntity;
import com.cosmos.pojo.JWTResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(PasswordError.class)
    public ResponseEntity<ErrorEntity> passwordNotMatching(PasswordError exception, WebRequest request){
        ErrorEntity errorEntity = new ErrorEntity(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorEntity);
    }

    @ExceptionHandler(UserAlreadyAvailableException.class)
    public ResponseEntity<ErrorEntity> userAlreadyAvailableException(UserAlreadyAvailableException exception, WebRequest request){
        ErrorEntity errorEntity = new ErrorEntity(HttpStatus.BAD_REQUEST,exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorEntity);
    }
    @ExceptionHandler(TransactionError.class)
    public ResponseEntity<ErrorEntity> transactionError(TransactionError exception, WebRequest request){
        ErrorEntity errorEntity = new ErrorEntity(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorEntity);
    }
}
