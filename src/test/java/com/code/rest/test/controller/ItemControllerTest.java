package com.code.rest.test.controller;

import com.code.rest.domain.Item;
import com.code.rest.repository.ItemRepository;
import static com.code.rest.test.util.TestUtils.*;

import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemControllerTest extends ControllerIntegrationTest {

    private static final String INVALID_TEST_ITEM = "";
    private static final String TEST_ITEM = "{\"description\": \"some test item\", "
            + "\"lineItems\": [{\"name\": \"test item\", \"title\": \"test title\", "
            + "\"description\": \"test description\", \"date\": \"02/02/2022\"}]}";

    @Autowired
    private ItemRepository repository;

    @Before
    public void setUp() { repository.clear(); }
    
    private ResultActions createItem(String payload) throws Exception {
        return post("/item", payload);
    }
        
    @Test
    public void tesForValidPostReturn() throws Exception {
        assertNoItems();
        createItem(TEST_ITEM);
        Assert.assertEquals(201, getLastPostResponse().getStatus());
    }

    @Test
    public void testGetAllEmptyListEnsureCorrectResponse() throws Exception {
        assertNoItems();
        getItem()
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    
    
    
    
    
    private ResultActions getItem() throws Exception {
        return get("/item");
    }

    private ResultActions getItem(long id) throws Exception {
        return get("/item/{id}", id);
    }

    public MockHttpServletResponse getLastPostResponse() {
        return lastPostResponse;
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }

    private Item getCreatedItem() {
        List<Item> items = repository.findAll();
        return items.get(items.size() - 1);
    }

    private void assertNoItems() {
        assertItemCountIs(0);
    }

    private void assertItemCountIs(int count) {
        Assert.assertEquals(count, repository.getCount());
    }

    @Test
    public void testGetNonexistentItemEnsureNotFoundResponse() throws Exception {
        assertNoItems();
        getItem(1)
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateNewItemEnsureItemCreated() throws Exception {
        assertNoItems();
        Item desiredItem = generateTestItem();
        createItem(toJsonString(desiredItem));
        assertItemCountIs(1);
        assertAllButIdsMatchBetweenItems(desiredItem, getCreatedItem());
    }

    @Test
    public void testCreateInvalidNewItemEnsureCorrectResponse() throws Exception {
        assertNoItems();
        createItem(INVALID_TEST_ITEM)
                .andExpect(status().isBadRequest());
    }
    
    private ResultActions updateItem(long id, Item updatedItem) throws Exception {
        return put("/item/{id}", updatedItem, String.valueOf(id));
    }

    private ResultActions deleteItem(long id) throws Exception {
        return delete("/item/{id}", id);
    }

}
