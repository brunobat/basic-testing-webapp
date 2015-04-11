package com.brunobat.model.base;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Bruno Baptista on 09/04/15.
 */
@MappedSuperclass
public abstract class BaseEntity implements Identifiable, Timestampable, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    /**
     * Default constructor
     */
    protected BaseEntity() {
    }

    public BaseEntity(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Date getLastUpdated() {
        return updated == null ? null : (Date) updated.clone();
    }

    @Override
    public Date getCreated() {
        return created == null ? null : (Date) created.clone();
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
