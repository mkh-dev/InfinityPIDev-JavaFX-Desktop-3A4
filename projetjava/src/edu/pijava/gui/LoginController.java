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
import javafx.scene.control.Hyperlink;
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
    private Hyperlink linkPassword;
        @FXML
    private Hyperlink hlinkRegister;

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
            String userRole = userService.getUserByEmail(email).getUserRole();
            if (userRole.equals("Utilisateur")) {
                // Charger l'interface utilisateur.fxml
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Utilisateur.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (userRole.equals("Partenaire")) {
                // Charger l'interface page.fxml
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Partenaire.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                } else if (userRole.equals("Transporteur")) {
                // Charger l'interface page.fxml
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Transporteur.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (userRole.equals("Administrateur")) {
                // Charger l'interface inscription.fxml
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Administrateur.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
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
    linkPassword.setOnAction(this::handleForgotPassword);
    hlinkRegister.setStyle("-fx-text-fill: black;");
    hlinkRegister.setOnAction(this::handleRegisterClick);
}


    
    @FXML
private void handleForgotPassword(ActionEvent event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("ResetPassword.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


private void handleRegisterClick(ActionEvent event) {
        try {
            Parent loginParent = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
            Scene loginScene = new Scene(loginParent);
            Stage loginStage = (Stage) hlinkRegister.getScene().getWindow();
            loginStage.setScene(loginScene);
            loginStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
