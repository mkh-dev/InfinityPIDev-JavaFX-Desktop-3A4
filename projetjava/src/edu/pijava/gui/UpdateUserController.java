package edu.pijava.gui;

import edu.pijava.model.Users;
import edu.pijava.services.UserService;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UpdateUserController implements Initializable {

    @FXML
    private TextField updatePrenom;
    @FXML
    private TextField updateNom;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField updateEmail;
    @FXML
    private TextField updateNumTel;
    @FXML
    private PasswordField updatePassword;
    @FXML
    private CheckBox ckUtilisateur;
    @FXML
    private CheckBox ckPartenaire;

    private UserService userService;
    private Users user;

   public void initData(Users user) {
    this.user = user;
    if (user != null) {
        updatePrenom.setText(user.getPrenom());
        updateNom.setText(user.getNom());
        datePicker.setValue(new Date(user.getDateNaissance().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        updateEmail.setText(user.getEmail());
        updateNumTel.setText(Integer.toString(user.getNumTel()));
        updatePassword.setText(user.getPassword());
        if (user.getUserRole().equals("utilisateur")) {
            ckUtilisateur.setSelected(true);
        } else if (user.getUserRole().equals("partenaire")) {
            ckPartenaire.setSelected(true);
        }
    
    }
}

    @FXML
    private void handleUpdateUser(ActionEvent event) {
        user.setPrenom(updatePrenom.getText());
        user.setNom(updateNom.getText());
        user.setDateNaissance(new Date(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()));
        user.setEmail(updateEmail.getText());
        user.setNumTel(Integer.parseInt(updateNumTel.getText()));
        user.setPassword(updatePassword.getText());

        if (ckUtilisateur.isSelected()) {
            user.setUserRole("utilisateur");
        } else if (ckPartenaire.isSelected()) {
            user.setUserRole("partenaire");
        } else {
            user.setUserRole("utilisateur");
        }

        // update the user in the database
        userService.modifierUtilisateur(user);

        // show a success message to the user
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification réussie");
        alert.setHeaderText(null);
        alert.setContentText("Le profil a été modifié avec succès !");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // initialize the UserService
        userService = new UserService();
        initData(user);
    }
}

