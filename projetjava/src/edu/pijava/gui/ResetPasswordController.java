package edu.pijava.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javafx.scene.control.Button;

public class ResetPasswordController {

    @FXML
    private TextField textFieldEmail;
    @FXML
    private Button btnValider;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        String to = textFieldEmail.getText();

        // Send email
        sendEmail(to);
    }

  private void sendEmail(String to) {
    String from = "pidevtestapp@gmail.com"; // Replace with your email
    String password = "krkjrmewwhxrsndq"; // Replace with your password

    // SMTP settings
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    // Create session with authentication
    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, password);
        }
    });

    try {
        // Create message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Réinitialisation de mot de passe");
        message.setText("Bonjour,\n\nCliquez sur ce lien pour réinitialiser votre mot de passe : https://votre-site-web.com/reset-password\n\nCordialement,\nL'équipe de votre site web");

        // Send message
        System.out.println("Sending message...");
        Transport.send(message);
        System.out.println("E-mail sent successfully");

    } catch (MessagingException e) {
        e.printStackTrace();
    }
}
}