package com.cnu2016.assignment2.app.appliances;

import static org.junit.Assert.assertEquals;

import com.cnu2016.assignment2.app.State;

/**
 * A collection of helper functions that perform state change and
 * no-state change tests for all applications
 * 
 * Does not contain standalone tests
 */
public class ApplianceTest {
        
    /**
     * Tests whether the application changes state or not
     */
    public void applianceStateChange(Appliance appliance, State state) {
        appliance.setState(state);
        assertEquals(appliance.getState(), state);
    }
    
    /**
     * Tests whether the appliance turns off -> on -> off 
     */
    public void basicApplianceTest(Appliance appliance) {
        applianceStateChange(appliance, State.OFF);
        applianceStateChange(appliance, State.ON);
        applianceStateChange(appliance, State.OFF);
    }
    
    /**
     * Tests whether the appliance turns off -> off -> on -> on
     * 
     * Basically checking if the application works when new state is 
     * the same as the old state 
     */
    public void noChangeTest(Appliance appliance) {
        applianceStateChange(appliance, State.OFF);
        applianceStateChange(appliance, State.OFF);
        applianceStateChange(appliance, State.ON);
        applianceStateChange(appliance, State.ON);
    }
}
