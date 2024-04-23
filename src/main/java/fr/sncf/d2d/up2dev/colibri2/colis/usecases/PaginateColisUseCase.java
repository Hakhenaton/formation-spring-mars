package fr.sncf.d2d.up2dev.colibri2.colis.usecases;

import org.springframework.stereotype.Service;

import fr.sncf.d2d.up2dev.colibri2.colis.models.Colis;
import fr.sncf.d2d.up2dev.colibri2.colis.models.Page;
import fr.sncf.d2d.up2dev.colibri2.colis.models.PaginateColisParams;
import fr.sncf.d2d.up2dev.colibri2.colis.persistence.ColisRepository;

@Service
public class PaginateColisUseCase {
    
    private final ColisRepository colisRepository;

    public PaginateColisUseCase(ColisRepository repository){
        this.colisRepository = repository;
    }

    public Page<Colis> paginate(PaginateColisParams params){
        return this.colisRepository.paginate(params);
    }
}
