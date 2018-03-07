/*
 * Class for assembling and finalizing the links for the Item objects
 */
package com.code.rest.resource;

import com.code.rest.domain.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks; //accessor to links pointing to controllers backing an entity type
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

@Component //allows for injection
public class ItemResourceAssembler extends ResourceAssembler<Item, ItemResource> {

    @Autowired
    protected EntityLinks entityLinks;//provides for link construction

    private static final String UPDATE_REL = "update";
    private static final String DELETE_REL = "delete";

    @Override
    public ItemResource toResource(Item item) {

        ItemResource resource = new ItemResource(item);

        final Link selfLink = entityLinks.linkToSingleResource(item);//creates a Link pointing to single resource backing the given entity

        resource.add(selfLink.withSelfRel());
        resource.add(selfLink.withRel(UPDATE_REL));
        resource.add(selfLink.withRel(DELETE_REL));

        return resource;
    }

}
