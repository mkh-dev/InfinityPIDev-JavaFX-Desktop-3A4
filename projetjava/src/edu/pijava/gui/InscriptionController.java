package edu.pijava.gui;

import edu.pijava.model.Users;
import edu.pijava.services.UserService;
import java.io.IOException;
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
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.mindrot.jbcrypt.BCrypt;

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
    private Button btnValiderRegister;
    @FXML
    private CheckBox ckUtilisateur;
    @FXML
    private CheckBox ckPartenaire;
    @FXML
    private Hyperlink hlinkLogin;
    
    
    
    private final UserService userService = new UserService();

 private final String EMAIL_FROM = "pidevtestapp@gmail.com";
    private final String EMAIL_PASSWORD = "krkjrmewwhxrsndq";
    private final String EMAIL_SUBJECT = "Confirmation de compte";
    private final String EMAIL_CONTENT = "Voici votre code de confirmation de compte : ";

    private String code;

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
        Users user = userService.getUserByEmail(email);
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
        // Générer un code aléatoire à 6 chiffres
    code = generateCodeRegister();

    // Envoyer le code par email
    sendEmailRegister(email, code);

    // Afficher une alerte "Code de réinitialisation envoyé dans le mail"
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Confirmation du compte");
    alert.setHeaderText(null);
    alert.setContentText("Le code de confirmation a été envoyé à votre adresse email.");
    alert.showAndWait();

    // Rediriger l'utilisateur vers l'interface CodeConfirmation.fxml
    redirectToCodeConfirmation();
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
        




private String generateCodeRegister() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000; // Générer un nombre aléatoire à 6 chiffres
        return String.valueOf(code);
    }




  private void sendEmailRegister (String email, String code) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication (EMAIL_FROM, EMAIL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(EMAIL_SUBJECT);
            message.setSentDate(new Date());

            message.setText(EMAIL_CONTENT + code);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

   private void redirectToCodeConfirmation() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/CodeConfirmation.fxml"));
        Parent root = loader.load();
        CodeConfirmationController codeConfirmationController = loader.getController();
        codeConfirmationController.setEmail(tfEmail.getText());
        codeConfirmationController.setCode(code); // Correction de l'erreur : utiliser la variable 'code' au lieu de 'randomCode'
        Stage stage = (Stage) btnValiderRegister.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
     public void hashPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        // Utiliser le mot de passe haché comme bon vous semble
    }
}
