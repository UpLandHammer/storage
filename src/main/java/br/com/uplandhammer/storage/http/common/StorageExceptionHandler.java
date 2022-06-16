package br.com.uplandhammer.storage.http.common;

import br.com.uplandhammer.storage.exception.StorageFileException;
import br.com.uplandhammer.storage.http.domain.response.PatternResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class StorageExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {StorageFileException.class})
    protected ResponseEntity<PatternResponse> storageContentException(StorageFileException ex, WebRequest request) {

        String message = ex.getMessage() + " - " + ( ex.getCause() != null ? ex.getCause().getMessage() : null);
        log.error("Exception Handler {} ", message);
        PatternResponse patternResponse = PatternResponse.builder()
                .message(message)
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return ResponseEntity.badRequest().body(patternResponse);
    }

}
