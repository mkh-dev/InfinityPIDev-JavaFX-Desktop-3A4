/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class BackController implements Initializable {

    /**
     * Initializes the controller class.
     */
        private BorderPane BorderPane;
    @FXML
    private Button tfcategorie;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
     private void loadUi(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui + ".fxml"));
        } catch (IOException ex) {
            ex.getMessage();
        }
        BorderPane.setCenter(root); 
    }

    @FXML
    private void categorie(ActionEvent event) {
        loadUi("catproduit");
    }

    
}
