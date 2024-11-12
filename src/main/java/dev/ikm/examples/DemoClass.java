/*-
 * ========================LICENSE_START=================================
 * Hello.java - demo-java-maven Project - IKMDEV
 * %%
 * Copyright (C) 2024 IKMDEV
 * %%
 * IKM DEV license header
 * =========================LICENSE_END==================================
 */
package dev.ikm.examples;

class DemoClass {
    public static void main(String []args) {
        System.out.println("Hello, Super Awesome World!");
    }
    
    public int sum(int x, int y) {
    	return x+y;
    }

    public int multiply(int x, int y) {
    	return x*y;
    }
    
    public int substract(int x, int y) {
    	return x-y;
    }
    
    public float divide(int x, int y) {
    	return x/y;
    }   

    public float dividebytwo(int x, int y) {
    	return (x+y)/2;
    } 
}
