package fr.sncf.d2d.up2dev.colibri2.colis.rest;

import java.security.NoSuchAlgorithmException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.sncf.d2d.up2dev.colibri2.colis.models.Colis;
import fr.sncf.d2d.up2dev.colibri2.colis.models.CreateColisParams;
import fr.sncf.d2d.up2dev.colibri2.colis.models.Page;
import fr.sncf.d2d.up2dev.colibri2.colis.models.PaginateColisParams;
import fr.sncf.d2d.up2dev.colibri2.colis.usecases.CreateColisUseCase;
import fr.sncf.d2d.up2dev.colibri2.colis.usecases.PaginateColisUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/colis")
public class ColisRestController {

    private final CreateColisUseCase createColisUseCase;
    private final PaginateColisUseCase paginateColisUseCase;

    public ColisRestController(CreateColisUseCase createColisUseCase, PaginateColisUseCase paginateColisUseCase) {
        this.createColisUseCase = createColisUseCase;
        this.paginateColisUseCase = paginateColisUseCase;
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
    public Colis createColis(@RequestBody @Valid CreateColisRequestBody body) throws NoSuchAlgorithmException {
        
        final var params = new CreateColisParams();
        params.setAddress(body.getAddress());
        params.setDetails(body.getDetails());
        params.setEmail(body.getEmail());
        params.setDeliveryPersonUsername(body.getDeliveryPersonUsername());
        
        return this.createColisUseCase.create(params);
    }
}
