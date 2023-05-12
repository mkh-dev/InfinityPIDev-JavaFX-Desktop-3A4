package edu.pijava.gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import edu.pijava.model.Users;
import edu.pijava.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class ResetPasswordController {
    @FXML
    private TextField textFieldEmail;

    @FXML
    private Button btnValider;

    private UserService userService = new UserService();

    private final String EMAIL_FROM = "pidevtestapp@gmail.com";
    private final String EMAIL_PASSWORD = "krkjrmewwhxrsndq";
    private final String EMAIL_SUBJECT = "Réinitialisation de mot de passe";
    private final String EMAIL_CONTENT = "Voici votre code de réinitialisation de mot de passe : ";

    private String code;
    
    
@FXML
public void handleValider() {
    String email = textFieldEmail.getText();
    Users user = userService.getUserByEmail(email);

    if (user == null) {
        showAlert("Adresse email invalide", "Aucun utilisateur n'est associé à cette adresse email !");
        return;
    }

    // Générer un code aléatoire à 6 chiffres
    code = generateCode();

    // Envoyer le code par email
    sendEmail(email, code);

    // Afficher une alerte "Code de réinitialisation envoyé dans le mail"
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Réinitialisation de mot de passe");
    alert.setHeaderText(null);
    alert.setContentText("Le code de réinitialisation a été envoyé à votre adresse email.");
    alert.showAndWait();

    // Rediriger l'utilisateur vers l'interface NewCode.fxml
    redirectToNewCode();
    return;
}



    private String generateCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000; // Générer un nombre aléatoire à 6 chiffres
        return String.valueOf(code);
    }

    private void sendEmail(String email, String code) {
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

   private void redirectToNewCode() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/NewCode.fxml"));
        Parent root = loader.load();
        NewCodeController newCodeController = loader.getController();
        newCodeController.setEmail(textFieldEmail.getText());
        newCodeController.setCode(code); // Correction de l'erreur : utiliser la variable 'code' au lieu de 'randomCode'
        Stage stage = (Stage) btnValider.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}



    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    

}


