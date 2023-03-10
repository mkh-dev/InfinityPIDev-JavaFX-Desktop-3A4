package edu.pijava.gui;

import edu.pijava.model.CategorieEvenement;
import edu.pijava.services.EvenementCategorieService;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AjoutCatController implements Initializable {



    @FXML
    private Button validerButton;
    @FXML
    private TextField categorie;
 @FXML
    void ajouterReclamation() {
        String nom = categorie.getText();


        // Vérification des champs obligatoires
        if (nom.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Tous les champs sont obligatoires");
            alert.showAndWait();
            return;
        }

        // Création d'un objet réclamation avec les valeurs saisies
        CategorieEvenement e = new CategorieEvenement();
        e.setCat_event(nom);

        // Enregistrement de la réclamation
        EvenementCategorieService service = new  EvenementCategorieService();
        service.ajouterEvenementCategorie(e);

        // Affichage d'un message de succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setContentText("Réclamation ajoutée avec succès");
        alert.showAndWait();
        
        // Effacer les champs de texte
       categorie.clear();
    }
     @FXML
    void validerCat() {
    String nom = categorie.getText();


    // Vérification des champs obligatoires
    if (nom.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setContentText("Tous les champs sont obligatoires");
        alert.showAndWait();
        return;
    }

    // Création d'un objet réclamation avec les valeurs saisies
     CategorieEvenement e = new  CategorieEvenement();
     e.setCat_event(nom);


    // Enregistrement de la réclamation
    EvenementCategorieService service = new EvenementCategorieService();
    service.ajouterEvenementCategorie(e);

    // Affichage d'un message de succès
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setContentText("Réclamation ajoutée avec succès");
    alert.showAndWait();

    // Effacer les champs de texte
    categorie.clear();

}


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
