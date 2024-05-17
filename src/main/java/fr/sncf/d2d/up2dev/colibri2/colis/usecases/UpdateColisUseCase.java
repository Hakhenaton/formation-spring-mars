package fr.sncf.d2d.up2dev.colibri2.colis.usecases;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import fr.sncf.d2d.up2dev.colibri2.colis.exceptions.ColisNotFoundException;
import fr.sncf.d2d.up2dev.colibri2.colis.exceptions.ColisStatusException;
import fr.sncf.d2d.up2dev.colibri2.colis.models.Colis;
import fr.sncf.d2d.up2dev.colibri2.colis.models.ColisStatus;
import fr.sncf.d2d.up2dev.colibri2.colis.models.UpdateColisParams;
import fr.sncf.d2d.up2dev.colibri2.colis.persistence.ColisRepository;
import fr.sncf.d2d.up2dev.colibri2.colis.services.ColisNotificationService;
import fr.sncf.d2d.up2dev.colibri2.users.exceptions.UserNotFoundException;
import fr.sncf.d2d.up2dev.colibri2.users.persistence.UsersRepository;

@Service
public class UpdateColisUseCase {
    
    private final ColisRepository colisRepository;
    private final UsersRepository usersRepository;
    private final ColisNotificationService notifications;

    public UpdateColisUseCase(ColisRepository colisRepository, UsersRepository usersRepository, ColisNotificationService colisNotificationService){
        this.colisRepository = colisRepository;
        this.usersRepository = usersRepository;
        this.notifications = colisNotificationService;

    }

    public Colis update(UpdateColisParams params) throws ColisNotFoundException, ColisStatusException, UserNotFoundException {

        final var colis = this.colisRepository.findById(params.getId())
            .orElseThrow(() -> new ColisNotFoundException());

        params.getAddress().ifProvided(colis::setAddress);
        // params.getAddress().ifProvided(address -> colis.setAddress(address));
        params.getDeliveryPersonUsername().ifProvided(colis::setDeliveryPersonUsername);
        params.getDetails().ifProvided(colis::setDetails);
        params.getEmail().ifProvided(colis::setEmail);

        final var isNewStatusProvided = params.getStatus()
            .map(newStatus -> newStatus != colis.getStatus())
            .orElse(false);

        params.getStatus().ifProvided(newStatus -> {
            final var oldStatus = colis.getStatus();

            if (oldStatus == newStatus){
                return;
            }

            switch (newStatus){
                case PENDING:
                    if (oldStatus == ColisStatus.IN_TRANSIT){
                        colis.setStatus(newStatus);
                        return;
                    }
                    break;
                case IN_TRANSIT:
                    if (oldStatus == ColisStatus.PENDING){
                        colis.setStatus(newStatus);
                        return;
                    }
                    break;
                case DELIVERED:
                case RETURNED:
                    if (Stream.of(ColisStatus.PENDING, ColisStatus.IN_TRANSIT)
                        .anyMatch(allowedStatus -> allowedStatus == oldStatus)){
                        colis.setStatus(newStatus);
                        return;
                    }
            }

            throw new ColisStatusException(oldStatus, newStatus);
        });

        final var deliveryPersonNotExists = colis.getDeliveryPersonUsername()
            .map(username -> this.usersRepository.findByUsername(username).isEmpty())
            .orElse(false);

        if (deliveryPersonNotExists){
          throw new UserNotFoundException();  
        }

        this.colisRepository.update(colis);

        if (isNewStatusProvided){
            this.notifications.notifyRecipient(colis);
        }

        return colis;
    }
}
