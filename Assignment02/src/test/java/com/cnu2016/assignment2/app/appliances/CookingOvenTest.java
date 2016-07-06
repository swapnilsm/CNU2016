package com.cnu2016.assignment2.app.appliances;

import org.junit.Test;

/**
 * Tests whether the oven passes the basic appliance and no-change
 * tests as defined in ApplianceTest
 */
public class CookingOvenTest {
    
    @Test
    public void cookingOvenOnOffTests() {
        CookingOven cookingOven = new CookingOven();
        ApplianceTest applianceTest = new ApplianceTest();
        applianceTest.basicApplianceTest(cookingOven);
        applianceTest.noChangeTest(cookingOven);
    }
}
