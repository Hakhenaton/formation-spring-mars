package fr.sncf.d2d.up2dev.colibri2.colis.guards;

import org.springframework.stereotype.Component;

import fr.sncf.d2d.up2dev.colibri2.colis.models.Colis;
import fr.sncf.d2d.up2dev.colibri2.security.models.ApplicationUserDetails;
import fr.sncf.d2d.up2dev.colibri2.users.models.Role;

@Component
public class ColisGuard {
    
    public boolean canCreate(Colis colis, ApplicationUserDetails userDetails){

        final var caller = userDetails.getUser();

        if (caller.getRole() == Role.ADMINISTRATOR){
            return true;
        }

        if (caller.getRole() == Role.DELIVERY_PERSON){
            return colis.getDeliveryPersonUsername()
                .map(username -> username.equals(caller.getUsername()))
                .orElse(true);
        }

        return false;
    }

    
}
