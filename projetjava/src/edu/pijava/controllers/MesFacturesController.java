/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.controllers;

import edu.pijava.model.Facture;
import edu.pijava.services.FactureCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ouesl
 */
public class MesFacturesController implements Initializable {

    @FXML
    private TableColumn<Facture, String> fxnumres;
    @FXML
    private TableColumn<Facture, String> fxnetapayer;
    @FXML
    private TableView<Facture> tableview;
    @FXML
    private Label fxnbre;
      FactureCrud fc = new FactureCrud();
    @FXML
    private TableColumn<?, ?> fxiduser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fxnumres.setCellValueFactory(new PropertyValueFactory<>("numRes"));
        fxnetapayer.setCellValueFactory(new PropertyValueFactory<>("netApayer"));
        fxiduser.setCellValueFactory(new PropertyValueFactory<>("idUser"));

        ObservableList<Facture> FactureList = FXCollections.observableArrayList(fc.afficherMesFactures(2));
        tableview.setItems(FactureList);
        fxnbre.setText("Vous Avez  " + FactureList.size() + "      factures a payer:");
        
    }    
    
}
