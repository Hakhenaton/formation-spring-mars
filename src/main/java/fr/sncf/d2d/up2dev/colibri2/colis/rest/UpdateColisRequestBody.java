package fr.sncf.d2d.up2dev.colibri2.colis.rest;

import org.springframework.util.StringUtils;

import fr.sncf.d2d.up2dev.colibri2.common.models.SetField;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UpdateColisRequestBody {
    
    private SetField<@NotBlank String> address = SetField.noop();

    private SetField<String> details = SetField.noop();

    private SetField<String> deliveryPersonUsername = SetField.noop();

    private SetField<@Email String> email = SetField.noop();

    public SetField<String> getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = SetField.with(address);
    }

    public SetField<String> getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? SetField.withNull() : SetField.with(details);
    }

    public SetField<String> getDeliveryPersonUsername() {
        return deliveryPersonUsername;
    }

    public void setDeliveryPersonUsername(String deliveryPersonUsername) {
        this.deliveryPersonUsername = deliveryPersonUsername == null ? SetField.withNull() : SetField.with(deliveryPersonUsername);
    }

    public SetField<String> getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = SetField.with(email);
    }

    @AssertTrue
    private boolean isDetailsValid(){
        return this.details
            .map(details -> details == null || StringUtils.hasText(details))
            .orElse(true);
    }

    @AssertTrue
    private boolean isDeliveryPersonUsername(){
        return this.deliveryPersonUsername
            .map(username -> username == null || StringUtils.hasText(username))
            .orElse(true);
    }
}
