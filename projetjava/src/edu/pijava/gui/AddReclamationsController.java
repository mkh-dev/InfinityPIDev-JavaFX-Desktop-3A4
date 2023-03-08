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

public class AddReclamationsController implements Initializable {

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

    @FXML
    void ajouterReclamation() {
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

        // Création d'un objet réclamation avec les valeurs saisies
        Reclamations reclamation = new Reclamations();
        reclamation.setNom(nom);
        reclamation.setPrenom(prenom);
        reclamation.setEmail(email);
        reclamation.setMessage(message);

        // Enregistrement de la réclamation
        ReclamationService service = new ReclamationService();
        service.ajouterReclamation(reclamation);

        // Affichage d'un message de succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setContentText("Réclamation ajoutée avec succès");
        alert.showAndWait();
        
        // Effacer les champs de texte
        nomTextField.clear();
        prenomTextField.clear();
        emailTextField.clear();
        textArea.clear();
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

    // Création d'un objet réclamation avec les valeurs saisies
    Reclamations reclamation = new Reclamations();
    reclamation.setNom(nom);
    reclamation.setPrenom(prenom);
    reclamation.setEmail(email);
    reclamation.setMessage(message);

    // Enregistrement de la réclamation
    ReclamationService service = new ReclamationService();
    service.ajouterReclamation(reclamation);

    // Affichage d'un message de succès
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setContentText("Réclamation ajoutée avec succès");
    alert.showAndWait();

    // Effacer les champs de texte
    nomTextField.clear();
    prenomTextField.clear();
    emailTextField.clear();
    textArea.clear();
}


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}

