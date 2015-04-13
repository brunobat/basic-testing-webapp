/**
 * @author Copyright (c) 2008,2013, Oracle and/or its affiliates. All rights reserved.
 *
 */
package com.brunobat.service;

import com.brunobat.model.FinancialTransaction;
import com.brunobat.model.Owner;
import com.brunobat.service.repository.OwnerJPARepository;
import com.brunobat.service.repository.OwnerSimpleRepository;
import com.brunobat.service.repository.base.Repository;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.HashMap;

/**
 * CDI Managed Bean Class
 */
@Named
@SessionScoped
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AccountService implements Serializable {

//    @Inject
//    protected OwnerSimpleRepository repository;

    @Inject
    protected OwnerJPARepository repository;

    public FinancialTransaction deposit(final FinancialTransaction transaction) {

        if (transaction == null &&
                transaction.getOwner() == null &&
                transaction.getOwner().getId() == null &&
                transaction.getAmount() == null) {
            return null;
        }

        Owner owner = repository.get(transaction.getOwner().getId());

        if(owner == null){
            owner = repository.store(transaction.getOwner());
        }

        if (owner != null) {

            owner.setCurrentAmount(owner.getCurrentAmount() + transaction.getAmount());
            owner.getFinancialTransactions().add(transaction);
            transaction.setOwner(owner);
            repository.store(owner);
        }
        return transaction;
    }
}
