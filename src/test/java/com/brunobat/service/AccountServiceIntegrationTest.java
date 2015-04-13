package com.brunobat.service;

import com.brunobat.model.FinancialTransaction;
import com.brunobat.model.Owner;
import com.brunobat.service.repository.OwnerJPARepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.jboss.shrinkwrap.descriptor.api.persistence10.PersistenceDescriptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.ArrayList;

@RunWith(Arquillian.class)
public class AccountServiceIntegrationTest {

    //todo add logs

    public static final String AFONSO = "Afonso";
    @Inject
    private AccountService accountBean;

    @Inject
    private OwnerJPARepository repository;

    @Deployment
    public static WebArchive createDeployment() {
//        return ShrinkWrap.createFromZipFile(WebArchive.class, new File(
//                "target/basicWebapp.war"));
        return ShrinkWrap.create(WebArchive.class, "basicWebapp.war")
                .addClass(AccountService.class)
                .addPackage(Package.getPackage("com.brunobat.service.repository"))
                .addPackage(Package.getPackage("com.brunobat.service.repository.base"))
                .addPackages(true, Package.getPackage("com.brunobat.model"))
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml", ArchivePaths.create("META-INF/persistence.xml"));
        //.addAsResource(new StringAsset(descriptor().exportAsString()), "META-INF/persistence.xml")

    }

    /**
     * Simulates the persistence.xml configuration file.
     * Mind that the location of the descriptor changes according to the type of archive.
     *
     * @return
     */
    public static PersistenceDescriptor descriptor() {
        return Descriptors.create(PersistenceDescriptor.class)
                .createPersistenceUnit()
                .name("basic-testing-webapp-persistence-unit")
                .getOrCreateProperties()
                .createProperty()
                .name("hibernate.hbm2ddl.auto")
                .value("create-drop").up()
                .createProperty()
                .name("hibernate.show_sql")
                .value("true").up().up()
                .jtaDataSource("java:jboss/datasources/ExampleDS").up();
    }

    /**
     * Before each test
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        repository.store(createOwner("owner1"));
    }

    @Test
    public void testDeposit() throws Exception {
        final Owner owner = new Owner("owner1");
        final FinancialTransaction transaction = new FinancialTransaction("transaction1");
        transaction.setAmount(10.20f);
        transaction.setOwner(owner);

        final FinancialTransaction deposit = accountBean.deposit(transaction);
        Assert.assertEquals(transaction.getAmount(), deposit.getAmount()); // amount was kept
        Assert.assertEquals(AFONSO, transaction.getOwner().getName());     // name was set

        Owner ownerFromRepo = repository.get(owner.getId());
        Assert.assertEquals(Float.valueOf(110.20f), ownerFromRepo.getCurrentAmount()); // current amount was updated
        Assert.assertTrue(1 == ownerFromRepo.getFinancialTransactions().size());       // transaction was added.
    }

    @Test
    public void testGetOwner(){
        final Owner owner = accountBean.getOwner("owner1");
        Assert.assertNotNull(owner);
        Assert.assertEquals(Float.valueOf(100f), owner.getCurrentAmount());
    }

    private Owner createOwner(String ownerId) {
        final Owner owner = new Owner(ownerId);
        owner.setCurrentAmount(100f);
        owner.setName(AFONSO);
        owner.setFinancialTransactions(new ArrayList<FinancialTransaction>());
        return owner;
    }
}