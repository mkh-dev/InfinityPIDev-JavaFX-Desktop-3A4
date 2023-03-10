/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.produit;
import Services.CRUDProduit;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author miguel
 */
public class AddProduitPopUpController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private ListView<produit> listView;
    @FXML
    private Button addButton;

    int prixTotal;

    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();

    List<produit> produitSelect = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        CRUDProduit crud = new CRUDProduit();
        List<produit> ListProduit = crud.afficherprod();
        listView.getItems().addAll(ListProduit);

        listView.setCellFactory(param -> new ListCell<produit>() {

            @Override
            protected void updateItem(produit item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getId_prod() == 0) {
                    setText(null);
                } else {

                    CheckBox checkProduit = new CheckBox();
                    checkProduit.setText(item.getNom_prod());

                    setGraphic(checkProduit);

                    checkProduit.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue) {
                            // La checkbox a été cochée
                            if (!produitSelect.contains(item)) {
                                produitSelect.add(item);
                                System.out.println("added");
                            }
                        } else {
                            if (produitSelect.contains(item)) {
                                produitSelect.remove(item);
                            }
                        }
                    });

                }
            }

        });

    }

    public Button getAddButton() {
        Button bouttonAjouter = addButton;

        addButton.setOnAction(event -> {

        });
        return bouttonAjouter;
    }

    public int getSelectedNumber() {
        prixTotal = 0;
        System.out.println(produitSelect.toString());
        for (produit p : produitSelect) {
            prixTotal += p.getPrix();
        }
        return prixTotal;
    }

    public List<String> ListNomTrans() {

        List<String> listC = new ArrayList<String>();
        try {
            String req = "SELECT type_transport FROM transport";
            Statement ste = conn.createStatement();
            ResultSet result = ste.executeQuery(req);

            while (result.next()) {

                String name = result.getString("type_transport");
                listC.add(name);
            }
            //System.out.println(listC);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listC;
    }

}
