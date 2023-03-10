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
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class CategorieProdController implements Initializable {

    @FXML
    private HBox productScrollPane;
    @FXML
    private Button btnadd_cat_prod;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    
     private categorie_prod_service cps = new categorie_prod_service();
    private List<categorie_prod> catprod = new ArrayList<>();
    @FXML
    private TextField searchbox;
    @FXML
    private Button searchbtn;
    @FXML
    private VBox vbox;
    @FXML
    private Button btnhome;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        catprod = cps.affichercatprod();
         afficher_cat_prod();
         categorie_prod_service cps = new categorie_prod_service();
    }    

    @FXML
    private void btnadd_cat_prod(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("addcatprod.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
List<categorie_prod> list = new ArrayList<>();
public void afficher_cat_prod() {
        try {
            grid.getChildren().remove(0, list.size());
            list = cps.affichercatprod();
            int column = 0;
            int row = 1;
            for (int i = list.size()-1; i >= 0; i--) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item_cat_prod.fxml"));
                AnchorPane anchropane= fxmlLoader.load();
                Item_cat_prodController itemcatprodController = fxmlLoader.getController();
                itemcatprodController.setData(list.get(i));
                
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchropane, column, row);
             

                column++;
                //GridPane.setMargin(anchorpane, new Insets(10));
        
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
                
    }
    
}
/*
@FXML
private void searchbtn(ActionEvent event) {
    String searchText = searchbox.getText().toLowerCase();

    List<categorie_prod> searchResults = new ArrayList<>();
    for (categorie_prod cp : catprod) {
        if (cp.getCat_prod().toLowerCase().contains(searchText)) {
            searchResults.add(cp);
        }
    }

    // Afficher les résultats de la recherche
    grid.getChildren().clear();
    int column = 0;
    int row = 1;
    for (categorie_prod cp : searchResults) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("item_cat_prod.fxml"));
        AnchorPane anchropane;
        try {
            anchropane = fxmlLoader.load();
            Item_cat_prodController itemcatprodController = fxmlLoader.getController();
            itemcatprodController.setData(cp);
            if (column == 3) {
                column = 0;
                row++;
            }
            grid.add(anchropane, column, row);
            column++;
        } catch (IOException ex) {
            Logger.getLogger(CategorieProdController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
*/

@FXML
private void searchbtn(ActionEvent event) {
    String searchText = searchbox.getText().toLowerCase();

    List<categorie_prod> searchResults = new ArrayList<>();
    for (categorie_prod cp : catprod) {
        if (cp.getCat_prod().toLowerCase().contains(searchText)) {
            searchResults.add(cp);
        }
    }

    // Créer une nouvelle liste de nodes pour les résultats de la recherche
    List<Node> searchResultNodes = new ArrayList<>();
    for (categorie_prod cp : searchResults) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("item_cat_prod.fxml"));
        AnchorPane anchorPane;
        try {
            anchorPane = fxmlLoader.load();
            Item_cat_prodController itemController = fxmlLoader.getController();
            itemController.setData(cp);
            searchResultNodes.add(anchorPane);
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Remplacer les nodes actuels dans le VBox avec les résultats de la recherche
   vbox.getChildren().clear();
    vbox.getChildren().addAll(searchResultNodes);
}

    @FXML
    private void btnhome(ActionEvent event) {
         try {
            Parent root = FXMLLoader.load(getClass().getResource("Partenaire.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    }








