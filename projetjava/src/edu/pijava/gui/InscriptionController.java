package edu.pijava.gui;

import edu.pijava.model.Users;
import edu.pijava.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class InscriptionController implements Initializable {
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String NUMTEL_REGEX = "^\\d{8}$";

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
    @FXML
    private Hyperlink hlinkLogin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hlinkLogin.setStyle("-fx-text-fill: black;");

    }

    @FXML
    private void savePerson() {
        if (!validateFields()) {
            return;
        }

        int checkedCount = 0;

        if (ckUtilisateur.isSelected()) {
            checkedCount++;
        }

        if (ckPartenaire.isSelected()) {
            checkedCount++;
        }

        if (checkedCount != 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une et une seule case à cocher !");
            alert.showAndWait();
            return;
        }

        String prenom = tfPrenom.getText();
        String nom = tfNom.getText();
        String email = tfEmail.getText();
        int numTel = Integer.parseInt(tfNumTel.getText());

        LocalDate localDate = datePicker.getValue();
        Date dateNaissance = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String password = pfPassword.getText();
        String confirmerMotDePasse = pfConfirmerPassword.getText();

        if (!password.equals(confirmerMotDePasse)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Les mots de passe ne correspondent pas !");
            alert.showAndWait();
            return;
        }

        String userRole = ckUtilisateur.isSelected() ? "Utilisateur" : "Partenaire";

        Users u = new Users(prenom, nom, email, dateNaissance, numTel, userRole, password);
        UserService userCrud = new UserService();
        userCrud.ajouterUtilisateur2(u);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inscription réussie");
        alert.setHeaderText(null);
        alert.setContentText("Votre inscription a été enregistrée !");
        alert.showAndWait();
    }

    private boolean validateFields() {
        if (tfPrenom.getText().isEmpty() ||
                tfNom.getText().isEmpty() ||
                datePicker.getValue() == null ||
                tfEmail.getText().isEmpty() ||
                tfNumTel.getText().isEmpty() ||
                pfPassword.getText().isEmpty() ||
                pfConfirmerPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs obligatoires.");
            alert.showAndWait();
            return false;
            } else if (!tfEmail.getText().matches(EMAIL_REGEX)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Format email incorrect");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir une adresse email valide.");
            alert.showAndWait();
            return false;
            } else if (!tfNumTel.getText().matches(NUMTEL_REGEX)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Format numéro de téléphone incorrect");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un numéro de téléphone valide.");
            alert.showAndWait();
            return false;
            } else if (!pfPassword.getText().equals(pfConfirmerPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mots de passe différents");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir le même mot de passe dans les deux champs.");
            alert.showAndWait();
            return false;
            }
            return true;
            }
            }