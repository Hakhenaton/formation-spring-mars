package fr.sncf.d2d.up2dev.colibri2.colis.models;

public class CreateColisParams {

    private String email;

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

    
}
