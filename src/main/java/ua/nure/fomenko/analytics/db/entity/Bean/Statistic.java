package ua.nure.fomenko.analytics.db.entity.Bean;

import ua.nure.fomenko.analytics.db.entity.Entity;
import ua.nure.fomenko.analytics.db.entity.Links;
import ua.nure.fomenko.analytics.db.entity.Visiters;

/**
 * Created by fomenko on 25.02.2017.
 */
public class Statistic extends Entity{

    private Links links;
    private Visiters visiters;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Visiters getVisiters() {
        return visiters;
    }

    public void setVisiters(Visiters visiters) {
        this.visiters = visiters;
    }
}
