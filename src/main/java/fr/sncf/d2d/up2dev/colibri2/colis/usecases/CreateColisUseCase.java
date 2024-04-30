package fr.sncf.d2d.up2dev.colibri2.colis.usecases;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.sncf.d2d.up2dev.colibri2.colis.models.Colis;
import fr.sncf.d2d.up2dev.colibri2.colis.models.ColisStatus;
import fr.sncf.d2d.up2dev.colibri2.colis.models.CreateColisParams;
import fr.sncf.d2d.up2dev.colibri2.colis.persistence.ColisRepository;
import fr.sncf.d2d.up2dev.colibri2.colis.services.ColisNotificationService;
import fr.sncf.d2d.up2dev.colibri2.users.exceptions.UserNotFoundException;
import fr.sncf.d2d.up2dev.colibri2.users.models.Role;
import fr.sncf.d2d.up2dev.colibri2.users.persistence.UsersRepository;

@Service
public class CreateColisUseCase {

    private final UsersRepository usersRepository;
    private final ColisRepository colisRepository;
    private final SecureRandom random;
    private final ColisNotificationService notifications;

    public CreateColisUseCase(ColisRepository colisRepository, UsersRepository usersRepository, ColisNotificationService notifications, SecureRandom random){
        this.colisRepository = colisRepository;
        this.usersRepository = usersRepository;
        this.random = random;
        this.notifications = notifications;
    } 
    
    public Colis create(CreateColisParams params) throws UserNotFoundException {

        final var id = UUID.randomUUID();

        final var bytes = new byte[8];
        this.random.nextBytes(bytes);
        final var trackingCode = HexFormat.of().formatHex(bytes);

        final var colis = Colis.builder()
            .id(id)
            .address(params.getAddress())
            .details(params.getDetails())
            .trackingCode(trackingCode)
            .email(params.getEmail())
            .status(ColisStatus.PENDING)
            .deliveryPersonUsername(params.getDeliveryPersonUsername())
            .build();

        final var deliveryPersonNotExists = colis.getDeliveryPersonUsername()
            .map(username -> this.usersRepository.findByUsername(username).isEmpty())
            .orElse(false);

        if (deliveryPersonNotExists){
          throw new UserNotFoundException();  
        }

        this.colisRepository.insert(colis);

        this.notifications.notifyRecipient(colis);

        return colis;
    }
}
