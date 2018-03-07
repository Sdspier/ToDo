/*
 * Identifier management class using the Spring HATEOAS framework
 */
package com.mycompany.sspier.code.rest.domain;


public interface Identifiable extends org.springframework.hateoas.Identifiable<Long> {
	public void setId(Long id);
}
