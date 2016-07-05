package com.cnu2016.assignment2.app.appliances;

import com.cnu2016.assignment2.app.State;

/**
 * Class for the AirConditioner Appliance
 */
public class AirConditioner extends Appliance{

    public AirConditioner() {
        super();
        name = "AC";
    }
    
    @Override
    public void setState(State state) {
        if(this.state != state) {
            System.out.println("Changing state of AC from " 
                + this.getState() + " to " + state);
            this.state = state;
        }
    }
}
