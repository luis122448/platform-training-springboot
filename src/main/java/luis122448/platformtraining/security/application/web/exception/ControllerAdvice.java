package luis122448.platformtraining.security.application.web.exception;

import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(Exception e){
        ApiResponseObject<?> apiResponseObject = new ApiResponseObject<>(-2,"ERROR UNKNOWN", e.getMessage(), Optional.empty());
        return new ResponseEntity<>(apiResponseObject, HttpStatus.OK);
    }

    @ExceptionHandler(value = SecurityException.class)
    public ResponseEntity<?> securityExceptionHandler(SecurityException e){
        ApiResponseObject<?> apiResponseObject = new ApiResponseObject<>(-2,e.getMessage(), e.getMessage(), Optional.empty());
        return new ResponseEntity<>(apiResponseObject, HttpStatus.OK);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<?> noSuchElementException(NoSuchElementException e){
        ApiResponseObject<?> apiResponseObject = new ApiResponseObject<>(-2,e.getMessage(), e.getMessage(), Optional.empty());
        return new ResponseEntity<>(apiResponseObject, HttpStatus.OK);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFoundExceptionHandler(SecurityException e){
        ApiResponseObject<?> apiResponseObject = new ApiResponseObject<>(-2,e.getMessage(), e.getCause().getMessage(), Optional.empty());
        return new ResponseEntity<>(apiResponseObject, HttpStatus.OK);
    }

    @ExceptionHandler(value = GenericObjectServiceException.class)
    public ResponseEntity<?> genericObjectServiceException(GenericObjectServiceException e){
        ApiResponseObject<?> apiResponseObject = new ApiResponseObject<>(-2,e.getMessage(),e.getLogMessage(),Optional.empty());
        return new ResponseEntity<>(apiResponseObject, HttpStatus.OK);
    }

    @ExceptionHandler(value = GenericListServiceException.class)
    public ResponseEntity<?> genericListServiceException(GenericListServiceException e){
        ApiResponseList<?> apiResponseList = new ApiResponseList<>(-2,e.getMessage(),e.getMessage(),Optional.empty());
        return new ResponseEntity<>(apiResponseList, HttpStatus.OK);
    }


}
