package com.code.rest.test.util;

import com.code.rest.domain.Item;

public class TestUtils {

    public static Item generateTestItem() {
        Item item = new Item();
        item.setTitle("test title");
        item.setDescription("test description");
        item.setDate("test date");
        return item;
    }

    public static Item generateUpdatedItem(Item original) {
        Item updated = new Item();
        updated.setTitle(original.getTitle() + " updated");
        updated.setDescription(original.getDescription() + " updated");
        updated.setDate(original.getDate() + " updated");
        return updated;
    }
}
