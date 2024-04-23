package fr.sncf.d2d.up2dev.colibri2.colis.rest;

import org.springframework.util.StringUtils;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateColisRequestBody {

    @Email
    @NotNull
    private String email;

    @NotBlank
    private String address;

    private String details;

    private String deliveryPersonUsername;

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    
    public String getDeliveryPersonUsername() {
        return deliveryPersonUsername;
    }
    
    public void setDeliveryPersonUsername(String deliveryPersonUsername) {
        this.deliveryPersonUsername = deliveryPersonUsername;
    }

    @AssertTrue(message = "details must not be blank if provided")
    private boolean validateDetails(){
        return this.details == null || StringUtils.hasText(this.details);
    }

    @AssertTrue(message = "deliveryPersonUsername must not be blank if provided")
    private boolean validateDeliveryPersonUsername(){
        return this.deliveryPersonUsername == null || StringUtils.hasText(this.deliveryPersonUsername);
    }
}
