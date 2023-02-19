package edu.pijava.gui;

import edu.pijava.services.UserService;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField logEmail;

    @FXML
    private PasswordField logPassword;
    @FXML
    private Button loginButton;

@FXML
private void handleLogin(ActionEvent event) {
    String email = logEmail.getText();
    String password = logPassword.getText();
    UserService userService = new UserService();
    boolean isValid = userService.authenticateUser(email, password);
    if (email.isEmpty() || password.isEmpty()) {
        // Afficher une boîte de dialogue d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Identifiants invalides");
        alert.setContentText("Veuillez entrer une adresse e-mail et un mot de passe.");
        alert.showAndWait();
    } else if (isValid) {
        // Vérifier si l'utilisateur est un administrateur
        if (userService.getUserByEmail(email).getUserRole().equals("Administrateur")) {
            // Charger directement l'interface "page.fxml"
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("page.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) loginButton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e) {
                
            }
        } else {
            // Charger l'interface d'inscription
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) loginButton.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e) {
              
            }
        }
    } else {
        // Afficher une boîte de dialogue d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Identifiants invalides");
        alert.setContentText("L'adresse e-mail ou le mot de passe est incorrect.");
        alert.showAndWait();
    }
}


@FXML
void initialize() {
    loginButton.setOnAction(this::handleLogin);
}
}
