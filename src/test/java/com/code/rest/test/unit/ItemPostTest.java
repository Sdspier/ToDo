/*
 * Test the POST 
 */
package com.code.rest.test.unit;

import com.code.rest.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemPostTest extends AbstractFrame {


    String TEST_ITEM = "{\"description\": \"some test item\", "
            + "\"lineItems\": [{\"name\": \"test item\", \"title\": \"test title\", "
            + "\"description\": \"test description\", \"date\": \"02/02/2022\"}]}";

    public void createItem() throws Exception {
        post("/item", TEST_ITEM);
    }

    @Test
    public void ItemSuccessfullyPosted() throws Exception {
        createItem();
        Assert.assertEquals(201, getLastPostResponse().getStatus());
    }

}
