package app;

import java.sql.*;
import java.awt.*;

public class Food {
    private int foodID;
    private String name;
    private String Description;
    private int proteinAmount;    
    private int carbAmount;
    private int fatAmount;
    
    public Food(int id, String name, String desc, int pAmount, int cAmount, int fAmount)
    {
        foodID = id;
        this.name = name;
        Description = desc;
        proteinAmount = pAmount;
        carbAmount = cAmount;
        fatAmount = fAmount;
    }
    
    @Override 
    public String toString()
    {
        return name;
    }
    
    public Object[] toArray()
    {
        return new Object[]{foodID, name, Description, proteinAmount, carbAmount, fatAmount};
    }
    
    public int getID(){
        return foodID;
    }
    
    public String getName(){
        return name;
    }
    
    public String getDesc(){
        return Description;
    }
    
    public int getPAmount(){
        return proteinAmount;
    }
    
    public int getCAmount(){
        return carbAmount;
    }
    
    public int getFAmount(){
        return fatAmount;
    }
}
