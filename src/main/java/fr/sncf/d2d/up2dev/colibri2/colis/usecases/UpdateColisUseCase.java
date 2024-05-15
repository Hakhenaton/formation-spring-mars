package fr.sncf.d2d.up2dev.colibri2.colis.usecases;

import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.sncf.d2d.up2dev.colibri2.colis.exceptions.ColisNotFoundException;
import fr.sncf.d2d.up2dev.colibri2.colis.models.Colis;
import fr.sncf.d2d.up2dev.colibri2.colis.models.UpdateColisParams;
import fr.sncf.d2d.up2dev.colibri2.colis.persistence.ColisRepository;
import fr.sncf.d2d.up2dev.colibri2.users.exceptions.UserNotFoundException;
import fr.sncf.d2d.up2dev.colibri2.users.persistence.UsersRepository;

@Service
public class UpdateColisUseCase {
    
    private final ColisRepository colisRepository;
    private final UsersRepository usersRepository;

    public UpdateColisUseCase(ColisRepository colisRepository, UsersRepository usersRepository){
        this.colisRepository = colisRepository;
        this.usersRepository = usersRepository;
    }

    public Colis update(UpdateColisParams params) throws ColisNotFoundException, UserNotFoundException {

        final var colis = this.colisRepository.findById(params.getId())
            .orElseThrow(() -> new ColisNotFoundException());

        params.getAddress().ifProvided(colis::setAddress);
        // params.getAddress().ifProvided(address -> colis.setAddress(address));
        params.getDeliveryPersonUsername().ifProvided(colis::setDeliveryPersonUsername);
        params.getDetails().ifProvided(colis::setDetails);
        params.getEmail().ifProvided(colis::setEmail);

        final var deliveryPersonNotExists = colis.getDeliveryPersonUsername()
            .map(username -> this.usersRepository.findByUsername(username).isEmpty())
            .orElse(false);

        if (deliveryPersonNotExists){
          throw new UserNotFoundException();  
        }

        this.colisRepository.update(colis);

        return colis;
    }
}
