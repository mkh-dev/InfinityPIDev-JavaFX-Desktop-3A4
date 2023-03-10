/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import edu.pijava.model.categorie_prod;
import edu.pijava.model.produit;
import edu.pijava.services.categorie_prod_service;
import edu.pijava.services.produit_service;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class ModifyCatProdController implements Initializable {

    @FXML
    private TextField tfcatprod;
    @FXML
    private Button Modifier;
    private int id_cat;
    @FXML
    private Button btnrevenir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
/*
    @FXML
    private void Modifier(ActionEvent event) {
        String cat_prod = tfcatprod.getText();
    

    categorie_prod cp = new categorie_prod();
    cp.setCat_prod(cat_prod);
categorie_prod_service cps = new categorie_prod_service();

        cps.modifiercatprod(cat_prod);
   
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText(null);
alert.setContentText("Produit modifié!");
alert.show();

   try {
            Parent root = FXMLLoader.load(getClass().getResource("CategorieProd.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   */
    @FXML
private void Modifier(ActionEvent event) {
    String cat_prod = tfcatprod.getText();
    categorie_prod cp = new categorie_prod();
    cp.setCat_prod(cat_prod);

    categorie_prod_service cps = new categorie_prod_service();

    // Update the category using the service
    cps.modifiercatprod(id_cat,cat_prod);

    // Show a confirmation message
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText("Produit modifié!");
    alert.show();

    // Go back to the category list view
    try {
        Parent root = FXMLLoader.load(getClass().getResource("CategorieProd.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    void setCatProd(int id_cat) {
        this.id_cat = id_cat;  
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
        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
       
}
