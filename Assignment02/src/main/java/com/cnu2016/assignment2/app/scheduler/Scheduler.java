package com.cnu2016.assignment2.app.scheduler;

import java.util.TreeMap;

import com.cnu2016.assignment2.app.ApplianceEvent;
import com.cnu2016.assignment2.app.State;
import com.cnu2016.assignment2.app.appliances.Appliance;

/**
 * Singleton class to manage and schedule events.
 * 
 * Stores the events that are to be executed, and executes
 * the next chronological event. Keeps track of the current
 * time.
 */
public class Scheduler {
    
    private static Scheduler scheduler = null;
    private int currentTime;
    
    /**
     * Holds the list of all events, sorted by time
     */
    private TreeMap<Integer, ApplianceEvent> eventList;
    
    /**
     * (private) Constructor for Scheduler class. 
     * Current time is set to 0
     */
    private Scheduler() {
        currentTime = 0;
        eventList = new TreeMap<Integer, ApplianceEvent>();
    }
    
    /**
     * Returns the singleton instance of the class
     */
    public static Scheduler getInstance() {
        if(scheduler == null) 
            scheduler = new Scheduler();
        return scheduler;
    }
    
    /**
     * Adds an event to the eventList
     */
    public void addEvent(Appliance appliance, 
        State nextState, int time) {
        ApplianceEvent applianceEvent = new ApplianceEvent(appliance, 
            time, nextState);
        if(!eventList.isEmpty() && eventList.firstKey() > currentTime) {
            //TODO: Throw an exception here
        } else {
            eventList.put(time, applianceEvent);
        }
    }
    
    /**
     * Executes the next chronological event (if it exists)
     * 
     * If no event exists, returns false
     * else executes the event, deletes it from the list, and returns true
     */
    public boolean executeNextEvent() {
        if(eventList.isEmpty())
            return false;
        ApplianceEvent executableEvent = eventList.firstEntry().getValue();
        executableEvent.executeEvent();
        this.currentTime = executableEvent.getTime();
        eventList.remove(this.currentTime);
        return true;
    }
    
    /**
     * Getter for current time. Since scheduler is the only object
     * controlling the time, we do not provide setter for this property
     */
    public int getCurrentTime() {
        return this.currentTime;
    }
    
}
