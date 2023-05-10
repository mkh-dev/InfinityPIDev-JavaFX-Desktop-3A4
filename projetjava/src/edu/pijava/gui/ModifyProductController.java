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

    // Récupérer les valeurs des champs
    String nom_prod = tfNom.getText();
    String quantiteText = tfQuantite.getText();
    String prixText = tfPrix.getText();
    String id_catText = tfIdCat.getText();
    String description = tfDesc.getText();
    String image = tfImg.getText();

    // Convertir les valeurs numériques en entiers
    int quantite = 0;
    if (!quantiteText.isEmpty()) {
        quantite = Integer.parseInt(quantiteText);
    }
    int prix = 0;
    if (!prixText.isEmpty()) {
        prix = Integer.parseInt(prixText);
    }
    int id_cat_prod= 0;
    if (!id_catText.isEmpty()) {
        id_cat_prod = Integer.parseInt(id_catText);
    }

    // Créer un objet produit avec les valeurs récupérées
    produit p = new produit();
    p.setNom_prod(nom_prod);
    p.setDescription(description);

    // Vérifier si les champs sont vides avant de les inclure dans la modification
    if (quantite != 0) {
        p.setQuantite(quantite);
    }
    if (prix != 0) {
        p.setPrix(prix);
    }
    if (id_cat_prod != 0) {
        p.setId_cat_prod(id_cat_prod);
    }

    // Appeler la méthode de modification dans le service de produit
    produit_service ps = new produit_service();
    ps.modifierprod(nom_prod, description, prix, quantite, id_cat_prod, id_prod);

    // Afficher une boîte de dialogue pour informer l'utilisateur que le produit a été modifié
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Information Dialog");
    alert.setHeaderText(null);
    alert.setContentText("Produit modifié!");
    alert.show();

    // Rediriger l'utilisateur vers la vue de liste de produits
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

    void setCatProdId(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
