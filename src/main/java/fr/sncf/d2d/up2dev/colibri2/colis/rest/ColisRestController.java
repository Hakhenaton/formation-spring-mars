package fr.sncf.d2d.up2dev.colibri2.colis.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colis")
public class ColisRestController {
 
    @PostMapping
    public void createColis(@RequestBody CreateColisRequestBody body){

    }
}
