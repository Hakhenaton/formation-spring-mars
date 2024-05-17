package fr.sncf.d2d.up2dev.colibri2.colis.models;

import java.util.UUID;

import fr.sncf.d2d.up2dev.colibri2.common.models.SetField;

public class UpdateColisParams {

    private UUID id;

    private SetField<String> address;

    private SetField<String> details;

    private SetField<String> deliveryPersonUsername;

    private SetField<String> email;

    private SetField<ColisStatus> status;

    public SetField<String> getAddress() {
        return address;
    }

    public void setAddress(SetField<String> address) {
        this.address = address;
    }

    public SetField<String> getDetails() {
        return details;
    }

    public void setDetails(SetField<String> details) {
        this.details = details;
    }

    public SetField<String> getDeliveryPersonUsername() {
        return deliveryPersonUsername;
    }

    public void setDeliveryPersonUsername(SetField<String> deliveryPersonUsername) {
        this.deliveryPersonUsername = deliveryPersonUsername;
    }

    public SetField<String> getEmail() {
        return email;
    }

    public void setEmail(SetField<String> email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SetField<ColisStatus> getStatus() {
        return status;
    }

    public void setStatus(SetField<ColisStatus> status) {
        this.status = status;
    }
}
