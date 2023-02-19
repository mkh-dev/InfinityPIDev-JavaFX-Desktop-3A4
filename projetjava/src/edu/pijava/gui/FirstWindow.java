/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import javafx.scene.image.Image;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author MALEK-ADMIN
 */
public class FirstWindow extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            Parent root= FXMLLoader.load(getClass().getResource("Login.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Evento");
            Image icon = new Image(getClass().getResourceAsStream("/images/logo.png"));
            primaryStage.getIcons().add(icon);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
