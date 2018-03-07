/*
 * Class for holding data in-memory instead of a DB
 */
package com.mycompany.sspier.code.rest.repository;

import com.mycompany.sspier.code.rest.domain.Identifiable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class InMemoryRepository<T extends Identifiable> {

    @Autowired
    private IdGenerator idGenerator;

    //avoid concurrent ops race condition with a synchronized list
    private List<T> items = Collections.synchronizedList(new ArrayList<>());

    public T create(T item) {
        items.add(item);
        item.setId(idGenerator.getNextId());
        return item;
    }

    public boolean delete(Long id) {
        return items.removeIf(i -> i.getId().equals(id));
    }

    //return the list of all todo items
    public List<T> findAll() {
        return items;
    }

    //use Optional to avoid null pointer death 
    public Optional<T> findById(Long id) {
        return items.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    //resolved in ItemRepository class
    public abstract void updateIfExists(T original, T updated);

    public boolean update(Long id, T updated) {
        if (updated == null) {
            return false;
        } else {
            Optional<T> item = findById(id);
            item.ifPresent(original -> updateIfExists(original, updated));
            return item.isPresent();
        }
    }

}
