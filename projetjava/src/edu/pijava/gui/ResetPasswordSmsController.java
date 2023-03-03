package edu.pijava.gui;

import java.util.Properties;
import java.util.Random;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ResetPasswordSmsController {

    private final String ACCOUNT_SID = "ACf7846977c75534470d13b8755561834f";
    private final String AUTH_TOKEN = "02be9042800d8d9bcb1581d9606f72a2";
    private final String TWILIO_PHONE_NUMBER = "+21658948914";

    @FXML
    private TextField textFieldPhoneNumber;

    @FXML
    private Button btnValider;

    private String code;

    @FXML
    public void handleValider() {
        String phoneNumber = textFieldPhoneNumber.getText();

        // Valider le numéro de téléphone
        if (phoneNumber == null || phoneNumber.trim().isEmpty() || !phoneNumber.matches("^\\+[1-9]\\d{1,14}$")) {
            showAlert("Numéro de téléphone invalide",
                    "Veuillez saisir un numéro de téléphone valide au format international (ex: +21612345678) !");
            return;
        }

        // Générer un code aléatoire à 6 chiffres
        code = generateCode();

        // Envoyer le code par SMS
        sendSms(phoneNumber, code);

        // Rediriger l'utilisateur vers l'interface NewCode.fxml
        redirectToNewCode();
    }

    private String generateCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000; // Générer un nombre aléatoire à 6 chiffres
        return String.valueOf(code);
    }

    private void sendSms(String phoneNumber, String code) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(phoneNumber), new PhoneNumber(TWILIO_PHONE_NUMBER),
                "Voici votre code de réinitialisation de mot de passe : " + code).create();

        System.out.println(message.getSid());
    }

 private void redirectToNewCode() {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("NewCode.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnValider.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        showAlert("Erreur de chargement de l'interface", "Impossible de charger l'interface NewCode.fxml !");
    }
}


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
