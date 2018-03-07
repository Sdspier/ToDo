/*
 * Test class
 */
package com.code.rest.test.run;

import com.code.rest.test.unit.ItemPostTest;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;


@WebAppConfiguration
public class TestRunner{

    @Test
    public void testPost() throws Exception{
      ItemPostTest ipt = new ItemPostTest();
      ipt.ItemSuccessfullyPosted();
      
    }
    
}