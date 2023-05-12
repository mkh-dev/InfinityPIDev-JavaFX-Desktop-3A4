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
    private Button btnReserver1;
    @FXML
    private Button fxmesfactures;
    @FXML
    private Button fxtest;
     @FXML
    private Button deconnexionButton;
    private Button btEvent;
                 @FXML
    private Button btReclam;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setBtnReclam(btReclam);
    }

    @FXML
    public void handlebc1(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("/edu/pijava/gui/DetailsReservation.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(ReserverEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void handlebc2(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("/edu/pijava/gui/Login.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(ReserverEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void hadlebc5(ActionEvent event) {
        try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("/edu/pijava/gui/MesFactures.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(ReserverEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
@FXML
    private void test(ActionEvent event) {
          try {
            Parent sv;
            sv = (AnchorPane) FXMLLoader.load(getClass().getResource("/edu/pijava/gui/MesReservations2.fxml"));
            pane1.getChildren().removeAll();
            pane1.getChildren().setAll(sv);
        } catch (IOException ex) {
            Logger.getLogger(ReserverEventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    

    
    public void openReclam() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/pijava/gui/ReclamationsUtilisateur.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void setBtnReclam(Button btReclam) {
        this.btReclam = btReclam;
        this.btReclam.setOnAction(e -> {
            try {
                openReclam();
            } catch (IOException ex) {
               
            }
        });
    }
@FXML
    public void deconnexion(ActionEvent event) throws IOException {
        // Code pour déconnecter l'utilisateur de son compte
        // ...

        // Rediriger l'utilisateur vers la page de connexion
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) deconnexionButton.getScene().getWindow();
        stage.setScene(scene);
    }
}