package com.cnu2016.assignment2.app;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.cnu2016.assignment2.app.scheduler.HomeController;
import com.cnu2016.assignment2.app.scheduler.Scheduler;

/**
 * Starter class contains main function to execute the program
 */
public class Starter {
    public void fileReader(String stringPath) {
        Path path = Paths.get(stringPath);
        try {
            Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8);
            HomeController homeController = HomeController.getInstance();
            stream.forEach(e->homeController.readEvent(e));
            stream.close();
        } catch(IOException ex) {
            System.out.println("Unable to read file");
        }     
    }
    
    public void controller(String stringPath) {
        HomeController homeController = HomeController.getInstance();
        fileReader("/tmp/testfile.txt");
        Scheduler scheduler = Scheduler.getInstance();
        while(scheduler.executeNextEvent())
            homeController.displayStatus();
    }
    
    public static void main(String args[]) {
        Starter starter = new Starter();
        starter.controller("/projects/Assignment02/testfile.txt");
    }
}
