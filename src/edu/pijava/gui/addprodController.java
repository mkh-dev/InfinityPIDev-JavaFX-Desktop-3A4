/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import static edu.pijava.gui.TwilioSMS.ACCOUNT_SID;
import static edu.pijava.gui.TwilioSMS.AUTH_TOKEN;
import static edu.pijava.gui.TwilioSMS.FROM_NUMBER;
import static edu.pijava.gui.TwilioSMS.TO_NUMBER;
import edu.pijava.model.produit;
import edu.pijava.services.UploadServices;
import edu.pijava.services.produit_service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JFileChooser;




/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class addprodController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfQuantite;
    @FXML
    private TextField tfPrix;
    @FXML
    private TextField tfIdCat;
    @FXML
    private TextArea tfDesc;
    @FXML
    private TextField tfNomPart;
    @FXML
    private TextField tfImg;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button upload;

    
    
      // Set your Twilio account SID and auth token
    public static final String ACCOUNT_SID = "ACf785d947a6ff3386444c79acfae41e4d";
    public static final String AUTH_TOKEN = "60b0f0074f4c5b09d1d4468dac6d2048";

    // Set the phone number to send the message to
    public static final String TO_NUMBER = "+21650734421";

    // Set your Twilio phone number
    public static final String FROM_NUMBER = "+15673343235";
    
    
    /**
     * Initializes the controller class.
     */
    UploadServices uploadservices= new UploadServices();
    @FXML
    private Button btnrevenir;
    @FXML
    private Button importer;
    @FXML
    private Button back;
 
    
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
        
     // Envoyer un SMS si la quantité ajoutée est inférieure à 20
    if (quantite < 20) {
        // Initialize the Twilio client
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Send the message
        Message message = Message.creator(new PhoneNumber(TO_NUMBER), new PhoneNumber(FROM_NUMBER), "Attention, la quantité du produit " + nom_prod + " est inférieure à 20.").create();

        // Print the message SID
        System.out.println("Message SID: " + message.getSid());
        
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

  

    System.out.println("Message envoyé avec succès.");
    /*
        Twilio.init("ACCOUNT_SID", "AUTH_TOKEN");
        Message.creator(new PhoneNumber("NUMERO_DESTINATION"), new PhoneNumber("NUMERO_SOURCE"),
                "Attention, la quantité du produit " + nom_prod + " est inférieure à 20.").create();*/
    }
    
        // Réinitialiser les champs
        tfNom.clear();
        tfQuantite.clear();
        tfPrix.clear();
        tfIdCat.clear();
        tfDesc.clear();
        tfNomPart.clear();
        tfImg.clear();
        
        
}  
/*
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
    */
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
            //Path destination = Paths.get("C:/xampp/htdocs/img/");

            //chemin relatif du dossier ou seront stocké les images des produits
            Path destination = Paths.get("src/edu/pijava/gui/ProductData/Images");
            if (!destination.toFile().exists()) {
                destination.toFile().mkdirs();
            }

            // Copier le fichier sélectionné dans le répertoire de destination en utilisant la référence aléatoire pour le nom du fichier
            try {
                Files.copy(selectedFile.toPath(), destination.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Fichier copié avec succès !");

                // Obtenir le lien de l'emplacement de la copie de l'image
                String imagePath = destination.toUri().toString() + fileName;

                String chaine = imagePath;

                // Découpage de la chaîne à partir de "Data"
                int index = chaine.indexOf("ProductData");
                if (index == -1) {
                    System.out.println("La chaîne ne contient pas \"Data\"");
                    return;
                }
                String sousChaine = chaine.substring(index);

                // Ajouter le produit à la base de données avec le chemin d'accès de l'image
                produit p = new produit();
                produit_service ps = new produit_service();
                p.setNom_prod(tfNom.getText());
                p.setDescription(tfDesc.getText());
                p.setPrix(Double.parseDouble(tfPrix.getText()));
                p.setQuantite(Integer.parseInt(tfQuantite.getText()));
                p.setNom_part(tfNomPart.getText());
                p.setId_cat(Integer.parseInt(tfIdCat.getText()));

                p.setImage(sousChaine); // Stocker le nom du fichier dans la base de données
                //on ne stock pas le nom du fichier mais directement le chemin relatif au fichier. plus simple
                ps.ajouterprod(p);

                System.out.println("Produit ajouté avec succès !");

                
                

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

    @FXML
    private void importer(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
fileChooser.setTitle("Choisir un fichier CSV");
fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Fichier CSV", "*.csv")
);
File selectedFile = fileChooser.showOpenDialog(null);
// Si un fichier a été sélectionné, importer les données du fichier
 // Vérifier si un fichier a été sélectionné
    if (selectedFile != null) {
        // Lire les données du fichier CSV et les ajouter à la base de données
        try {
            BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String nom_prod = data[0];
                double prix = Double.parseDouble(data[1]);
                int quantite = Integer.parseInt(data[2]);
                 int id_cat = Integer.parseInt(data[3]);
                String description = data[4];
                
                String nom_part = data[5];
               
                String image = data[6];
                produit p = new produit(nom_prod, prix, quantite,id_cat,description, nom_part, image);
                produit_service ps = new produit_service();
                ps.ajouterprod(p);
            }
            reader.close();
            
            // Afficher un message de confirmation
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Importation réussie");
            alert.setHeaderText(null);
            alert.setContentText("Les données ont été importées avec succès !");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            
            // Afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de l'importation");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'importation des données : " + e.getMessage());
            alert.showAndWait();
        }
    }
}

    @FXML
    private void back(ActionEvent event) {
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
    


    

 



