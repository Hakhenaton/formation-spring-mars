package fr.sncf.d2d.up2dev.colibri2.colis.usecases;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.sncf.d2d.up2dev.colibri2.colis.models.Colis;
import fr.sncf.d2d.up2dev.colibri2.colis.models.CreateColisParams;
import fr.sncf.d2d.up2dev.colibri2.colis.persistence.ColisRepository;

@Service
public class CreateColisUseCase {

    private final ColisRepository colisRepository;

    public CreateColisUseCase(ColisRepository colisRepository){
        this.colisRepository = colisRepository;
    } 
    
    public Colis create(CreateColisParams params) throws NoSuchAlgorithmException {

        final var id = UUID.randomUUID();

        final var bytes = new byte[8];
        final var random = SecureRandom.getInstanceStrong();
        random.nextBytes(bytes);
        final var trackingCode = HexFormat.of().formatHex(bytes);

        final var colis = Colis.builder()
            .id(id)
            .address(params.getAddress())
            .details(params.getDetails())
            .trackingCode(trackingCode)
            .email(params.getEmail())
            .build();

        this.colisRepository.insert(colis);

        return colis;
    }
}
