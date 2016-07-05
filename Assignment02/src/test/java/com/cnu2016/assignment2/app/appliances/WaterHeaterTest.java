package com.cnu2016.assignment2.app.appliances;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class WaterHeaterTest {
    /**
     * Tests whether the water heater passes the basic appliance and no-change
     * tests
     */
    @Test
    public void waterHeaterOnOffTests() {
        WaterHeater waterHeater = new WaterHeater();
        ApplianceTest applianceTest = new ApplianceTest();
        applianceTest.basicApplianceTest(waterHeater);
        applianceTest.noChangeTest(waterHeater);
    }
}
