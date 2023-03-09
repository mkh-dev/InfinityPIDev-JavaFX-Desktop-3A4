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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ouesl
 */
public class AjouterFactureController implements Initializable {

    @FXML
    private TextField fxiduser;
    @FXML
    private TextField fxnetapayer;
    @FXML
    private Button btnajouter;
    @FXML
    private Label fxmessage;
        static int id;

   public void init(int id) {
        this.id = id;
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(ActionEvent event) {
         if (validateFields()) {
            //System.out.println("save");
            int netApayer =  Integer.parseInt(fxnetapayer.getText());
            int idUser = Integer.parseInt(fxiduser.getText());
            Facture f = new Facture(id,netApayer,idUser);
            
            FactureCrud fc = new FactureCrud();
            fc.ajouterFacture(f);

           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Facture ajoutée!");
            alert.show();
        }
    }
    private boolean validateFields() {
        if (fxnetapayer.getText().isEmpty() || fxiduser.getText().isEmpty()) {
            /* bch tji fenetre okhra 
            Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return false;*/

            fxmessage.setText("Veuillez remplir tous les champs");
            fxmessage.setVisible(true);
            return false;
        }
        return true;
    }
    
}
