package com.pilyemaya.java5;

import org.junit.Assert;
import org.junit.Test;

public class VarargsTest {
	
	@Test
	public void sumTwoNumbersTest() {
		Assert.assertEquals(4, sumTwoNumbers(2,2));
		int[] numbers=new int[]{2,2};
		Assert.assertEquals(4,sumNumbersUsingAnArray(numbers));
		Assert.assertEquals(4, sumNumbersUsingVarargs(2,2));
	}
	
	@Test
	public void sumThreeNumbersTest() {
		Assert.assertEquals(6, sumThreeNumbers(2,2,2));
		int[] numbers=new int[]{2,2,2};
		Assert.assertEquals(6,sumNumbersUsingAnArray(numbers));
		Assert.assertEquals(6, sumNumbersUsingVarargs(2,2,2));
	}
	
	@Test
	public void sumSixNumbersTest() {
		Assert.assertEquals(12, sumSixNumbers(2,2,2,2,2,2));
		int[] numbers=new int[]{2,2,2,2,2,2};
		Assert.assertEquals(12,sumNumbersUsingAnArray(numbers));
		Assert.assertEquals(12, sumNumbersUsingVarargs(2,2,2,2,2,2));
	}
	
	
	public int sumTwoNumbers(int a, int b){
		return a+b;
	}
	
	public int sumThreeNumbers(int a, int b, int c){
		return a+b+c;
	}
	public int sumSixNumbers(int a, int b, int c, int d, int e, int f){
		return a+b+c+d+e+f;
	}
	
	public int sumNumbersUsingAnArray(int [] numbers){
		int total=0;
		for(int number:numbers){
			total+=number;
		}
		return total;
	}
	
	public int sumNumbersUsingVarargs(int... numbers){
		int total=0;
		for(int number:numbers){
			total+=number;
		}
		return total;
	}
	
	

}
