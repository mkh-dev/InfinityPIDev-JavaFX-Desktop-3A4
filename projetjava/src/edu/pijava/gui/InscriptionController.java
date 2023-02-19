/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import edu.pijava.model.Users;
import edu.pijava.services.UserService;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MALEK-ADMIN
 */
public class InscriptionController implements Initializable {


    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfNumTel;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private PasswordField pfConfirmerPassword;
    @FXML
    private Button btnValider;
    @FXML
    private CheckBox ckUtilisateur;
    @FXML
    private CheckBox ckPartenaire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  @FXML
private void savePerson(ActionEvent event) {
    if (validateFields()) {
        String prenom = tfPrenom.getText();
        String nom = tfNom.getText();
        String email = tfEmail.getText();
        int numTel = Integer.parseInt(tfNumTel.getText());

        LocalDate localDate = datePicker.getValue();
        Date dateNaissance = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String password = pfPassword.getText();
        String confirmerMotDePasse = pfConfirmerPassword.getText();

        if (!password.equals(confirmerMotDePasse)) {
            // Les mots de passe ne correspondent pas
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Les mots de passe ne correspondent pas !");
            alert.showAndWait();
            return;
        }

        Users u = new Users(prenom, nom, email, dateNaissance, numTel, "user", password);
        UserService userCrud = new UserService();
        userCrud.ajouterUtilisateur2(u);
    }
}

private boolean validateFields() {
    if (tfPrenom.getText().isEmpty() ||
        tfNom.getText().isEmpty() ||
        datePicker.getValue() == null ||
        tfEmail.getText().isEmpty() ||
        tfNumTel.getText().isEmpty() ||
        pfPassword.getText().isEmpty() ||
        pfConfirmerPassword.getText().isEmpty()) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs !");
        alert.showAndWait();
        return false;
    }

    LocalDate dateNaissance = datePicker.getValue();
    if (dateNaissance.isAfter(LocalDate.now())) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une date de naissance valide !");
        alert.showAndWait();
        return false;
    }

    return true;
}
}