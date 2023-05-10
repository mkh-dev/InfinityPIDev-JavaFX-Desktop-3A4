/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import edu.pijava.model.categorie_prod;
import edu.pijava.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class Item_cat_prodController implements Initializable {
    private categorie_prod catprod;

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Label tfnomcatprod;
    @FXML
    private Button DeleteCatProd;
    private HBox hbox;
    @FXML
    private Button DeleteCatProd1;
    @FXML
    private Button modifiercatprod;
    @FXML
    private VBox vboxitem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

    @FXML
    private void DeleteCatProd(ActionEvent event) {
         // Récupérer l'id du produit correspondant à l'item sélectionné
    int id_cat_prod = Integer.parseInt(anchorpane.getId());

    // Créer une boîte de dialogue de confirmation
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText("Supprimer cette catégorie ?");
    alert.setContentText("Êtes-vous sûr de vouloir supprimer cette catégorie ?");

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
        if (supprimercatprod1(id_cat_prod)) {
            // Si la suppression a réussi, retirer l'item de la liste
            anchorpane.getChildren().remove(anchorpane);
            
           // Appeler la méthode qui affiche les produits à nouveau
    
        } else {
            // Sinon, afficher un message d'erreur
            System.out.println("Erreur lors de la suppression du categorie");
        }
    } else {
        // Si l'utilisateur a annulé la suppression, ne rien faire
    }
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
    
       Connection conn = MyConnection.getInstance().getConn();

    //categorie_prod cp=new categorie_prod();
          //Mèthode pour supprimer categorie produit 
    public boolean supprimercatprod1(int id_cat_prod) {

        String requete = "DELETE FROM categorie_prod WHERE id_cat_prod = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id_cat_prod);
        if (pst.executeUpdate() != 0) {
            System.out.println("Produit supprimé avec succès");
            return true;
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression du produit : " + ex.getMessage());
    }
    return false;
    }
    
    
    
    

    void setData(categorie_prod catprod) {
     this.catprod = catprod;
        tfnomcatprod.setText(catprod.getCat_prod());
        anchorpane.setId(Integer.toString(catprod.getId_cat_prod()));
    }

    @FXML
    private void handleItemClick(MouseEvent event) {
        AnchorPane clickedAnchorPane = (AnchorPane) event.getSource();
    String id = clickedAnchorPane.getId();
    System.out.println("Clicked on item with ID: " + id);
    }
    
     private void modifiercatprod(MouseEvent event) {
    }

    @FXML
    private void modifiercatprod(ActionEvent event) {
          try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/ModifyCatProd.fxml"));
        Parent root = loader.load();
        ModifyCatProdController controller = loader.getController();
        controller.setCatProd(Integer.parseInt(anchorpane.getId())); // passer l'ID de l'item sélectionné au contrôleur de modification
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(CategorieProdController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
