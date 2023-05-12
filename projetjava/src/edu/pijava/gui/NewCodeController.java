 package edu.pijava.gui;

import edu.pijava.model.Users;
import edu.pijava.services.UserService;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewCodeController {
    @FXML
    private TextField textFieldCode;

    @FXML
    private Button btnValider;

    @FXML
    private Label lblAlert;

    private final UserService userService = new UserService();
    private String email;
    private String code;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @FXML
    public void handleValider() {
        String codeSaisi = textFieldCode.getText();

        if (codeSaisi.equals(code)) {
            redirectToNewPassword();
        } else {
            lblAlert.setText("Code invalide !");
        }
    }

    private void redirectToNewPassword() {
        try {
            // Récupérer les données de l'utilisateur
            Users user = userService.getUserByEmail(email);

            // Charger l'interface NewPassword.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewPassword.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de l'interface NewPassword.fxml
            NewPasswordController controller = loader.getController();

            // Passer les données nécessaires au contrôleur
            controller.setUserData(user);

            // Créer la scène avec l'interface NewPassword.fxml
            Scene scene = new Scene(root);

            // Obtenir la fenêtre actuelle et la fermer
            Stage currentStage = (Stage) btnValider.getScene().getWindow();
            currentStage.close();

            // Créer une nouvelle fenêtre pour la scène NewPassword.fxml
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("Nouveau mot de passe");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUserData(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
