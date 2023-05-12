package edu.pijava.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomePageController {

    public Button connectBtn;
    public Button registerBtn;

    public void handleConnectBtn(ActionEvent event) throws IOException {
        // Chargement de la vue Login.fxml
        Parent loginView = FXMLLoader.load(getClass().getResource("/edu/pijava/gui/Login.fxml"));
        Scene loginScene = new Scene(loginView);

        // Création d'une nouvelle fenêtre pour la vue de connexion
        Stage loginStage = new Stage();
        loginStage.setTitle("Connexion");
        loginStage.setScene(loginScene);
        loginStage.show();
    }

    public void handleRegisterBtn(ActionEvent event) throws IOException {
        // Chargement de la vue Inscription.fxml
        Parent registerView = FXMLLoader.load(getClass().getResource("/edu/pijava/gui/Inscription.fxml"));
        Scene registerScene = new Scene(registerView);

        // Création d'une nouvelle fenêtre pour la vue d'inscription
        Stage registerStage = new Stage();
        registerStage.setTitle("Inscription");
        registerStage.setScene(registerScene);
        registerStage.show();
    }
}

