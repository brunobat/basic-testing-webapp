package com.brunobat.service;

import com.brunobat.model.FinancialTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

@RunWith(Arquillian.class)
public class AccountBeanTest {

    @Inject
    private AccountService accountBean;

    @Deployment
    public static WebArchive createDeployment() {
//        return ShrinkWrap.createFromZipFile(WebArchive.class, new File(
//                "target/basicWebapp.war"));
        return ShrinkWrap.create(WebArchive.class, "basicWebapp.war")
                .addClass(AccountService.class)
                .addClass(FinancialTransaction.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

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