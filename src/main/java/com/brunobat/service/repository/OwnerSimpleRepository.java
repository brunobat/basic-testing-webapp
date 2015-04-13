package com.brunobat.service.repository;

import com.brunobat.model.Owner;
import com.brunobat.service.repository.base.Repository;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Bruno Baptista on 09/04/15.
 */
//todo set as singleton
//@Startup
//@Singleton
@Named
@SessionScoped
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OwnerSimpleRepository implements Repository<Owner>, Serializable {

    // this will only work properly if we lock each owner out of the accounts. Out of scope.
    private static ConcurrentHashMap<String, Owner> accounts = new ConcurrentHashMap<>();

//    @PostConstruct
//    void init() {
//        accounts = new ConcurrentHashMap<>();
//    }

    @Override
    public Class<Owner> getType() {
        return Owner.class;
    }

    @Override
    public Owner store(Owner entity) {
        return accounts.put(entity.getId(), entity);
    }

    @Override
    public Owner get(String id) {
        return accounts.get(id);
    }

    @Override
    public void remove(Owner entity) {
        accounts.remove(entity.getId());
    }
}
