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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class ProductController implements Initializable {

    @FXML
    private HBox productScrollPane;
    @FXML
    private Button btnadd;
    @FXML
    private GridPane grid;
    
   // @FXML
   // private ListView<produit> prodlistview;

    /**
     * Initializes the controller class.
     */
    
    private produit_service ps = new produit_service();
    private List<produit> prod = new ArrayList<>();
    @FXML
    private Button searchbtn;
    @FXML
    private TextField searchbox;
    @FXML
    private VBox vbox;
    @FXML
    private Button btnhome;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prod = ps.afficherprod();
        afficher();
        produit_service ps = new produit_service();
        
        
        
        
    }    
    

    @FXML
    private void btnadd(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("addprod.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    produit p =new produit(); 
    List<produit> list = new ArrayList<>();
    public void afficher() {
        try {
            grid.getChildren().remove(0, list.size());

            list = ps.afficherprod();

            int column = 0;
            int row = 1;
            for (int i = list.size()-1; i >= 0; i--) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchropane= fxmlLoader.load();
                ItemController itemController = fxmlLoader.getController();
                itemController.setData(list.get(i));
                
                
          
                
                
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

    List<produit> searchResults = new ArrayList<>();
    for (produit p : prod) {
        if (p.getNom_prod().toLowerCase().contains(searchText)) {
            searchResults.add(p);
        }
    }

    // Afficher les résultats de la recherche
    grid.getChildren().clear();
    int column = 0;
    int row = 1;
    for (produit p : searchResults) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("item.fxml"));
        AnchorPane anchropane;
        try {
            anchropane = fxmlLoader.load();
            ItemController itemController = fxmlLoader.getController();
            itemController.setData(p);
            if (column == 3) {
                column = 0;
                row++;
            }
            grid.add(anchropane, column, row);
            column++;
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
   

    */
    
    
    @FXML
private void searchbtn(ActionEvent event) {
    String searchText = searchbox.getText().toLowerCase();

    List<produit> searchResults = new ArrayList<>();
    for (produit p : prod) {
        if (p.getNom_prod().toLowerCase().contains(searchText)) {
            searchResults.add(p);
        }
    }

    // Afficher les résultats de la recherche
    vbox.getChildren().clear();
    for (produit p : searchResults) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("item.fxml"));
            AnchorPane anchorpane = fxmlLoader.load();
            ItemController itemController = fxmlLoader.getController();
            itemController.setData(p);
            vbox.getChildren().add(anchorpane);
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
