 package edu.pijava.gui;

import edu.pijava.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CodeConfirmationController {
    @FXML
    private TextField textFieldCode;

    @FXML
    private Button btnValider;


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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation du compte");
            alert.setHeaderText(null);
            alert.setContentText("Le code de confirmation a été confirmé");
            alert.showAndWait();
        } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation du compte");
                alert.setHeaderText(null);
                alert.setContentText("Le code de confirmation est faux");
                alert.showAndWait();
        }
    }


    public void setUserData(String email, String code) {
        this.email = email;
        this.code = code;
    }
}