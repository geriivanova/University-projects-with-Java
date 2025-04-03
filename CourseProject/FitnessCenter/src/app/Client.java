package app;

import java.sql.*;
import java.awt.*;

public class Client {
    private int clientID;
    private String name;
    private int age;
    private String city;
    private String address;
    private String email;
    
    public Client(int id, String name, int age, String city, String address, String email)
    {
        clientID = id;
        this.name = name;
        this.age = age;
        this.city = city;
        this.address = address;
        this.email = email;
    }
    
    @Override 
    public String toString()
    {
        return name;
    }
    
    public Object[] toArray()
    {
        return new Object[]{clientID, name, age, city, address, email};
    }
    
    public int getID()
    {
        return clientID;
    }
    
    public String getCity()
    {
        return city;
    } 
    
    public String getName()
    {
        return name;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public String getAddress()
    {
        return address;
    }
    
     public String getEmail()
    {
        return email;
    }
    
}
