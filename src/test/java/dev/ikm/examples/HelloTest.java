package dev.ikm.examples;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;

public class HelloTest {
	private int x = 6;
	
	@Test
    public void testNameMkyong() {
		
		String helloText = "Hello mkyong";

        assertEquals("Hello mkyong", helloText);
        
        Hello hello = new Hello();
        
        int z = hello.sum(3, 4);
        
        assertEquals(z, 7);

    }

	@Test
    public void testFail() {
		
		String helloText = "Hello mkyong";

        assertEquals("Hello mkyong", helloText);
        
        Hello hello = new Hello();
        
        int z = hello.multiply(3, 4);
        
        assertEquals(z, 12);

    }	

	@Test
	@Ignore
    public void testIgnore() {
		
		String helloText = "Hello mkyong";

        assertEquals("Hello mkyong", helloText);
        
        Hello hello = new Hello();
        
        float z = hello.divide(6, 2);
        
        assertEquals(z, 3);

    }	

	@Test
    public void testFail2() {
		
		String helloText = "Hello mkyong";

        assertEquals("Hello mkyong", helloText);
        
        Hello hello = new Hello();
        
        int z = hello.multiply(3, 4);
        
        assertEquals(z, 12);

    }	


	@Test
    public void testFail3() {
		
		String helloText = "Should fail";

        assertEquals("Should fail", helloText);
        
        Hello hello = new Hello();
        
        int z = hello.multiply(4, 4);
        
        assertEquals(z, 16);

    }	
	
}
