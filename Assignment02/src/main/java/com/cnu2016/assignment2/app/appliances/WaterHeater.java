package com.cnu2016.assignment2.app.appliances;

import com.cnu2016.assignment2.app.State;
import com.cnu2016.assignment2.app.scheduler.Scheduler;

/**
 * Class for the WaterHeater appliance
 */
public class WaterHeater extends Appliance{
    
    private static final int MAX_TIME_ON = 8;
    
    public WaterHeater() {
        super();
        name = "Heater";
    }
    
    /**
     * The water heater is set to switch off MAX_TIME_ON time units
     * after it is switched on.
     */
    @Override
    public void setState(State state) {
        if(this.state != state) {
            System.out.println("Changing state of Water Heater from " 
                + this.getState() + " to " + state);
            this.state = state;
            if(state == State.ON) {
                int time = Scheduler.getInstance().getCurrentTime();
                Scheduler.getInstance().addEvent(this, State.OFF, time 
                    + MAX_TIME_ON);
            }
        }
    }
}
