/*
 * The Item objects do not know how to update themselves update, so the data 
 * source has to perform updates.
 */
package com.mycompany.sspier.code.rest.repository;

import com.mycompany.sspier.code.rest.domain.Item;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository extends InMemoryRepository<Item> {

    //get it and set it
    @Override
    public void updateIfExists(Item original, Item updated) {
        original.setTitle(updated.getTitle());
        original.setDescription(updated.getDescription());
        original.setDate(updated.getDate());

    }

}
