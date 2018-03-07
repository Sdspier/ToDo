/*
 * Test the POST 
 */
package com.mycompany.sspier.code.rest.test.unit;


import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemPostTest extends AbstractFrame {

    private static final String TEST_ITEM = "{\"description\": \"some test item\", "
            + "\"lineItems\": [{\"name\": \"test item\", \"title\": \"test title\", "
            + "\"description\": \"test description\", \"date\": \"02/02/2022\"}]}";
    
   
    private void createItem() throws Exception {
        post("/item", TEST_ITEM);
    }
    
    public void anItemExists() throws Throwable {
        createItem();
    }

    public void theUserCallsGetOrders() throws Throwable {
        createItem();
    }

    public void theOrderIsSuccessfullyCreated() {
        Assert.assertEquals(201, getLastPostResponse().getStatus());
    }
}
