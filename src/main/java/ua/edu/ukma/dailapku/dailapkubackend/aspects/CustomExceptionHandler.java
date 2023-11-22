package ua.edu.ukma.dailapku.dailapkubackend.aspects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import ua.edu.ukma.dailapku.dailapkubackend.exceptions.ExceptionMessage;

import static ua.edu.ukma.dailapku.dailapkubackend.utils.Utils.getCurrentTime;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) throws JsonProcessingException {
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), uri, getCurrentTime());
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(exceptionMessage), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(org.springframework.security.access.AccessDeniedException ex, WebRequest request) throws JsonProcessingException {
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        String message = ex.getMessage() + ", Probably user doesn't have enough permissions to call the resource";
        ExceptionMessage exceptionMessage = new ExceptionMessage(message, uri, getCurrentTime());
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(exceptionMessage), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherException(Exception ex, WebRequest request) throws JsonProcessingException {
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), uri, getCurrentTime());
//        ex.printStackTrace();
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(exceptionMessage), HttpStatus.BAD_REQUEST);
    }

}
