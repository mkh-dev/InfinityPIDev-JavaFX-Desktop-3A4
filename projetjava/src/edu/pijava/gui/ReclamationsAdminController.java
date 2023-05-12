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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ReclamationsAdminController implements Initializable {

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

  
}

