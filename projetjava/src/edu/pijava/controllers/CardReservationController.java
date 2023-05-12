/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.controllers;

import edu.pijava.model.Reservation;
import edu.pijava.services.ReservationCrud;
import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ouesl
 */
public class CardReservationController implements Initializable {

    @FXML
    private Label fxnumres;
    @FXML
    private Label fxnbplaces;
    @FXML
    private Label fxidevent;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnModifier;
       private AnchorPane pane1; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        
        
        
        
    }    
    public void setData(Reservation r){
    
    fxnumres.setText(Integer.toString(r.getNumRes()));
    fxnbplaces.setText(Integer.toString(r.getNbPlaces()));
    fxidevent.setText(Integer.toString(r.getIdEvent()));
 
    }

 public void handlebc3(ActionEvent event){
   
 }
    }

        
 

