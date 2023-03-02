package edu.pijava.gui;

import edu.pijava.model.Users;
import edu.pijava.services.UserService;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ResetPasswordController {
    @FXML
    private TextField textFieldEmail;

    @FXML
    private Button btnValider;

    @FXML
    private Label lblAlert;

    private UserService userService = new UserService();

    private final String EMAIL_FROM = "pidevtestapp@gmail.com";
    private final String EMAIL_PASSWORD = "krkjrmewwhxrsndq";
    private final String EMAIL_SUBJECT = "Réinitialisation de mot de passe";
    private final String EMAIL_CONTENT = "Cliquez sur le lien ci-dessous pour réinitialiser votre mot de passe : \n\n";

    @FXML
public void handleValider() {
    String email = textFieldEmail.getText();
    Users user = userService.getUserByEmail(email);

    if (user == null) {
        lblAlert.setText("Aucun utilisateur n'est associé à cette adresse email !");
        return;
    }

    sendEmail(email);
    lblAlert.setText("Un email de réinitialisation de mot de passe a été envoyé à votre adresse email !");

    // Ajouter une boîte de dialogue d'alerte pour informer l'utilisateur que l'email a été envoyé
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Email envoyé");
    alert.setHeaderText("Email de réinitialisation envoyé !");
    alert.setContentText("Un email de réinitialisation de mot de passe a été envoyé à votre adresse email !");
    alert.showAndWait();
}


    private void sendEmail(String email) {
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

            String url = "http://localhost:8080/new-password";
            message.setText(EMAIL_CONTENT + url);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

