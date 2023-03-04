/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import edu.pijava.model.produit;
import edu.pijava.services.UploadServices;
import edu.pijava.services.produit_service;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.UUID;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
//import javafx.util.Duration;
//import javafx.geometry.Pos;
//import edu.pijava.gui.Notifications;



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
    @FXML
    private Button upload;

    /**
     * Initializes the controller class.
     */
    UploadServices uploadservices= new UploadServices();
    @FXML
    private Button btnrevenir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterClicked(ActionEvent event) {
    
        String nom_prod = tfNom.getText();
        String description = tfDesc.getText();
      //  String image = tfImg.getText();
        String FilenameInserver = uploadservices.upload(tfImg.getText());
        System.out.println("fins"+FilenameInserver);
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
        
  produit p =new produit(nom_prod,description,prix,quantite,nom_part,id_cat,tfImg.getText());
        produit_service ps =new produit_service();
        ps.ajouterprod(p); 
       // addNotifications("Succes", "produit ajouté");
        
        // Réinitialiser les champs
        tfNom.clear();
        tfQuantite.clear();
        tfPrix.clear();
        tfIdCat.clear();
        tfDesc.clear();
        tfNomPart.clear();
        tfImg.clear();
        
        
}  

  @FXML
    private void upload(ActionEvent event) {

// Générer une référence aléatoire pour le nom du fichier
String fileName = UUID.randomUUID().toString() + ".png";

// Créer une instance de FileChooser
FileChooser fileChooser = new FileChooser();

// Configurer le FileChooser pour ne sélectionner que les fichiers image
fileChooser.getExtensionFilters().addAll(
    new ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
);

// Afficher la boîte de dialogue de sélection de fichier
File selectedFile = fileChooser.showOpenDialog(null);

if (selectedFile != null) {
    // Définir le répertoire de destination
    Path destination = Paths.get("C:/xampp/htdocs/img");

    // Copier le fichier sélectionné dans le répertoire de destination en utilisant la référence aléatoire pour le nom du fichier
    try {
        Files.copy(selectedFile.toPath(), destination.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Fichier copié avec succès !");
    } catch (IOException ex) {
        System.out.println("Erreur lors de la copie du fichier : " + ex.getMessage());
    }
}
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
}
    /*
private void addNotifications(String title, String content) {

        if (null != content) {
            if (content.length() > 50) {
                content = content.substring(0, 49) + "......";
            }
        }
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(content)
                //.hideAfter(Duration.seconds(360))
                .position(Pos.BOTTOM_RIGHT);

        notificationBuilder.showInformation();
    }
    */

    



