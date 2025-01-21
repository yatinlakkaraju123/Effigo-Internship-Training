package com.in28minutes.junit;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class MyMathTest extends TestCase {
    private final MyMath math = new MyMath();
    private final int[] numbers = {1,2,3};
public void testTest(){

    int result = math.CalculateSum(numbers);
    int expected = 6;
    assertEquals(expected,result);
    //fail("Not yet implemented");
}
    public void testTest1(){
        MyMath math = new MyMath();
        int[] numbers = {};
        int result = math.CalculateSum(numbers);
        int expected = 0;
        assertEquals(expected,result);
        //fail("Not yet implemented");
    }

    public void beforeEach(){
        System.out.println("before each");
    }
    public void afterEach(){
        System.out.println("after each");
    }

}