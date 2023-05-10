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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.mindrot.jbcrypt.BCrypt;

public class AddUserController implements Initializable {

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
    @FXML
    private CheckBox ckTransporteur;
    @FXML
    private CheckBox ckOrganisateur;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void savePerson(ActionEvent event) {
        // Vérifier que tous les champs sont remplis et que la date de naissance est valide
        if (!validateFields()) {
            return;
        }

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
        
         if (ckTransporteur.isSelected()) {
            checkedCount++;
        }
         
        if (ckOrganisateur.isSelected()) {
            checkedCount++;
        }
        
        

        // Si plus d'une case à cocher est sélectionnée, afficher un message d'erreur
        if (checkedCount != 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une et une seule case à cocher !");
            alert.showAndWait();
            return;
        }

        // Si une seule case à cocher est sélectionnée, continuer avec l'inscription
        String prenom = addPrenom.getText();
        String nom = addNom.getText();
        String email = addEmail.getText();
        int numTel = Integer.parseInt(addNumTel.getText());

        LocalDate localDate = datePicker.getValue();
        Date dateNaissance = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        String password = addPassword.getText();

        // Récupérer le rôle utilisateur ou partenaire
         String userRole = ckUtilisateur.isSelected() ? "ROLE_UTILISATEUR" : (ckPartenaire.isSelected() ? "ROLE_PARTENAIRE" : (ckOrganisateur.isSelected() ? "ROLE_ORGANISATEUR" : "ROLE_TRANSPORTEUR"));

        Users u = new Users(prenom, nom, email, dateNaissance, numTel, userRole, password);
        UserService userCrud = new UserService();
        userCrud.ajouterUtilisateur2(u);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajout réussie");
        alert.setHeaderText(null);
        alert.setContentText("L'utilisateur a été ajouté avec succès !");
        alert.showAndWait();
    }

   private boolean validateFields() {
    // Vérifier si tous les champs obligatoires sont remplis
    if (addPassword.getText().isEmpty() ||
            addPrenom.getText().isEmpty() ||
            addNom.getText().isEmpty() ||
            datePicker.getValue() == null ||
            addEmail.getText().isEmpty() ||
            addNumTel.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs obligatoires.");
        alert.showAndWait();
        return false;
    }
    
    // Vérifier si l'adresse email est au bon format
    String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    if (!addEmail.getText().matches(EMAIL_REGEX)) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Format email incorrect");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir une adresse email valide.");
        alert.showAndWait();
        return false;
    }
    
    // Vérifier si le numéro de téléphone est au bon format
    String NUMTEL_REGEX = "^[0-9]{8}$";
    if (!addNumTel.getText().matches(NUMTEL_REGEX)) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Format numéro de téléphone incorrect");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir un numéro de téléphone valide.");
        alert.showAndWait();
        return false;
    }
    
    // Si tous les champs sont remplis et que l'adresse email et le numéro de téléphone sont au bon format, retourner true
    return true;
}
   
        public void hashPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        // Utiliser le mot de passe haché comme bon vous semble
    }
}