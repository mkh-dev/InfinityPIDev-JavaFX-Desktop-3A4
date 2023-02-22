/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
/**
 *
 * @author HP
 */
public class MyConnection {
    private  Connection  conn;
   String url = "jdbc:mysql://localhost:3306/infinity";
   String user = "root";
   String pwd = "";
   private static MyConnection instance;
    private MyConnection() {
        
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie!!!");
        } catch (SQLException ex) {
            System.out.println("probleme de Connexion");    
        }}

  public static MyConnection getInstance(){
        if (MyConnection.instance == null) {
            MyConnection.instance = new MyConnection();
        }
        return MyConnection.instance;
        
    }
  public static Connection getConnection() {
        return MyConnection.getInstance().conn;
    }
}
