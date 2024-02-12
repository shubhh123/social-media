package com.social.spring.socialmedia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/*
Place where all your
exceptions will be handled
 */
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ChatNotFoundException.class)
    public ResponseEntity<ErrorDetails> chatNotFoundExceptionHandler(ChatNotFoundException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), webRequest.getDescription(false), LocalDateTime.now()); //getDescription false because we do not want the long trace message.
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorDetails> commentNotFoundExceptionHandler(CommentNotFoundException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), webRequest.getDescription(false), LocalDateTime.now()); //getDescription false because we do not want the long trace message.
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorDetails> postNotFoundExceptionHandler(PostNotFoundException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), webRequest.getDescription(false), LocalDateTime.now()); //getDescription false because we do not want the long trace message.
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReelNotFoundForUserException.class)
    public ResponseEntity<ErrorDetails> reelNotFoundExceptionHandler(ReelNotFoundForUserException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), webRequest.getDescription(false), LocalDateTime.now()); //getDescription false because we do not want the long trace message.
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandler(UserException e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), webRequest.getDescription(false), LocalDateTime.now()); //getDescription false because we do not want the long trace message.
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> otherExceptionHandler(Exception e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), webRequest.getDescription(false), LocalDateTime.now()); //getDescription false because we do not want the long trace message.
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
