package com.cnu2016.assignment2.app.appliances;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CookingOvenTest {
    
    /**
     * Tests whether the oven passes the basic appliance and no-change
     * tests
     */
    @Test
    public void cookingOvenOnOffTests() {
        CookingOven cookingOven = new CookingOven();
        ApplianceTest applianceTest = new ApplianceTest();
        applianceTest.basicApplianceTest(cookingOven);
        applianceTest.noChangeTest(cookingOven);
    }
}
