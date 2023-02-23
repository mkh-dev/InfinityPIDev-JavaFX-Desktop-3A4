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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author ouesl
 */
public class ReserverEventController implements Initializable {

    @FXML
    private Button btnReserver;
    
    @FXML
    private AnchorPane pane1; 
    @FXML
    private Button fxmesresrvations;
    @FXML
    private Button btnReserver1;
    @FXML
    private Button fxmesfactures;
    @FXML
    private Button fxtest;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML 
    public void handlebc1(ActionEvent event)
    {
        try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("/edu/pijava/gui/DetailsReservation.fxml"));
          pane1.getChildren().removeAll() ; 
          pane1.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(ReserverEventController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
  
       @FXML 
    public void handlebc2(ActionEvent event)
    {
        try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("/edu/pijava/gui/MesReservations.fxml"));
          pane1.getChildren().removeAll() ; 
          pane1.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(ReserverEventController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }

    @FXML
    private void hadlebc5(ActionEvent event) {
        try
        {
            Parent sv ;
            sv = (AnchorPane)FXMLLoader.load(getClass().getResource("/edu/pijava/gui/MesFactures.fxml"));
          pane1.getChildren().removeAll() ; 
          pane1.getChildren().setAll(sv) ;                              
        } catch (IOException ex) {
             Logger.getLogger(ReserverEventController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        
        
    }

    @FXML
    private void test(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/InterfaceTest.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             ReserverEventController controller = loader.getController();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.showAndWait();
    }
}
