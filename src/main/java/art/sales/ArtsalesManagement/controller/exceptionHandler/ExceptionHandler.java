package art.sales.ArtsalesManagement.controller.exceptionHandler;

import art.sales.ArtsalesManagement.controller.ApiError;

import art.sales.ArtsalesManagement.exception.UserCannotBeFoundException;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(UserCannotBeFoundException.class)
    public ResponseEntity<?> handleCustomerAlreadyExistException(UserCannotBeFoundException userCannotBeFoundException){
        ApiError apiError = ApiError.builder()
                .message(userCannotBeFoundException.getMessage())
                .successful(false)
                .statusCode(userCannotBeFoundException.getStatusCode())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatusCode()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UnirestException.class)
    public ResponseEntity<?> handleUnirestException( UnirestException unirestException){
        ApiError apiError = ApiError.builder()
                .message(unirestException.getMessage())
                .successful(false)
                .statusCode(400)
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatusCode()));
    }
}
