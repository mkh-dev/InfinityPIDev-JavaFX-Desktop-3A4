/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.controllers;

import edu.pijava.model.Facture;
import edu.pijava.services.FactureCrud;
import java.awt.Color;
import static java.awt.Color.RED;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ouesl
 */
public class MesFacturesController implements Initializable {

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
    @FXML
    private Button fxretour;
    @FXML
    private TableColumn<Facture, Void> fxactions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();

    }

    private void loadData() {
        
   

        fxnetapayer.setCellValueFactory(new PropertyValueFactory<>("netApayer"));
        fxiduser.setCellValueFactory(new PropertyValueFactory<>("idUser"));

        ObservableList<Facture> FactureList = FXCollections.observableArrayList(fc.afficherMesFactures(2));
        tableview.setItems(FactureList);
        fxnbre.setText("Vous Avez  " + FactureList.size() + "      factures a payer:");

        fxactions.setCellFactory(new Callback<TableColumn<Facture, Void>, TableCell<Facture, Void>>() {
            @Override
            public TableCell<Facture, Void> call(final TableColumn<Facture, Void> param) {
                final TableCell<Facture, Void> cell = new TableCell<Facture, Void>() {
                    private final Button btn2 = new Button("Supprimer");
                        {
                             btn2.setOnAction((ActionEvent event) -> {
   
                            Facture f = getTableView().getItems().get(getIndex());
                             AjouterFactureController factureController = new AjouterFactureController();
                           factureController.init(f.getNumRes());

                            // Suppression alert
                            FactureCrud fc = new FactureCrud();
                            fc.supprimerFacture(f.getIdFacture());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("Facture supprimée!");
                            alert.show();
                            
                            // Rafraîchir la table des reservations
                            tableview.setItems(FXCollections.observableArrayList(fc.afficherMesFactures(2)));
                        
                                     });
                                     }
                                     
             
               
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        HBox managebtn = new HBox(btn2);
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(managebtn);}
                        }
                    };
          return cell;
            }
        });
   
       
                        
                        
    
     }
    
    @FXML
    private void back(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/ReserverEvent.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             ReserverEventController controller = loader.getController();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.showAndWait();
    }
    
}

 
  
    

