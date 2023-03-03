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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ouesl
 */
public class MesReservations2Controller implements Initializable {

    @FXML
    private ListView<Reservation> listview;
    @FXML
    private Label fxnbre;
    @FXML
    private Button fxdelete;
    @FXML
    private Button fxedit;
    @FXML
    private Button fxpayer;
    @FXML
    private Button refresh;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReservationCrud rc = new ReservationCrud();
        ObservableList<Reservation> ReservationList = FXCollections.observableArrayList(rc.afficherMesReservations(10));
        listview.setItems(ReservationList);
        fxnbre.setText("Vous Avez " + ReservationList.size() +" réservations:");
        ;

    }

    @FXML
    private void delete(ActionEvent event) {
        ReservationCrud rc = new ReservationCrud();
        ObservableList<Reservation> ReservationList = FXCollections.observableArrayList(rc.afficherMesReservations(2));
        Reservation selectedRes = listview.getSelectionModel().getSelectedItem();
        if (selectedRes != null) {
            rc.annulerReservation(selectedRes.getNumRes());
            ReservationList.remove(selectedRes);
            fxnbre.setText("Total des reservations : " + ReservationList.size());

            // Afficher l'alerte pour indiquer que la suppression a été effectuée avec succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Suppression réussie");
            alert.setHeaderText(null);
            alert.setContentText("La réservation a été annulée avec succès !");
            alert.showAndWait();
        }
    }

    @FXML
    private void edit() throws IOException {
        Reservation selectedRes = listview.getSelectionModel().getSelectedItem();
        if (selectedRes == null) {
            // Afficher un message d'erreur pour informer l'utilisateur qu'il doit sélectionner un utilisateur avant de pouvoir le modifier
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une réservation à modifier.");
            alert.showAndWait();
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/ModifierReservation.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ModifierReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModifierReservationController modifierReservationController = loader.getController();
        modifierReservationController.init(selectedRes.getNumRes());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        // Rafraîchir la liste des utilisateurs après la mise à jour
        refresh();
    }

    @FXML
    private void save(ActionEvent event) {
        
                         FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/AjouterFacture.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                              AjouterFactureController controller = loader.getController();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.showAndWait();
        
        
        
    }

    @FXML
    private void refresh() {

       ReservationCrud rc = new ReservationCrud();
        ObservableList<Reservation> ReservationList = FXCollections.observableArrayList(rc.afficherMesReservations(10));
        listview.setItems(ReservationList);
        fxnbre.setText("Vous Avez  " + ReservationList.size() + "      réservations:");
        ;

    }

}
