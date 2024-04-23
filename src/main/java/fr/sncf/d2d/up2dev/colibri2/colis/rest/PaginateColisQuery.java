package fr.sncf.d2d.up2dev.colibri2.colis.rest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class PaginateColisQuery {
    
    @Min(1)
    @Max(100)
    private long itemsPerPage = 10;

    @Min(0)
    private long page = 0;

    public long getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(long itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }
}
