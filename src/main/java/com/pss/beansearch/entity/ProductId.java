package com.pss.beansearch.entity;


import jakarta.persistence.Embeddable;

import java.io.Serializable;


@Embeddable
public class ProductId implements Serializable {
    long id;
    long version;

    public long getId() {
        return id;
    }

    public long getVersion() {
        return version;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
