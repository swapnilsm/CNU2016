package com.cnu2016.assignment2.app.scheduler;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import com.cnu2016.assignment2.app.State;
import com.cnu2016.assignment2.app.appliances.*;
import org.junit.Test;

public class HappyTest {
    /**
     * Tests one happy test case which should work
     */
    @Test
    public void oneHappyTest() {
        HomeController homeController = new HomeController();
        HashMap<String, Appliance> applianceMap = homeController.getApplianceMap();
        Appliance airConditioner = applianceMap.get("AC");
        
        // Resetting the airConditioner
        airConditioner.setState(State.OFF);
        
        // Then we generate an event
        homeController.readEvent("AC,4,ON");
        
        // All events are executed
        Scheduler scheduler = Scheduler.getInstance();
        while(scheduler.executeNextEvent())
            homeController.displayStatus();
            
        // The new state of the AC is found
        State newState = airConditioner.getState();
        
        // Testing the new state and the current time
        assertEquals(newState, State.ON);
        assertEquals(scheduler.getCurrentTime(), 4);
    }

}
