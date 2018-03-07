/*
 * Test class
 */
package com.code.rest.test.run;

import com.mycompany.sspier.code.rest.test.unit.ItemPostTest;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;


@WebAppConfiguration
public class TestRunner{

    @Test
    public void testPost(){
      ItemPostTest ipt = new ItemPostTest();   
      
      
    }
    
}