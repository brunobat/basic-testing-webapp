package com.brunobat.service;

import com.brunobat.model.FinancialTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(Arquillian.class)
public class AccountBeanTest {

    @Inject
    private AccountService accountBean;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testDeposit() throws Exception {
        final FinancialTransaction transaction = new FinancialTransaction();
        transaction.setAmount(10.20f);
        transaction.setName("Bruno");
        final FinancialTransaction deposit = accountBean.deposit(transaction);

        Assert.assertEquals(transaction.getAmount(), deposit.getAmount());
        Assert.assertEquals(transaction.getName(), deposit.getName());
        Assert.assertEquals(
                "The money have been deposited to " +
                        transaction.getName() +
                        ", the balance of the account is " +
                        transaction.getAmount(),
                deposit.getMsg());
    }
}