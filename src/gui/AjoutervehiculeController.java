/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Vehicule;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CRUDvehicule;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutervehiculeController implements Initializable {

    private TextField immatriculation_V;
    private TextField Disponibilite_v;
    private ComboBox<String> marque_v;
    private ComboBox<String> modele_v; 
    @FXML
    private Button Ajouter_v;
    @FXML
    private Button Annuler_v;
    @FXML
    private AnchorPane anchor_v;
    @FXML
    private Button Voir_v;
    @FXML
    private TextField immatriculation_aj;
    @FXML
    private TextField Disponibilite_aj;
    @FXML
    private ComboBox<String> marque_aj;
    @FXML
    private ComboBox<String> modele_aj;
    @FXML
    private AnchorPane anchor_voir_v;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 

        ObservableList<String> options = FXCollections.observableArrayList(
                "Mercedes",
                "Audi",
                "BMW",
                "Cotroen",
                "Clio"
        );
        marque_aj.setItems(options);

        ObservableList<String> options2 = FXCollections.observableArrayList(
                "2022",
                "2021",
                "2020",
                "2019"
        );
        modele_aj.setItems(options2); 
        
         Voir_v.setOnAction((event) -> {
            try {
                Parent newParent = FXMLLoader.load(getClass().getResource("Affichervehicule.fxml"));
                Scene newScene = new Scene(newParent);
                Stage currentStage = (Stage) anchor_voir_v.getScene().getWindow();
                currentStage.setScene(newScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            });

    }

    @FXML
private void save_voiture(ActionEvent event) { 

    String marque_v = marque_aj.getValue();
    String modele_v = modele_aj.getValue();
    String immatriculation = immatriculation_aj.getText();
    String Disponibilite = Disponibilite_aj.getText();
    
     Vehicule v = new Vehicule(6, marque_v, modele_v, immatriculation, Disponibilite);
     
     CRUDvehicule crud = new CRUDvehicule();
     crud.ajoutervehicule(v);
} 

 
}
