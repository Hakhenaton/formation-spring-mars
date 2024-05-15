package fr.sncf.d2d.up2dev.colibri2.colis.exceptions;

public class ColisNotFoundException extends Exception {
    public ColisNotFoundException(){
        super("colis was not found");
    }
}
