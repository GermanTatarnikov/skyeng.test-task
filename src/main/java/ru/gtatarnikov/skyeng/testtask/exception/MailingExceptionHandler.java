package ru.gtatarnikov.skyeng.testtask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static ru.gtatarnikov.skyeng.testtask.exception.MailingExceptionMessages.UNKNOWN_EXCEPTION;

@ControllerAdvice
public class MailingExceptionHandler extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler({MailingException.class})
    public ResponseEntity<MailingException> handleMailingException(MailingException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(
                new MailingException(UNKNOWN_EXCEPTION),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
