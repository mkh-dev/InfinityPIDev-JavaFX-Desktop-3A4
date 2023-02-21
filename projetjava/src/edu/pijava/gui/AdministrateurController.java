/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author MALEK-ADMIN
 */
public class AdministrateurController implements Initializable {

    @FXML
    private BorderPane bpAdmin;
    @FXML
    private AnchorPane apAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(MouseEvent event) {
        bpAdmin.setCenter(apAdmin);
    }

    @FXML
    private void liste(MouseEvent event) {
        
            loadPage("liste");

    }

    @FXML
    private void modifications(MouseEvent event) {
                    loadPage("modifications");
    }

    @FXML
    private void reclamations(MouseEvent event) {
                    loadPage("reclamations");
    }
            private void loadPage (String page){
        Parent root=null;
        try {
            root= FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
bpAdmin.setCenter(root);
}
            

}
