package com.cnu2016.assignment2.app;

import com.cnu2016.assignment2.app.scheduler.HomeController;
import com.cnu2016.assignment2.app.scheduler.Scheduler;


/**
 * Starter class contains main function to execute the program
 */
public class Starter {
    public static void main(String args[]) {
        HomeController homeController = new HomeController();
        homeController.readEvent("AC,4,ON");
        Scheduler scheduler = Scheduler.getInstance();
        while(scheduler.executeNextEvent())
            homeController.displayStatus();
    }
}
