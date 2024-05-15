package fr.sncf.d2d.up2dev.colibri2.colis.models;

import java.util.Optional;
import java.util.UUID;

public class Colis {

    private final UUID id; 
    private String address; 
    private String details; 
    private String email; 
    private final String trackingCode;
    private ColisStatus status;
    private String deliveryPersonUsername;
    
    private Colis(UUID id, String address, String details, String email, String trackingCode, ColisStatus status, String deliveryPersonUsername){
        assert id != null;
        assert address != null;
        assert email != null;
        assert trackingCode != null;

        this.id = id;
        this.address = address;
        this.details = details;
        this.email = email;
        this.trackingCode = trackingCode;
        this.status = status;
        this.deliveryPersonUsername = deliveryPersonUsername;
    }

    public UUID getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public Optional<String> getDetails() {
        return Optional.ofNullable(details);
    }

    public String getEmail() {
        return email;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public ColisStatus getStatus(){
        return status;
    }

    public Optional<String> getDeliveryPersonUsername() {
        return Optional.ofNullable(deliveryPersonUsername);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(ColisStatus status) {
        this.status = status;
    }

    public void setDeliveryPersonUsername(String deliveryPersonUsername) {
        this.deliveryPersonUsername = deliveryPersonUsername;
    }

    public static ColisBuilder builder(){
        return new ColisBuilder();
    }

    public static class ColisBuilder {

        private UUID id; 
        private String address; 
        private String details; 
        private String email; 
        private String trackingCode;
        private ColisStatus status;
        private String deliveryPersonUsername;

        private ColisBuilder(){}

        public ColisBuilder id(UUID id){
            this.id = id;
            return this;
        }

        public ColisBuilder address(String address){
            this.address = address;
            return this;
        }

        public ColisBuilder details(String details){
            this.details = details;
            return this;
        }

        public ColisBuilder status(ColisStatus status){
            this.status = status;
            return this;
        }

        public ColisBuilder email(String email){
            this.email = email;
            return this;
        }

        public ColisBuilder trackingCode(String trackingCode){
            this.trackingCode = trackingCode;
            return this;
        }

        public ColisBuilder deliveryPersonUsername(String username){
            this.deliveryPersonUsername = username;
            return this;
        }

        public Colis build(){
            return new Colis(
                this.id,
                this.address,
                this.details,
                this.email,
                this.trackingCode,
                this.status,
                this.deliveryPersonUsername
            );
        }
    }
}
