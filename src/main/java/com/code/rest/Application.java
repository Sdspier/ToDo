/*
 * Main driver class
 */
package com.code.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;


@EnableEntityLinks // allow EntityLink object injection
@EnableHypermediaSupport(type = HypermediaType.HAL) // include HATEOAS with HAL
@SpringBootApplication // boilerplate config and component scan for injectables
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
