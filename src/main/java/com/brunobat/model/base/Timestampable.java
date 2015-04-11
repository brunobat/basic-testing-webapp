package com.brunobat.model.base;

import java.util.Date;

/**
 * Created by Bruno Baptista on 09/04/15.
 */
public interface Timestampable {

    public Date getLastUpdated();

    public Date getCreated();
}
