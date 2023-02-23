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
public class UpdateUserController implements Initializable {

    @FXML
    private TextField addPrenom;
    @FXML
    private TextField addNom;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField addEmail;
    @FXML
    private TextField addNumTel;
    @FXML
    private Button addValider;
    @FXML
    private PasswordField addPassword;
    @FXML
    private CheckBox ckUtilisateur;
    @FXML
    private CheckBox ckPartenaire;




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

  @FXML
    private void savePerson(ActionEvent event) {
        // Compteur de cases à cocher sélectionnées
        int checkedCount = 0;

        // Vérifier si la case à cocher utilisateur est sélectionnée
        if (ckUtilisateur.isSelected()) {
            checkedCount++;
        }

        // Vérifier si la case à cocher partenaire est sélectionnée
        if (ckPartenaire.isSelected()) {
            checkedCount++;
        }

        // Si plus d'une case à cocher est sélectionnée, afficher un message d'erreur
        if (checkedCount != 1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une et une seule case à cocher !");
            alert.showAndWait();
            return;
        }

        // Si une seule case à cocher est sélectionnée, continuer avec l'inscription
        if (validateFields()) {
            String prenom = addPrenom.getText();
            String nom = addNom.getText();
            String email = addEmail.getText();
            int numTel = Integer.parseInt(addNumTel.getText());

            LocalDate localDate = datePicker.getValue();
            Date dateNaissance = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            String password = addPassword.getText();

        // Récupérer le rôle utilisateur ou partenaire
        String userRole = ckUtilisateur.isSelected() ? "Utilisateur" : "Partenaire";

        Users u = new Users(prenom, nom, email, dateNaissance, numTel, userRole, password);
        UserService userCrud = new UserService();
        userCrud.modifierUtilisateur(u);
    }
}


private boolean validateFields() {
    if (addPassword.getText().isEmpty() ||
            addPrenom.getText().isEmpty() ||
            addNom.getText().isEmpty() ||
            datePicker.getValue() == null ||
            addEmail.getText().isEmpty() ||
            addNumTel.getText().isEmpty() ) {
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





