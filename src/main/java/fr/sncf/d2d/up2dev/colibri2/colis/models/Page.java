package fr.sncf.d2d.up2dev.colibri2.colis.models;

import java.util.List;

public class Page<T> {
    
    private final List<T> items;
    
    private final long total;

    public Page(List<T> items, long total) {
        this.items = items;
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public long getTotal() {
        return total;
    }
}
