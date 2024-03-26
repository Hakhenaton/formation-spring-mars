package fr.sncf.d2d.up2dev.colibri2.dummy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.sncf.d2d.up2dev.colibri2.dummy.ClassB;

public class ClassBTest {

    static ClassB INSTANCE;
    
    @BeforeAll
    static void globalSetup(){
        System.out.println("global setup is called !");
        INSTANCE = new ClassB();
    }

    @BeforeEach
    void specificSetup(){
        System.out.println("specific setup is called !");
    }

    // given an instance of ClassB and the string "hello world"
    // when i call the uppercase() method
    // THEN i should have the string "HELLO WORLD"
    @Test
    void helloWorld(){
        final var result = INSTANCE.uppercase("hello world");
        // java pur
        assert result.equals("HELLO WORLD");
        // junit
        Assertions.assertEquals("HELLO WORLD", result, "le résultat doit être en majuscule");
    }

    // given an instance of ClassB and an empty string
    // when i call the uppercase() method
    // THEN i should receive an exception
    @Test
    void emptyString(){
        // java pur
        try {
            INSTANCE.uppercase("");
            // je veux m'assurer qu'on arrive jamais là
            assert false;
        } catch(IllegalArgumentException ex){
            assert ex.getMessage().equals("input must not be blank");
        }
        // junit
        Assertions.assertThrows(IllegalArgumentException.class, () -> INSTANCE.uppercase(""));
    }
}
