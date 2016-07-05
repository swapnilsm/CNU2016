package com.cnu2016.assignment2.app.scheduler;

import java.util.HashMap;

import com.cnu2016.assignment2.app.State;
import com.cnu2016.assignment2.app.appliances.Appliance;
import com.cnu2016.assignment2.app.appliances.AirConditioner;
import com.cnu2016.assignment2.app.appliances.CookingOven;
import com.cnu2016.assignment2.app.appliances.WaterHeater;

/**
 * HomeController class is used to read events and schedule them
 * and displays the status of all appliances
 */
public class HomeController {
    /**
     * Maps the names of appliances to their object instances
     */
    private HashMap<String, Appliance> applianceMap;
    
    /**
     * Initializes appliance map by mapping names of appliances
     * to their respective Appliance object instances
     */
    public HomeController() {
        applianceMap = new HashMap<String, Appliance>();
        AirConditioner airConditioner = new AirConditioner();
        applianceMap.put(airConditioner.getName(), airConditioner);
        
        CookingOven cookingOven = new CookingOven();
        applianceMap.put(cookingOven.getName(), cookingOven);
        
        WaterHeater waterHeater = new WaterHeater();
        applianceMap.put(waterHeater.getName(), new WaterHeater());
    }
    
    /**
     * Reads the event as a string, parses the request and schedules it
     */
    public void readEvent(String event) {
        String[] eventComponents = event.split(",");
        Scheduler scheduler = Scheduler.getInstance();
        Appliance appliance = applianceMap.get(eventComponents[0]);
        int time = Integer.parseInt(eventComponents[1]);
        State nextState = State.valueOf(eventComponents[2]);
        scheduler.addEvent(appliance, nextState, time);
    }
    
    /**
     * Displays the status of all devices
     */
    public void displayStatus() {
        for(String applianceName: applianceMap.keySet()) {
            Appliance appliance = applianceMap.get(applianceName);
            appliance.displayStatus();
        }
    }
}
