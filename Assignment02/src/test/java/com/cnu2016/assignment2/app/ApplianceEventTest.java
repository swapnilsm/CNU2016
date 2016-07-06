package com.cnu2016.assignment2.app;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import com.cnu2016.assignment2.app.appliances.Appliance;
import com.cnu2016.assignment2.app.appliances.WaterHeater;

/**
 * Tests whether appliance events are created properly or not
 */
public class ApplianceEventTest {
    @Test
    public void evaluatesApplianceEvent() {
        Appliance appliance = new WaterHeater();
        Random random = new Random();
        int time = random.nextInt(10) + 1;
        State state = State.ON;
        ApplianceEvent applianceEvent = new ApplianceEvent(appliance, 
            time, state);
        assertEquals(applianceEvent.getTime(), time);
        assertEquals(applianceEvent.getAppliance(), appliance);
        assertEquals(applianceEvent.getState(), state);
    }
}
