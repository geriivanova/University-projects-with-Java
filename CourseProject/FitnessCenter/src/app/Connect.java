package app;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import java.awt.*;
import javax.swing.*;

public class Connect {
    public Connection conn;
    public Statement stmt;
    public ResultSet rs;
    
    public Connect(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:FitnessCalculatorBD.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    public ArrayList<Client> loadClientsData(String filter)
    {
        ArrayList<Client> data = new ArrayList<Client>();
        
        String sql="Select ClientID, Name, Age, City, Address, Email from Clients";
        if(!filter.trim().equals(""))
        {
            sql += " where " + filter;
        }
        
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
               int clientID = rs.getInt("ClientID");
               String name = rs.getString("Name");
               int age = rs.getInt("Age");
               String city = rs.getString("City");
               String address = rs.getString("Address");
               String email = rs.getString("Email");
               Client c = new Client(clientID, name, age, city, address,email);
               data.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return data;
    }
    
    public ArrayList<Exercise> loadExercisesData(String filter)
    {
        ArrayList<Exercise> data = new ArrayList<Exercise>();
        
        String sql="Select ExerciseID, Name, Description, DurationMinutes, CaloriesBurned from Exercises";
        if(!filter.trim().equals(""))
        {
            sql += " where " + filter;
        }
        
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
               int exerciseID = rs.getInt("ExerciseID");
               String name = rs.getString("Name");
               String desc = rs.getString("Description");
               int mins = rs.getInt("DurationMinutes");
               int calories = rs.getInt("CaloriesBurned");
               Exercise e = new Exercise(exerciseID, name, desc, mins, calories);
               data.add(e);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return data;
    }
    
    public ArrayList<Food> loadFoodsData(String filter)
    {
        ArrayList<Food> data = new ArrayList<Food>();
        
        String sql="Select FoodID, Name, Description, ProteinAmount, CarbAmount, FatAmount from Foods";
        if(!filter.trim().equals(""))
        {
            sql += " where " + filter;
        }
        
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
               int foodID = rs.getInt("FoodID");
               String name = rs.getString("Name");
               String desc = rs.getString("Description");
               int pAmount = rs.getInt("ProteinAmount");
               int cAmount = rs.getInt("CarbAmount");
               int fAmount = rs.getInt("FatAmount");
               Food f = new Food(foodID, name, desc, pAmount, cAmount, fAmount);
               data.add(f);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return data;
    }
    
    public void insert(int clientID, String name, int age, String city, String address, String email) {
        String sql = "INSERT INTO Clients (ClientID, Name, Age, City, Address, Email) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clientID);
            stmt.setString(2, name);
            stmt.setInt(3, age);
            stmt.setString(4, city);
            stmt.setString(5, address);
            stmt.setString(6, email);

            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted into Clients.");
        } catch (SQLException e) {
            System.out.println("Error executing insert into Clients: " + e.getMessage());
        }
    }   
    
    public void select(){
        
        String sql = "SELECT Name, Gender, Age, Email FROM Clients";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
               System.out.println(rs.getString("Name") + " " + rs.getString("Gender") + " " + rs.getInt("Age") + " " + rs.getString("Email"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void delete(String name){
        String sql = "DELETE FROM Clients WHERE Name = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) 
        {
        stmt.setString(1, name); 
        int updatedRows = stmt.executeUpdate();
        System.out.println(updatedRows + " row(s) deleted into Clients.");
        } catch (SQLException e) {
            System.out.println("Error executing delete into Clients: " + e.getMessage());
        }
    }
    
    
    public void update(int id, String name, String description, int duration, int calories) {
    String sql = "UPDATE Exercises SET Name = ?, Description = ?, DurationMinutes = ?, CaloriesBurned = ? WHERE ExerciseID = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, name);         
        pstmt.setString(2, description);  
        pstmt.setInt(3, duration);       
        pstmt.setInt(4, calories);       
        pstmt.setInt(5, id);              

        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Exercise with ID " + id + " successfully changed.");
        } else {
            System.out.println("No found exercise with ID " + id + ".");
        }
    } catch (SQLException e) {
        System.out.println("Error with changing information: " + e.getMessage());
    }
}

    public ArrayList<String> selectWhere(String[] columnsArray, String table, String whereColumn, String whereValue){
        ArrayList data = new ArrayList<String>();
        String columns = String.join(", ", columnsArray);
        String sql = "SELECT " + columns + " FROM " + table 
                + " WHERE " + whereColumn + " LIKE '" + whereValue+"'";

        try{
            System.out.println(sql);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String row = "";
                for (int i = 0; i < columnsArray.length; i++) {
                    row = row + rs.getString(columnsArray[i])+"---";
                }
                row=row.substring(0, row.length()-3);
                data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
    
     public void close(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Throwable ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
