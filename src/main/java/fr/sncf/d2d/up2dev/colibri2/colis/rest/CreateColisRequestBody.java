package fr.sncf.d2d.up2dev.colibri2.colis.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreateColisRequestBody {

    private String email;

    public void setEmail(String email){
        this.email = email;
    } 
}
