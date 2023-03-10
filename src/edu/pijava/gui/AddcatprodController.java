/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import edu.pijava.model.categorie_prod;
import edu.pijava.services.categorie_prod_service;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    @FXML
    private Button btnrevenir;

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
        
        // Go back to the category list view
    try {
        Parent root = FXMLLoader.load(getClass().getResource("CategorieProd.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(CategorieProdController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        
         
    }

    @FXML
    private void btnrevenir(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CategorieProd.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieProdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
