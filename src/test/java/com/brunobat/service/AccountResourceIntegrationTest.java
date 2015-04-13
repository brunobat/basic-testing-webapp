package com.brunobat.service;

import com.brunobat.model.FinancialTransaction;
import com.brunobat.model.Owner;
import com.brunobat.service.repository.OwnerJPARepository;
import com.brunobat.service.resource.AccountResource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
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
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

@RunWith(Arquillian.class)
public class AccountResourceIntegrationTest {

//    @Inject
//    private OwnerJPARepository repository;

    @ArquillianResource
    private URL webappUrl;

    @Deployment
    public static WebArchive createDeployment() {
                return ShrinkWrap.createFromZipFile(WebArchive.class, new File(
                "target/basicWebapp.war"));
//        return ShrinkWrap.create(WebArchive.class, "basicWebapp.war")
//                .addClass(AccountService.class)
//                .addClass(AccountResource.class)
//                .addPackage(Package.getPackage("com.brunobat.service.repository"))
//                .addPackage(Package.getPackage("com.brunobat.service.repository.base"))
//                .addPackages(true, Package.getPackage("com.brunobat.model"))
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
//                .addAsResource("META-INF/persistence.xml", ArchivePaths.create("META-INF/persistence.xml"));
        //.addAsResource(new StringAsset(descriptor().exportAsString()), "META-INF/persistence.xml")

    }

//    /**
//     * Before each test
//     *
//     * @throws Exception
//     */
//    @Before
//    public void setUp() throws Exception {
//        repository.store(createOwner("owner1"));
//    }

    @Test
    @RunAsClient
    public void testGetOwner(@ArquillianResteasyResource AccountResource accountResource) throws Exception {
        final Response response = accountResource.getOwnerDetail("owner1");
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        final Owner owner = (Owner) response.getEntity();
        Assert.assertNotNull(owner);
//        Assert.assertEquals(Float.valueOf(100f), owner.getCurrentAmount());
    }

    private Owner createOwner(String ownerId) {
        final Owner owner = new Owner(ownerId);
        owner.setCurrentAmount(100f);
        owner.setName("AFONSO");
        owner.setFinancialTransactions(new ArrayList<FinancialTransaction>());
        return owner;
    }
}