package ua.nure.fomenko.analytics.db.entity;

import java.io.Serializable;

/**
 * Created by fomenko on 25.02.2017.
 */
public abstract class Entity implements Serializable {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
