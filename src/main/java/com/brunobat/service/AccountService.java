/**
 * @author Copyright (c) 2008,2013, Oracle and/or its affiliates. All rights reserved.
 *
 */
package com.brunobat.service;

import com.brunobat.model.FinancialTransaction;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.HashMap;

/**
 * CDI Managed Bean Class
 */
@Named
@SessionScoped
public class AccountService implements Serializable {

    //cache of the bank accounts
    private HashMap<String, Float> accountAmountPairs = new HashMap<String, Float>();


    public FinancialTransaction deposit(FinancialTransaction transaction) {

        float theSum = 0;
        if (accountAmountPairs.containsKey(transaction.getName())) {
            theSum = accountAmountPairs.get(transaction.getName()).floatValue() + transaction.getAmount();
        }
        accountAmountPairs.put(transaction.getName(), Float.valueOf(theSum));
        transaction.setMsg("The money have been deposited to " + transaction.getName() + ", the balance of the account is " + theSum);
        return transaction;
    }
}
