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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MALEK-ADMIN
 */
public class EvenementController implements Initializable {

    @FXML
    private BorderPane bpEvent;
    @FXML
    private AnchorPane apEvent;
 @FXML
    private Button deconnexionButton;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(MouseEvent event) {
        bpEvent.setCenter(apEvent);
    }

    @FXML
    private void myevent (MouseEvent event) {
        
            loadPage("MyEvent");

    }

    @FXML
    private void catevent (MouseEvent event) {
                    loadPage("CatEvent");
    }

            private void loadPage (String page){
        Parent root=null;
        try {
            root= FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
bpEvent.setCenter(root);
}
            
@FXML
    public void deconnexion(ActionEvent event) throws IOException {
        // Code pour déconnecter l'utilisateur de son compte
        // ...

        // Rediriger l'utilisateur vers la page de connexion
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) deconnexionButton.getScene().getWindow();
        stage.setScene(scene);
    }
}
