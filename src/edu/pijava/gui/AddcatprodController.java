/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import edu.pijava.model.categorie_prod;
import edu.pijava.services.categorie_prod_service;
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
public class AddcatprodController implements Initializable {

    @FXML
    private TextField tfcatprod;
   
    @FXML
    private Button addcatprod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addcatprod(ActionEvent event) {
        
        String cat_prod = tfcatprod.getText();
        categorie_prod cp =new categorie_prod(cat_prod);
       categorie_prod_service cps =new categorie_prod_service();
        cps.ajoutercatprod(cp);
    }

    
}
