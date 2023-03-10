/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

//import entities.Transport;
import edu.pijava.model.Vehicule;
import java.net.URL;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import edu.pijava.services.CRUDvehicule;
import java.io.IOException;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ModifiervehiculeController implements Initializable {

    @FXML
    private AnchorPane modifier_v_anchorpane;
    @FXML
    private ComboBox<String> marque_mo_v;
    @FXML
    private ComboBox<String> model_mod_v;
    @FXML
    private TextField immatriculation_mod_v;
    @FXML
    private TextField disponibilte_mod_v;
    @FXML
    private Button retour_mod_v;
    @FXML
    private Button modifier_v;
    
    int dataSet=0;
    Vehicule vehicule;
    /*@FXML
    private Button retour_mod_v;*/
    
    @FXML
    void retour_mod_v_button(ActionEvent event) {
    }
    ;
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
        marque_mo_v.setItems(options);

        ObservableList<String> options2 = FXCollections.observableArrayList(
                "2022",
                "2021",
                "2020",
                "2019"
        );
        model_mod_v.setItems(options2);
        
        
        retour_mod_v.setOnAction(ev -> {
            try {
                Parent newParent = FXMLLoader.load(getClass().getResource("Affichervehicule.fxml"));
                Scene newScene = new Scene(newParent);
                Stage currentStage = (Stage) modifier_v_anchorpane.getScene().getWindow();
                currentStage.setScene(newScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
         
        
        
        // TODO
    }    
    
    
    
    @FXML
    private void getData() { 
        if (dataSet == 0) {
            Stage currentStage = (Stage) modifier_v_anchorpane.getScene().getWindow();
            this.vehicule = (Vehicule) currentStage.getUserData();

            /*remplissage des champs*/   
            marque_mo_v.setValue(this.vehicule.getMarque());
            model_mod_v.setValue(this.vehicule.getModele());
            immatriculation_mod_v.setText(this.vehicule.getImmatriculation());
            disponibilte_mod_v.setText(this.vehicule.getDisponibilite());
            
            dataSet = 1;
        } 
    }  

    private void modifier_vehicule_button(){
      
        String marque = marque_mo_v.getValue();
        String model = model_mod_v.getValue();
        String immatriculation = immatriculation_mod_v.getText();
        String Disponibilite = disponibilte_mod_v.getText();
        
        Vehicule v = new Vehicule(5, marque, model, immatriculation, Disponibilite);
        v.setId_vehicule(v.getId_vehicule());
        CRUDvehicule crud = new CRUDvehicule();
        crud.modifiervehicule(v);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succes");
        alert.setHeaderText(null);
        alert.setContentText("Vehicule modifiée !");
        alert.showAndWait();
         try {
            Parent newParent = FXMLLoader.load(getClass().getResource("Affichervehicule.fxml"));
            Scene newScene = new Scene(newParent);
            Stage currentStage = (Stage) modifier_v_anchorpane.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
    }

   
    @FXML
    private void modifier_v_button(ActionEvent event) {
        
        retour_mod_v.setOnAction(ev -> {
            try {
                Parent newParent = FXMLLoader.load(getClass().getResource("Affichervehicule.fxml"));
                Scene newScene = new Scene(newParent);
                Stage currentStage = (Stage) modifier_v_anchorpane.getScene().getWindow();
                currentStage.setScene(newScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        
    }
    
    
    
  
        
    
}
