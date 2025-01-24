package com.in28minutes.learn_spring_aop.aop.business;

import com.in28minutes.learn_spring_aop.aop.annotations.TrackTime;
import com.in28minutes.learn_spring_aop.aop.data.DataService1;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class BusinessService1 {
    DataService1 dataService1;

    public BusinessService1(DataService1 dataService1) {
        this.dataService1 = dataService1;
    }
    @TrackTime
    public int calculateMax(){
        int[] data = dataService1.retrieveData();
       return Arrays.stream(data).max().orElse(0);
    }
    // Business Logic
}
