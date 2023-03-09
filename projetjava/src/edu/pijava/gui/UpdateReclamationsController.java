package edu.pijava.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import edu.pijava.model.Reclamations;
import edu.pijava.services.ReclamationService;
import edu.pijava.utils.MyConnection;

public class UpdateReclamationsController implements Initializable {

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextArea textArea;

    @FXML
    private Button validerButton;

    private Reclamations reclamation; // la réclamation à modifier

    public void setReclamation(Reclamations reclamation) {
        this.reclamation = reclamation;

        // Mettre à jour les champs de texte avec les valeurs actuelles de la réclamation
        nomTextField.setText(reclamation.getNom());
        prenomTextField.setText(reclamation.getPrenom());
        emailTextField.setText(reclamation.getEmail());
        textArea.setText(reclamation.getMessage());
    }

    @FXML
    void validerReclamation() {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String email = emailTextField.getText();
        String message = textArea.getText();

        // Vérification des champs obligatoires
        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || message.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Tous les champs sont obligatoires");
            alert.showAndWait();
            return;
        }

        // Mettre à jour la réclamation avec les nouvelles valeurs
        reclamation.setNom(nom);
        reclamation.setPrenom(prenom);
        reclamation.setEmail(email);
        reclamation.setMessage(message);

        // Enregistrement de la réclamation modifiée
        ReclamationService service = new ReclamationService();
        service.modifierReclamation(reclamation);

        // Affichage d'un message de succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setContentText("Réclamation modifiée avec succès");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}

