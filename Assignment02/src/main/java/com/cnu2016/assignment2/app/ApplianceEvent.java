package com.cnu2016.assignment2.app;

import com.cnu2016.assignment2.app.appliances.Appliance;

/**
 * ApplianceEvent class holds details for an event, and can
 * execute itself.
 */
public class ApplianceEvent {
    private Appliance appliance;
    private int time;
    private State state;
    
    public ApplianceEvent(Appliance appliance, int time, State state) {
        this.appliance = appliance;
        this.time = time;
        this.state = state;
    }
    
    public void executeEvent() {
        this.appliance.setState(this.state);
    }
    
    public int getTime() {
        return this.time;
    }
}
