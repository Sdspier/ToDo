/*
 * Controller class
 */
package com.mycompany.sspier.code.rest.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.sspier.code.rest.domain.Item;
import com.mycompany.sspier.code.rest.repository.ItemRepository;
import com.mycompany.sspier.code.rest.resource.ItemResource;
import com.mycompany.sspier.code.rest.resource.ItemResourceAssembler;

@CrossOrigin(origins = "*") //specify what cross domain requests are allowed
@RestController
@ExposesResourceFor(Item.class) //exposes URIs for the Item class
@RequestMapping(value = "/item", produces = "application/json") //map an HTTP req to a handler method
public class ItemController {

    @Autowired //allow access to the stored Item objects
    private ItemRepository repository;
    
    @Autowired //allow for [Item object] -> [ItemResource object]
    private ItemResourceAssembler assembler;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<ItemResource>> findAllItems() {
        List<Item> items = repository.findAll();
        return new ResponseEntity<>(assembler.toResourceCollection(items), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ItemResource> createItem(@RequestBody Item item) {
        Item createdItem = repository.create(item);
        return new ResponseEntity<>(assembler.toResource(createdItem), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ItemResource> findItemById(@PathVariable Long id) {
        Optional<Item> item = repository.findById(id); //avoid null pointer death
        if (item.isPresent()) {
            return new ResponseEntity<>(assembler.toResource(item.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        boolean wasDeleted = repository.delete(id);
        HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(responseStatus);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity<ItemResource> updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        boolean wasUpdated = repository.update(id, updatedItem);
        if (wasUpdated) {
            return findItemById(id);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
