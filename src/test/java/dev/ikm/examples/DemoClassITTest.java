/*-
 * ========================LICENSE_START=================================
 * HelloTest.java - demo-java-maven Project - IKMDEV
 * %%
 * Copyright (C) 2024 IKMDEV
 * %%
 * IKM DEV license header
 * =========================LICENSE_END==================================
 */
package dev.ikm.examples;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;

public class DemoClassITTest {
	private int x = 6;
	
	@Test
    public void testNameMkyong() {
		
		String helloText = "Hello mkyong";

        assertEquals("Hello mkyong", helloText);
        
        DemoClass hello = new DemoClass();
        
        int z = hello.sum(3, 4);
        
        assertEquals(z, 7);

    }

	@Test
    public void testFail() {
		
		String helloText = "Hello mkyong";

        assertEquals("Hello mkyong", helloText);
        
        DemoClass hello = new DemoClass();
        
        int z = hello.multiply(3, 4);
        
        assertEquals(z, 12);

    }	

	@Test
	@Ignore
    public void testIgnore() {
		
		String helloText = "Hello mkyong";

        assertEquals("Hello mkyong", helloText);
        
        DemoClass hello = new DemoClass();
        
        float z = hello.divide(6, 2);
        
        assertEquals(z, 3);

    }	

	@Test
    public void testFail2() {
		
		String helloText = "Hello mkyong";

        assertEquals("Hello mkyong", helloText);
        
        DemoClass hello = new DemoClass();
        
        int z = hello.multiply(3, 4);
        
        assertEquals(z, 12);

    }	


	@Test
    public void testFail3() {
		
		String helloText = "Hello mkyong";

        assertEquals("Hello mkyong", helloText);
        
        DemoClass hello = new DemoClass();
        
        int z = hello.multiply(3, 4);
        
        assertEquals(z, 12);

    }	
	
}
