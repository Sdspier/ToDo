package com.code.rest.test.controller;

import com.code.rest.domain.Item;
import com.code.rest.repository.ItemRepository;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

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

    private static final String TEST_ITEM = "{\"description\": \"some test item\", "
            + "\"lineItems\": [{\"name\": \"test item\", \"title\": \"test title\", "
            + "\"description\": \"test description\", \"date\": \"02/02/2022\"}]}";

    @Autowired
    private ItemRepository repository;

    @Before
    public void setUp() {
        repository.clear();
    }

    private ResultActions createItem(String s) throws Exception {
        return post("/item", s);
    }

    @Test
    public void tesForValidPostReturn() throws Exception {
        createItem(TEST_ITEM);
        assertEquals(201, getLastPostResponse().getStatus());
    }

    @Test
    public void testGetAllEmptyListEnsureCorrectResponse() throws Exception {
        getItem().andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));
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

    @Test
    public void testGetNonexistentItemEnsureNotFoundResponse() throws Exception {
        getItem(1).andExpect(status().isNotFound());
    }

    private ResultActions updateItem(long id, Item updatedItem) throws Exception {
        return put("/item/{id}", updatedItem, String.valueOf(id));
    }

    private ResultActions deleteItem(long id) throws Exception {
        return delete("/item/{id}", id);
    }

}
