package edu.pijava.gui;

import edu.pijava.model.Evenement;
import edu.pijava.services.EvenementService;
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

public class MyEventController implements Initializable {

    @FXML
    private ListView<Evenement> listView;

    private ObservableList<Evenement> observableList;

    private EvenementService service;

    private Evenement selectedCat;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        service = new EvenementService();
        selectedCat = null;
        observableList = FXCollections.observableArrayList();
        afficherListeEvenement();
    }

    private void afficherListeEvenement() {
        observableList.clear();
        List<Evenement> cat = service.afficherEvenement();
        observableList.addAll(cat);
        listView.setItems(observableList);
        listView.setOnMouseClicked(this::selectionnerEvenement);
    }

    @FXML
private void ajouterEvenement(ActionEvent event) throws IOException {
// Charger la nouvelle fenêtre "AddReclamations.fxml"
FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEvent.fxml"));
Parent root = loader.load();
// Créer une nouvelle scène pour la nouvelle fenêtre
Scene scene = new Scene(root);

// Créer une nouvelle étape pour la nouvelle scène
Stage stage = new Stage();
stage.setScene(scene);
stage.setTitle("Ajouter événement");
stage.setResizable(false);

// Afficher la nouvelle étape
stage.showAndWait();

// Mettre à jour la liste des réclamations après l'ajout
afficherListeEvenement();
}

    @FXML
    private void supprimerEvenement(ActionEvent event) {
        if (selectedCat != null) {
            service.supprimerEvenement(selectedCat);
            observableList.remove(selectedCat);
            selectedCat= null;
        }
    }

    private void selectionnerEvenement(MouseEvent event) {
        selectedCat= listView.getSelectionModel().getSelectedItem();
    }

    //@FXML
  //  private void modifierEvenement(ActionEvent event) throws IOException {
    //    if (selectedCat != null) {
            // Charger la nouvelle fenêtre "UpdateReclamations.fxml"
     //       FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateEvent.fxml"));
      //      Parent root = loader.load();

            // Obtenir le contrôleur de la nouvelle fenêtre
     //   UpdateEventController controller = loader.getController();
     //   controller.setCat(selectedCat);

        // Créer une nouvelle scène pour la nouvelle fenêtre
     //   Scene scene = new Scene(root);

        // Créer une nouvelle étape pour la nouvelle scène
     //   Stage stage = new Stage();
     //   stage.setScene(scene);
     //   stage.setTitle("Modifier événement");
     //   stage.setResizable(false);

        // Afficher la nouvelle étape
     //   stage.showAndWait();

        // Mettre à jour la liste des réclamations après la modification
       // selectedCat = null;
      //  afficherListeEvenement();
  //  }
//}
}