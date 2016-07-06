package com.cnu2016.assignment2.app.appliances;

import org.junit.Test;

/**
 * Tests whether the water heater passes the basic appliance and no-change
 * tests as specified in the ApplianceTest class
 */
public class WaterHeaterTest {

    @Test
    public void waterHeaterOnOffTests() {
        WaterHeater waterHeater = new WaterHeater();
        ApplianceTest applianceTest = new ApplianceTest();
        applianceTest.basicApplianceTest(waterHeater);
        applianceTest.noChangeTest(waterHeater);
    }
}
