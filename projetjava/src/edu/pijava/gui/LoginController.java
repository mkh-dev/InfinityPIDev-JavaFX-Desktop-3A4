/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rimbs
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tflogin;
    @FXML
    private TextField tfpassword;
    @FXML
    private Button tfconnect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void connect(ActionEvent event) {
          if((tflogin.getText().equals("rim"))&&(tfpassword.getText().equals("rim")))
        {   try {
            Parent root = FXMLLoader.load(getClass().getResource("front.fxml"));
            //Scene scene = new Scene(root, 1100, 650);
            Scene scene = new Scene(root);//fhemtha faza edhyka imchi hajet tefha le:p hhh
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
            System.out.println("ok");
            } catch (IOException ex) {
               ex.getMessage();
            }
        }
     if((tflogin.getText().equals("admin"))&&(tfpassword.getText().equals("admin")))
        {   try {
            Parent root = FXMLLoader.load(getClass().getResource("back.fxml"));
            //Scene scene = new Scene(root, 1100, 650);
            Scene scene = new Scene(root);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
            System.out.println("ok");
            } catch (IOException ex) {
               ex.getMessage();
            }
        }
    }
    
}
