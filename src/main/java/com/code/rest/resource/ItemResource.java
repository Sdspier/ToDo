/*
 * Class for creating links for the Item class and creating a Resource object
 * from it as well.
 */
package com.code.rest.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.code.rest.domain.Item;
import org.springframework.hateoas.ResourceSupport;

// Spring ResourceSupport enables the attaching of links to resources.
public class ItemResource extends ResourceSupport {

    private final long id;
    private final String title;
    private final String description;
    private final String date;

    // create an item resource object from an Item
    public ItemResource(Item item) {
        id = item.getId();
        title = item.getTitle();
        description = item.getDescription();
        date = item.getDate();
    }

    /* can't use getId() because ResourceSupport has a default with the same name 
       that returns a link; also need to force the serialization to "id" instead of
       resourceID due to the getter method name */
    @JsonProperty("id")
    public Long getResourceId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

}
