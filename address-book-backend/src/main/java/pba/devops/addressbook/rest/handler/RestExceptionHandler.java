package pba.devops.addressbook.rest.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // TODO - Have to be used in the front apps --

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String,Object> body = new HashMap<String,Object>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());
        body.put("errors",
            ex.getBindingResult()
                .getFieldErrors().stream().map(objectError ->
                    Arrays.asList(objectError.getField(), objectError.getDefaultMessage()))
                        .collect(Collectors.toList()));

        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }
}
