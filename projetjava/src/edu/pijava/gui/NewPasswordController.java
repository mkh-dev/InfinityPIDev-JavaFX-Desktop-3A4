package edu.pijava.gui;

import edu.pijava.model.Users;
import edu.pijava.services.UserService;
import edu.pijava.utils.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class NewPasswordController {

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private Button validerButton;

    @FXML
    private Label confirmationLabel;

    private Connection connection;
    private String email;

    private UserService userService;

    public void setConnection(Connection connection) {
        this.connection = connection;
        userService = new UserService();
        userService.setConnection(connection);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @FXML
    public void valider() {
        if (connection == null) {
            System.err.println("La connexion n'a pas été initialisée.");
            return;
        }
        String password1 = passwordField1.getText();
        String password2 = passwordField2.getText();
        if (password1.equals(password2)) {
            try {
                String updateQuery = "UPDATE users SET password = ? WHERE email = ?";
                PreparedStatement statement = connection.prepareStatement(updateQuery);
                statement.setString(1, password1);
                statement.setString(2, email);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    confirmationLabel.setText("Le mot de passe a été mis à jour.");
                    confirmationLabel.setStyle("-fx-text-fill: green;");
                } else {
                    confirmationLabel.setText("Le mot de passe n'a pas pu être mis à jour.");
                    confirmationLabel.setStyle("-fx-text-fill: red;");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            confirmationLabel.setText("Les mots de passe ne sont pas identiques.");
            confirmationLabel.setStyle("-fx-text-fill: red;");
        }
    }

    public void setUserData(Users user) {
        setEmail(user.getEmail());
    }

    @FXML
    public void initialize() {
        // Initialise la connexion à la base de données
        setConnection(MyConnection.getInstance().getCnx());

    }
}