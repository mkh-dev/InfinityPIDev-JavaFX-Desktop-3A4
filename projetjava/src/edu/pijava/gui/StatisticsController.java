/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import edu.pijava.services.produit_service;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class StatisticsController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label lowQuantityProductsLabel;
    @FXML
    private Label totalProductsLabel;
     private produit_service ps;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ps = new produit_service();
         // Afficher le nombre total de produits
        int totalProducts = ps.getTotalProducts();
        totalProductsLabel.setText(String.valueOf(totalProducts));

        // Afficher le nombre de produits ayant une quantité inférieure à 20
        int lowQuantityProducts = ps.getLowQuantityProducts();
        lowQuantityProductsLabel.setText(String.valueOf(lowQuantityProducts));
    }    
    
}
