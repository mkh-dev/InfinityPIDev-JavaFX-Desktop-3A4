/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import edu.pijava.model.produit;
import edu.pijava.services.produit_service;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class AddController implements Initializable {

    @FXML
    private TextField tfNomProd;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfQuantite;
    @FXML
    private TextField tfIdCat;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfImg;
    @FXML
    private Button btnAdd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddClicked(ActionEvent event) {
        String nom_prod = tfNomProd.getText();
        double prix =Double.parseDouble(tfPrix.getText());
        int quantite =Integer.parseInt(tfQuantite.getText());
        int id_cat =Integer.parseInt(tfIdCat.getText());
        String description = tfDescription.getText();
        String image = tfImg.getText();
 
        produit p =new produit(nom_prod,description,prix,quantite,id_cat,image);
        produit_service ps =new produit_service();
        ps.ajouterprod(p);
    }
    
}
