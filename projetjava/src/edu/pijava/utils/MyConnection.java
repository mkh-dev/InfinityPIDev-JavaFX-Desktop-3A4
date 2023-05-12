/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MALEK-ADMIN
 */
public class MyConnection {
    private String url="jdbc:mysql://localhost:3306/pidev";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static MyConnection instance;

    private MyConnection(){
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx (){
        return cnx;
    }

    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }

    public Connection getConn(){
        return MyConnection.getInstance().getCnx();
    }
}


