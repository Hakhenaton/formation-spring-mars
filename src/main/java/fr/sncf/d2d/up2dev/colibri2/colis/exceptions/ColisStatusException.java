package fr.sncf.d2d.up2dev.colibri2.colis.exceptions;

import fr.sncf.d2d.up2dev.colibri2.colis.models.ColisStatus;

public class ColisStatusException extends Exception {
    public ColisStatusException(ColisStatus old, ColisStatus newStatus){
        super(String.format("cannot switch from %s to %s", old, newStatus));
    }
}
