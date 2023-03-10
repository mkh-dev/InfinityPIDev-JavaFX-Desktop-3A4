package edu.pijava.gui;

import edu.pijava.model.CategorieEvenement;
import edu.pijava.services.EvenementCategorieService;
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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CatEventController implements Initializable {

    @FXML
    private ListView<CategorieEvenement> listView;

    private ObservableList<CategorieEvenement> observableList;

    private EvenementCategorieService service;

    private CategorieEvenement selectedCat;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new EvenementCategorieService();
        selectedCat = null;
        observableList = FXCollections.observableArrayList();
        afficherListeCategorie();
    }

    private void afficherListeCategorie() {
        observableList.clear();
        List<CategorieEvenement> cat = service.afficherEvenementCategorie();
        observableList.addAll(cat);
        listView.setItems(observableList);
        listView.setOnMouseClicked(this::selectionnerCategorie);
    }

    @FXML
private void ajouterCategorie(ActionEvent event) throws IOException {
// Charger la nouvelle fenêtre "AddReclamations.fxml"
FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCat.fxml"));
Parent root = loader.load();
// Créer une nouvelle scène pour la nouvelle fenêtre
Scene scene = new Scene(root);

// Créer une nouvelle étape pour la nouvelle scène
Stage stage = new Stage();
stage.setScene(scene);
stage.setTitle("Ajouter catégorie");
stage.setResizable(false);

// Afficher la nouvelle étape
stage.showAndWait();

// Mettre à jour la liste des réclamations après l'ajout
afficherListeCategorie();
}

    @FXML
    private void supprimerCategorie(ActionEvent event) {
        if (selectedCat != null) {
            service.supprimerEvenementCategorie(selectedCat);
            observableList.remove(selectedCat);
            selectedCat= null;
        }
    }

    private void selectionnerCategorie(MouseEvent event) {
        selectedCat= listView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void modifierCategorie(ActionEvent event) throws IOException {
        if (selectedCat != null) {
            // Charger la nouvelle fenêtre "UpdateReclamations.fxml"
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModCat.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur de la nouvelle fenêtre
        ModCatController controller = loader.getController();
        controller.setCat(selectedCat);

        // Créer une nouvelle scène pour la nouvelle fenêtre
        Scene scene = new Scene(root);

        // Créer une nouvelle étape pour la nouvelle scène
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Modifier catégorie");
        stage.setResizable(false);

        // Afficher la nouvelle étape
        stage.showAndWait();

        // Mettre à jour la liste des réclamations après la modification
        selectedCat = null;
        afficherListeCategorie();
    }
}
}