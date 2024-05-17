package fr.sncf.d2d.up2dev.colibri2.colis.rest;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.sncf.d2d.up2dev.colibri2.colis.exceptions.ColisNotFoundException;
import fr.sncf.d2d.up2dev.colibri2.colis.exceptions.ColisStatusException;
import fr.sncf.d2d.up2dev.colibri2.colis.models.Colis;
import fr.sncf.d2d.up2dev.colibri2.colis.models.CreateColisParams;
import fr.sncf.d2d.up2dev.colibri2.colis.models.Page;
import fr.sncf.d2d.up2dev.colibri2.colis.models.PaginateColisParams;
import fr.sncf.d2d.up2dev.colibri2.colis.models.UpdateColisParams;
import fr.sncf.d2d.up2dev.colibri2.colis.usecases.CreateColisUseCase;
import fr.sncf.d2d.up2dev.colibri2.colis.usecases.PaginateColisUseCase;
import fr.sncf.d2d.up2dev.colibri2.colis.usecases.UpdateColisUseCase;
import fr.sncf.d2d.up2dev.colibri2.users.exceptions.UserNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/colis")
public class ColisRestController {

    private final CreateColisUseCase createColisUseCase;
    private final PaginateColisUseCase paginateColisUseCase;
    private final UpdateColisUseCase updateColisUseCase;

    public ColisRestController(CreateColisUseCase createColisUseCase, PaginateColisUseCase paginateColisUseCase, UpdateColisUseCase updateColisUseCase) {
        this.createColisUseCase = createColisUseCase;
        this.paginateColisUseCase = paginateColisUseCase;
        this.updateColisUseCase = updateColisUseCase;
    }

    @GetMapping
    public Page<Colis> paginateColis(@Valid PaginateColisQuery query){

        final var params = new PaginateColisParams();
        params.setPage(query.getPage());
        params.setItemsPerPage(query.getItemsPerPage());

        return this.paginateColisUseCase.paginate(params);
    }
 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Colis createColis(@RequestBody @Valid CreateColisRequestBody body) throws UserNotFoundException{
        
        final var params = new CreateColisParams();
        params.setAddress(body.getAddress());
        params.setDetails(body.getDetails());
        params.setEmail(body.getEmail());
        params.setDeliveryPersonUsername(body.getDeliveryPersonUsername());
        
        return this.createColisUseCase.create(params);
    }

    @PatchMapping("/{colisId}")
    public Colis updateColis(
        @PathVariable("colisId") UUID id,
        @RequestBody @Valid UpdateColisRequestBody body
    ) throws ColisNotFoundException, UserNotFoundException, ColisStatusException {

        final var params = new UpdateColisParams();
        params.setAddress(body.getAddress());
        params.setEmail(body.getEmail());
        params.setDeliveryPersonUsername(body.getDeliveryPersonUsername());
        params.setDetails(body.getDetails());
        params.setStatus(body.getStatus());
        params.setId(id);

        return this.updateColisUseCase.update(params);
    }
}
