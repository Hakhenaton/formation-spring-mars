package fr.sncf.d2d.up2dev.colibri2.colis.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.sncf.d2d.up2dev.colibri2.colis.exceptions.ColisNotFoundException;
import fr.sncf.d2d.up2dev.colibri2.colis.exceptions.ColisStatusException;
import fr.sncf.d2d.up2dev.colibri2.users.exceptions.UserNotFoundException;

@RestControllerAdvice(assignableTypes = { ColisRestController.class })
public class ColisRestControllerAdvice {
    
    private final ObjectMapper objectMapper;

    public ColisRestControllerAdvice(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler({ UserNotFoundException.class, ColisNotFoundException.class })
    public ResponseEntity<?> handleEntityNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({ ColisStatusException.class })
    public ResponseEntity<String> handleColisStatus(ColisStatusException colisStatusException) throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(this.objectMapper.writeValueAsString(colisStatusException.getMessage()));
    }
}
