package com.brunobat.service.resource;

import com.brunobat.model.FinancialTransaction;
import com.brunobat.model.Owner;
import com.brunobat.service.repository.OwnerJPARepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.ArrayList;

/**
 * REST client test
 */
@RunWith(Arquillian.class)
public class AccountResourceIntegrationTest {

    @Inject
    private OwnerJPARepository repository;

//    @ArquillianResource
//    private URL webappUrl;

    @Deployment()
    public static WebArchive createDeployment() {
        return ShrinkWrap.createFromZipFile(WebArchive.class, new File(
                "target/basicWebapp.war"));
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
    public void testGetOwner(@ArquillianResteasyResource AccountResource accountResource) throws Exception {
        final Response response = accountResource.getOwnerDetail("owner1");
        Assert.assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        final Owner owner = (Owner) response.getEntity();
        Assert.assertNotNull(owner);
        Assert.assertEquals(Float.valueOf(100f), owner.getCurrentAmount());
    }

    private Owner createOwner(String ownerId) {
        final Owner owner = new Owner(ownerId);
        owner.setCurrentAmount(100f);
        owner.setName("AFONSO");
        owner.setFinancialTransactions(new ArrayList<FinancialTransaction>());
        return owner;
    }
}