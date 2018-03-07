/*
 * Class for generating unique ids; injectable
 */
package com.mycompany.sspier.code.rest.repository;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component //Spring Boot injectable
@Scope(BeanDefinition.SCOPE_PROTOTYPE) //ensure a new object is created for each @Autowire
public class IdGenerator {

    private AtomicLong nextId = new AtomicLong(1); //ensure unique Ids, avoid race condition between two callers.

    public long getNextId() {
        return nextId.getAndIncrement();
    }
}
