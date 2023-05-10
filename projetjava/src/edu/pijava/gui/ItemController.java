/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

//import static edu.pijava.gui.ListeController1.id_prod;

import edu.pijava.model.produit;
import edu.pijava.services.produit_service;
import edu.pijava.utils.MyConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.applet.Main;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class ItemController implements Initializable {
    private produit prod;

    @FXML
    private VBox vbox;
    @FXML
    private Label tfnomprod;
    @FXML
    private ImageView tfimg;
    @FXML
    private Text tfdesc;
    @FXML
    private Label tfprix;
    @FXML
    private Label tfquantite;
    @FXML
    private AnchorPane anchropane;
    private Label tfidprod;
    @FXML
    private Button ModifierProd;
    @FXML
    private Button DeleteProd;
    private produit produit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        anchropane.setOnMouseClicked(event -> {
        System.out.println("Clicked on item with ID: "  + anchropane.getId());
        // Do something with the ID
         ModifierProd(event);
     
    });
        
           
         DeleteProd.setOnAction(event -> {
         DeleteProd(event);
});
         
       
    }    
   
/*

    @FXML
    private void ModifierProd(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/pijava/gui/ModifyProduct.fxml"));
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
private void ModifierProd(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/ModifyProduct.fxml"));
        Parent root = loader.load();
        ModifyProductController controller = loader.getController();
        controller.setProduitId(Integer.parseInt(anchropane.getId())); // passer l'ID de l'item sélectionné au contrôleur de modification
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
 public void setData(produit prod) {
    this.produit = prod;
    tfnomprod.setText(prod.getNom_prod());
    tfdesc.setText(prod.getDescription());
    tfprix.setText(Double.toString(prod.getPrix()));
    tfquantite.setText(Integer.toString(prod.getQuantite()));
    anchropane.setId(Integer.toString(prod.getId_prod()));
    String imagePath = "file:///C:/xampp/htdocs/img/" + prod.getImage(); // chemin absolu sur Windows
    Image image = new Image(imagePath);
    if (image.isError()) {
        System.out.println("Image introuvable: " + imagePath);
    } else {
        this.tfimg.setImage(image);
    }
}



          
    private void ModifierProd(MouseEvent event) {
    }

  //Button Delete dans l'interface   
    @FXML
private void DeleteProd(ActionEvent event) {
    // Récupérer l'id du produit correspondant à l'item sélectionné
    int id_prod = Integer.parseInt(anchropane.getId());

    // Créer une boîte de dialogue de confirmation
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText("Supprimer ce produit ?");
    alert.setContentText("Êtes-vous sûr de vouloir supprimer ce produit ?");

    // Ajouter les boutons de confirmation et d'annulation
    ButtonType confirmerBtn = new ButtonType("Oui");
    ButtonType annulerBtn = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
    alert.getButtonTypes().setAll(confirmerBtn, annulerBtn);
// Appliquer le style CSS
alert.getDialogPane().getStyleClass().add("alert");

    // Attendre que l'utilisateur appuie sur l'un des boutons
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == confirmerBtn) {
        
        

        // Si l'utilisateur a confirmé la suppression, supprimer le produit correspondant à l'id
        if (supprimerProd1(id_prod)) {
            // Si la suppression a réussi, retirer l'item de la liste
            vbox.getChildren().remove(anchropane);
            
           // Appeler la méthode qui affiche les produits à nouveau
    
        } else {
            // Sinon, afficher un message d'erreur
            System.out.println("Erreur lors de la suppression du produit");
        }
    } else {
        // Si l'utilisateur a annulé la suppression, ne rien faire
    }
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

    
    
   Connection conn = MyConnection.getInstance().getConn();

     public boolean supprimerProd1(int id_prod) {
    String requete = "DELETE FROM produit WHERE id_prod = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id_prod);
        if (pst.executeUpdate() != 0) {
            System.out.println("Produit supprimé avec succès");
            return true;
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression du produit : " + ex.getMessage());
    }
    return false;
}



}
