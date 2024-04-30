package fr.sncf.d2d.up2dev.colibri2.users.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(){
        super("user not found");
    }
}
