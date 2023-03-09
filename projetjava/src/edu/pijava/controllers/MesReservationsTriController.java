/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.controllers;

import edu.pijava.model.Reservation;
import edu.pijava.services.ReservationCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author ouesl
 */
public class MesReservationsTriController implements Initializable {

    @FXML
    private ListView<String> listview;
    @FXML
    private Label fxnbre;
    @FXML
    private Button fxedit;
    @FXML
    private Button fxpayer;
    @FXML
    private Button refresh;
    @FXML
    private Button fxdelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReservationCrud rc = new ReservationCrud();
        ObservableList<String> ReservationList = FXCollections.observableArrayList(rc.afficherMesReservationsTri(2));
        listview.setItems(ReservationList);
        fxnbre.setText("Vous Avez " + ReservationList.size() +" réservations:");
        ;

    }    

 
    
}
