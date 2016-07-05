package com.cnu2016.assignment2.app.appliances;

import com.cnu2016.assignment2.app.State;

/**
 * Abstract class for all appliances
 */
public abstract class Appliance {
    protected State state;
    protected String name;

    protected Appliance() {
        state = State.OFF;
    }
    
    public State getState() {
        return this.state;
    }

    /**
     * Since different appliances change states in different ways
     * we keep this abstract
     */
    public abstract void setState(State state);
    
    public String getName() {
        return this.name;
    }
    
    public void displayStatus() {
        System.out.println("Appliance " + this.name + " is " + this.state);
    }
}
