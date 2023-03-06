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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class CategorieProdController implements Initializable {

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
    private HBox productScrollPane;
    @FXML
    private Button btnadd_cat_prod;
    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    
     private categorie_prod_service cps = new categorie_prod_service();
    private List<categorie_prod> list = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list= cps.affichercatprod();
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
    
     
//List<categorie_prod> list = new ArrayList<>();
    public void afficher_cat_prod() {
        try {
            //grid.getChildren().remove(0, list.size());
            list = cps.affichercatprod();
            int column = 0;
            int row = 1;
            for (int i = 0; i < list.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item_cat_prod.fxml"));
                AnchorPane anchropane= fxmlLoader.load();
                Item_cat_prodController itemcatprodController = fxmlLoader.getController();
                itemcatprodController.setData1(list.get(i));
                
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
    
}}
