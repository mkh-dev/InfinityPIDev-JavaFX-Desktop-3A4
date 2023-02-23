/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.controllers;

import com.mysql.jdbc.StringUtils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import edu.pijava.model.Reservation;
import edu.pijava.services.ReservationCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ouesl
 */
public class DetailsReservationController implements Initializable {

    @FXML
    private AnchorPane pane2;
    private TextField fxnumres;
    @FXML
    private TextField fxidevent;
    @FXML
    private TextField fxnbreplaces;
    @FXML
    private TextField fxiduser;
    @FXML
    private Button btnreserver;
    @FXML
    private Label fxmessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    public void setFxnumres(String nom) {
        this.fxnumres.setText(nom);
    }

    public void setFxidevent(String prenom) {
        this.fxidevent.setText(prenom);
    }

    public void setFxnbreplaces(String prenom) {
        this.fxnbreplaces.setText(prenom);
    }

    public void setFxiduser(String prenom) {
        this.fxiduser.setText(prenom);
    }
    
    
    

    @FXML
    private void save(ActionEvent event) {
     if (validateFields()) {
         
        int idUser = Integer.parseInt(fxiduser.getText());
        int nbrePlaces = Integer.parseInt(fxnbreplaces.getText());
        int idEvent = Integer.parseInt(fxidevent.getText());
        Reservation r = new Reservation(idUser, nbrePlaces, idEvent);
        ReservationCrud rc = new ReservationCrud();
        rc.ajouterReservation(r);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Réservation confirmée!");
        alert.show();
     }
    }
    
    private boolean validateFields() {
    if (fxnumres.getText().isEmpty()
                || fxidevent.getText().isEmpty()
                || fxiduser.getText().isEmpty()) {
            /* bch tji fenetre okhra 
            Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return false;*/

            fxmessage.setText("Veuillez remplir tous les champs");
            fxmessage.setVisible(true);
            return false;
        }
        return true;
    }
    
   

}

