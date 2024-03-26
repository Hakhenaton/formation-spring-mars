package fr.sncf.d2d.up2dev.colibri2.dummy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ClassA {

    private final ClassB b;

    public ClassA(@Qualifier("toto") ClassB b){
        this.b = b;
    }
}
