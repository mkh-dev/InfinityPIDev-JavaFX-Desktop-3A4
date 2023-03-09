package edu.pijava.gui;

import edu.pijava.model.Reclamations;
import edu.pijava.services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReclamationsUtilisateurController implements Initializable {

    @FXML
    private ListView<Reclamations> listView;

    private ObservableList<Reclamations> observableList;

    private ReclamationService service;

    private Reclamations selectedReclamation;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new ReclamationService();
        selectedReclamation = null;
        observableList = FXCollections.observableArrayList();
        afficherListeReclamations();
    }

    private void afficherListeReclamations() {
        observableList.clear();
        List<Reclamations> reclamations = service.afficherReclamations();
        observableList.addAll(reclamations);
        listView.setItems(observableList);
        listView.setOnMouseClicked(this::selectionnerReclamation);
    }

    @FXML
private void ajouterReclamation(ActionEvent event) throws IOException {
// Charger la nouvelle fenêtre "AddReclamations.fxml"
FXMLLoader loader = new FXMLLoader(getClass().getResource("AddReclamations.fxml"));
Parent root = loader.load();
// Créer une nouvelle scène pour la nouvelle fenêtre
Scene scene = new Scene(root);

// Créer une nouvelle étape pour la nouvelle scène
Stage stage = new Stage();
stage.setScene(scene);
stage.setTitle("Ajouter réclamation");
stage.setResizable(false);

// Afficher la nouvelle étape
stage.showAndWait();

// Mettre à jour la liste des réclamations après l'ajout
afficherListeReclamations();
}

    @FXML
    private void supprimerReclamation(ActionEvent event) {
        if (selectedReclamation != null) {
            service.supprimerReclamation(selectedReclamation);
            observableList.remove(selectedReclamation);
            selectedReclamation = null;
        }
    }

    private void selectionnerReclamation(MouseEvent event) {
        selectedReclamation = listView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void modifierReclamation(ActionEvent event) throws IOException {
        if (selectedReclamation != null) {
            // Charger la nouvelle fenêtre "UpdateReclamations.fxml"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateReclamations.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la nouvelle fenêtre
        UpdateReclamationsController controller = loader.getController();
        controller.setReclamation(selectedReclamation);

        // Créer une nouvelle scène pour la nouvelle fenêtre
        Scene scene = new Scene(root);

        // Créer une nouvelle étape pour la nouvelle scène
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Modifier réclamation");
        stage.setResizable(false);

        // Afficher la nouvelle étape
        stage.showAndWait();

        // Mettre à jour la liste des réclamations après la modification
        selectedReclamation = null;
        afficherListeReclamations();
    }
}
}