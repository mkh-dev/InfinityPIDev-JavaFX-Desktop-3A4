/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Vehicule;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import services.CRUDvehicule;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AffichervehiculeController implements Initializable {

    @FXML
    private TableColumn<Vehicule, String> marque_af_v;
    @FXML
    private TableColumn<Vehicule, String> Modele_af_v;
    @FXML
    private TableColumn<Vehicule, String> Immatriculation_af_v;
    @FXML
    private TableColumn<Vehicule, String> Disponibilte_af_v;
    @FXML
    private TableView<Vehicule> tabaffichervoiture;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        CRUDvehicule crud = new CRUDvehicule();
        List<Vehicule> ListVehicule = crud.affichervehicule();
        
        marque_af_v.setCellFactory(TextFieldTableCell.forTableColumn());
        marque_af_v.setCellValueFactory(new PropertyValueFactory<>("marque"));
        
        Modele_af_v.setCellFactory(TextFieldTableCell.forTableColumn());
        Modele_af_v.setCellValueFactory(new PropertyValueFactory<>("modele"));
         
        Immatriculation_af_v.setCellFactory(TextFieldTableCell.forTableColumn());
        Immatriculation_af_v.setCellValueFactory(new PropertyValueFactory<>("immatriculation"));
        
        Disponibilte_af_v.setCellFactory(TextFieldTableCell.forTableColumn());
        Disponibilte_af_v.setCellValueFactory(new PropertyValueFactory<>("Disponibilite"));
        
        tabaffichervoiture.getItems().addAll(ListVehicule);
        ListVehicule.toString();
    }    
    
}
