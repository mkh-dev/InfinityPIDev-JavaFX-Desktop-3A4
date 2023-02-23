/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import edu.pijava.model.produit;
import edu.pijava.services.produit_service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class ThirdController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfQuantite;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfIdCat;
    @FXML
    private TextField tfDesc;
    @FXML
    private TextField tfNomPart;
    @FXML
    private TextField tfImg;
    @FXML
    private Button btnAjouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterClicked(ActionEvent event) {
   
        String nom_prod = tfNom.getText();
        String description = tfDesc.getText();
        String image = tfImg.getText();
        String nom_part =tfNomPart.getText();
       
       
     
       
       
        // Valider que les champs ne sont pas vides
        if (tfNom.getText().isEmpty() || tfQuantite.getText().isEmpty() || tfPrix.getText().isEmpty() || tfIdCat.getText().isEmpty() || tfDesc.getText().isEmpty() || tfNomPart.getText().isEmpty() || tfImg.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
           
        }
       
       
        // Valider que les champs numériques contiennent des valeurs numériques valides
        double prix;
        int quantite;
        int id_cat;
        try {
            prix = Double.parseDouble(tfPrix.getText());
            quantite = Integer.parseInt(tfQuantite.getText());
            id_cat = Integer.parseInt(tfIdCat.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Les champs Quantité, Prix et Id Catégorie doivent être des valeurs numériques.");
            alert.showAndWait();
            return;
        }
       
  produit p =new produit(nom_prod,description,prix,quantite,nom_part,id_cat,image);
        produit_service ps =new produit_service();
        ps.ajouterprod(p);  
       
        // Réinitialiser les champs
        tfNom.clear();
        tfQuantite.clear();
        tfPrix.clear();
        tfIdCat.clear();
        tfDesc.clear();
        tfNomPart.clear();
        tfImg.clear();
}  


}