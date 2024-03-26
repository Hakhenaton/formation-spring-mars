package fr.sncf.d2d.up2dev.colibri2.dummy;

import org.springframework.stereotype.Component;

public class ClassB {
    
    public String uppercase(String input){

        if (input.isBlank()){
            throw new IllegalArgumentException("input must not be blank");
        }

        return input.toUpperCase();
    }
}
