package com.in28minutes.junit;

public class MyMath {

    public int CalculateSum(int[] numbers)
        {   int sum = 0;
            for(int number:numbers)
            {
                sum += number;
            }
            return sum;
        }
}
