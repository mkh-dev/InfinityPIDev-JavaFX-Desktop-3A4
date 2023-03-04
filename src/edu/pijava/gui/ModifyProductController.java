/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import edu.pijava.model.produit;
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
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class ModifyProductController implements Initializable {

    @FXML
    private HBox btnmodify;
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
    private TextField tfImg;
    @FXML
    private Button btnModify;
    @FXML
    private Button upload;
    @FXML
    private Button btnrevenir;
    private int id_prod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifyClicked(ActionEvent event) {
        
    String nom_prod = tfNom.getText();
    int quantite = Integer.parseInt(tfQuantite.getText());  
    int prix = Integer.parseInt(tfPrix.getText());
    int id_cat= Integer.parseInt(tfIdCat.getText());
    String description = tfDesc.getText();
    String image = tfImg.getText();


    produit p = new produit();
    p.setNom_prod(nom_prod);
    p.setQuantite(quantite);
    p.setPrix(prix);
    p.setId_cat(id_cat);
    p.setDescription(description);
    p.setImage(image);

produit_service ps = new produit_service();
ps.modifierprod(nom_prod, description, prix, quantite, id_cat, image, id_prod);

Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText(null);
alert.setContentText("Produit modifié!");
alert.show();

   try {
            Parent root = FXMLLoader.load(getClass().getResource("product.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    @FXML
    private void upload(ActionEvent event) {
    }

    @FXML
    private void btnrevenir(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("product.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void init(int id_prod) {
       this.id_prod=id_prod;
    }

   

    void setProduitId(int id_prod) {
this.id_prod = id_prod;    }
    
}
