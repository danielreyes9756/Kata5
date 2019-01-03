/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5p1;

/**
 *
 * @author danie
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5P1 {
    
    private Connection connect() {
        
        String url = "jdbc:sqlite:KATA5.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void selectAll(){
        String sql = "SELECT * FROM PEOPLE";
        try (Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + "\t" +
                                rs.getString("Name") + "\t" +
                                rs.getString("Apellido") + "\t" +
                                rs.getString("Departamento") + "\t");    
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void createTable() {
        
        String query = "CREATE TABLE IF NOT EXISTS EMAIL (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIL TEXT NOT NULL);";
        
        try {
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Kata5P1 app = new Kata5P1();
        app.createTable();
    }
}