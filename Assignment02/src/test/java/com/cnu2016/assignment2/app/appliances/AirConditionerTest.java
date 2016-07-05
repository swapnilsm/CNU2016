package com.cnu2016.assignment2.app.appliances;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cnu2016.assignment2.app.State;

public class AirConditionerTest {
    
    /**
     * Tests whether the AC passes the basic appliance and no change
     * tests
     */
    @Test
    public void airConditionerOnOffTests() {
        AirConditioner airconditioner = new AirConditioner();
        ApplianceTest applianceTest = new ApplianceTest();
        applianceTest.basicApplianceTest(airconditioner);
        applianceTest.noChangeTest(airconditioner);
    }
}
