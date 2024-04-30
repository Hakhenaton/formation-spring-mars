package fr.sncf.d2d.up2dev.colibri2.colis.models;

public class PaginateColisParams {
    
    private long itemsPerPage;

    private long page;

    public long getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(long itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long total) {
        this.page = total;
    }
}
