package fr.sncf.d2d.up2dev.colibri2.colis.models;

import java.util.UUID;

public class Colis {

    private final UUID id; 
    private final String address; 
    private final String details; 
    private final String email; 
    private final String trackingCode;
    
    private Colis(UUID id, String address, String details, String email, String trackingCode){
        assert id != null;
        assert address != null;
        assert email != null;
        assert trackingCode != null;

        this.id = id;
        this.address = address;
        this.details = details;
        this.email = email;
        this.trackingCode = trackingCode;
    }

    public UUID getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getDetails() {
        return details;
    }

    public String getEmail() {
        return email;
    }

    public String getTrackingCode() {
        return trackingCode;
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

        public ColisBuilder email(String email){
            this.email = email;
            return this;
        }

        public ColisBuilder trackingCode(String trackingCode){
            this.trackingCode = trackingCode;
            return this;
        }

        public Colis build(){
            return new Colis(
                this.id,
                this.address,
                this.details,
                this.email,
                this.trackingCode
            );
        }
    }
}
