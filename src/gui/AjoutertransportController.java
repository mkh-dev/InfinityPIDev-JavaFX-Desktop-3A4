
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Transport;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CRUDtransport;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutertransportController implements Initializable {

    @FXML
    private Button Ajouter;
    @FXML
    private Button Annuler;
    @FXML
    private TextField lieu_arriver_aj;
    @FXML
    private TextField heure_depart_aj;
    @FXML
    private ComboBox<String> type_transport_aj;
    @FXML
    private TextField lieu_depart_aj;
    @FXML
    private TextField prix_transport_aj;
    @FXML
    private DatePicker date_depart_aj;
    @FXML
    private Button voir_ajoute;
    @FXML
    private AnchorPane anchorajoutertransport;
    @FXML
    private Button suivant_t;
    @FXML
    private AnchorPane anchore_suivant_t;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> options = FXCollections.observableArrayList(
                "Moto",
                "Voiture",
                "Mini bus",
                "Grand Bus",
                "Camion"
        );
        type_transport_aj.setItems(options);

        voir_ajoute.setOnAction((event) -> {
            try {
                Parent newParent = FXMLLoader.load(getClass().getResource("affichertransport.fxml"));
                Scene newScene = new Scene(newParent);
                Stage currentStage = (Stage) anchorajoutertransport.getScene().getWindow();
                currentStage.setScene(newScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        });
        
        suivant_t.setOnAction((event) -> {
            try {
                Parent newParent = FXMLLoader.load(getClass().getResource("ajoutervehicule.fxml"));
                Scene newScene = new Scene(newParent);
                Stage currentStage = (Stage) anchore_suivant_t.getScene().getWindow();
                currentStage.setScene(newScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    private void savetransport(ActionEvent event) {

        //int id_transport = Integer.parseInt(id_transport.getText());
        String heure_depart = heure_depart_aj.getText();
        String date_depart = date_depart_aj.getValue().toString();
        String type_transport = type_transport_aj.getValue();
        String lieu_depart = lieu_depart_aj.getText();
        String lieu_arriver = lieu_arriver_aj.getText();
        float prix_transport = Float.parseFloat(prix_transport_aj.getText());

        Transport t = new Transport(5, heure_depart, date_depart, type_transport, lieu_depart, lieu_arriver, prix_transport);
        CRUDtransport crud = new CRUDtransport();

        crud.ajoutertransport(t);
        Stage stage = null;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutervehicule.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
