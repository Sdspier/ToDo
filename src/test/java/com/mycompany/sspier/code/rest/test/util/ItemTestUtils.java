/*
 * Utilities for testing
 */
package com.mycompany.sspier.code.rest.test.util;

import com.mycompany.sspier.code.rest.domain.Item;

import org.junit.Assert;

public class ItemTestUtils {

    public static void assertTwoItems(Item expected, Item actual) {
        Assert.assertEquals(expected.getTitle(), actual.getTitle());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getDate(), actual.getDate());
    }

    public static Item generateTestItem() {
        Item order = new Item();
        order.setDescription("generated test description");
        order.setTitle("generated test title");
        order.setDate("01/02/3456");
        return order;
    }

}
