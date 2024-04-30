package fr.sncf.d2d.up2dev.colibri2.colis.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.sncf.d2d.up2dev.colibri2.users.exceptions.UserNotFoundException;

@RestControllerAdvice(assignableTypes = { ColisRestController.class })
public class ColisRestControllerAdvice {
    
    @ExceptionHandler({ UserNotFoundException.class })
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
}
