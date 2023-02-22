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
public class AddProductController implements Initializable {

    @FXML
    private TextField tfnomprod;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tfquantite;
    @FXML
    private TextField tfnompart;
    @FXML
    private TextField tfimg;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField tfidcat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnajouter(ActionEvent event) {
        
        String nom_prod = tfnomprod.getText();
        String description = tfdesc.getText();
        double prix =Double.parseDouble(tfprix.getText());
        int quantite =Integer.parseInt(tfquantite.getText());
        String nom_part = tfnompart.getText();
        int id_cat =Integer.parseInt(tfidcat.getText());
        String image = tfimg.getText();
        
 
        produit p =new produit(nom_prod,description,prix,quantite,nom_part,id_cat,image);
        produit_service ps =new produit_service();
        ps.ajouterprod(p);
    }
    
}
