package app;

import java.sql.*;
import java.awt.*;

public class Exercise {
    private int exerciseID;
    private String name;
    private String description;
    private int durationMins;
    private int caloriesBurned;
    
    public Exercise(int id, String name, String desc, int duration, int calories)
    {
        exerciseID = id;
        this.name = name;
        description = desc;
        durationMins = duration;
        caloriesBurned = calories;
    }
    
    @Override 
    public String toString()
    {
        return name;
    }
    
    public int getID(){
        return exerciseID;
    }
    
    public String getName(){
        return name;
    }
    
    public String getDescription(){
        return description;
    }
    
    public int getDuration(){
        return durationMins;
    }
    
    public int getCalories(){
        return caloriesBurned;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setDescription(String desc){
        description = desc;
    }
    
    public void setDuration(int mins){
        durationMins = mins;
    }
    
    public void setCalories(int cals){
        caloriesBurned = cals;
    }
}
