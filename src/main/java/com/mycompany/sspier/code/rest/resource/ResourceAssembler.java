/*
 * Abstract class to facilitate Item(s) to ItemResource(s)
 */
package com.mycompany.sspier.code.rest.resource;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class ResourceAssembler<DomainType, ResourceType> {

    //take a single Item object and produces an ItemResopurce object
    public abstract ResourceType toResource(DomainType domainObject);

    //map the list of Item objects to ItemResource objects
    public Collection<ResourceType> toResourceCollection(Collection<DomainType> domainObjects) {
        return domainObjects.stream().map(o -> toResource(o)).collect(Collectors.toList());
    }
    
    
    
    
}
