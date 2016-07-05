package com.cnu2016.assignment2.app.appliances;

import com.cnu2016.assignment2.app.State;

/**
 * Class for the CookingOven appliance
 */
public class CookingOven extends Appliance{
    
    public CookingOven() {
        super();
        name = "Oven";
    }
    
    @Override
    public void setState(State state) {
        if(this.state != state) {
            System.out.println("Changing state of Oven from " 
                + this.getState() + " to " + state);
            this.state = state;
        }
    }
}
