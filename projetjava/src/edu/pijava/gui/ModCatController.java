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


public class ModCatController implements Initializable {


    
    @FXML
    private TextField categorie;
    
    @FXML
    private Button validerButton;

    private CategorieEvenement cat; // la réclamation à modifier

    public void setCat(CategorieEvenement cat) {
        this.cat = cat;

        // Mettre à jour les champs de texte avec les valeurs actuelles de la réclamation
        categorie.setText(cat.getCat_event());

    }

    @FXML
    void validerModCat() {
        String c = categorie.getText();


        // Vérification des champs obligatoires
        if (c.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setContentText("Tous les champs sont obligatoires");
            alert.showAndWait();
            return;
        }

        // Mettre à jour la réclamation avec les nouvelles valeurs
        cat.setCat_event(c);


        // Enregistrement de la réclamation modifiée
        EvenementCategorieService service = new EvenementCategorieService();
        service.modifierEvenementCategorie(cat);

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