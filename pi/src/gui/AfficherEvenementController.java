/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Evenement;
import Services.CRUDEvenement;
import java.io.IOException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author miguel
 */
public class AfficherEvenementController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private AnchorPane afficherEventAnchorPane;
    @FXML
    private ListView<Evenement> listview_aff;
    @FXML
    private MenuItem MenuAfficher;
    @FXML
    private MenuItem menuAjouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        CRUDEvenement crud = new CRUDEvenement();
        List<Evenement> ListEvenement = crud.afficherevenement();

        listview_aff.getItems().addAll(ListEvenement);

        listview_aff.setCellFactory(param -> new ListCell<Evenement>() {

            @Override
            protected void updateItem(Evenement item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getId_event() == 0) {
                    setText(null);
                } else {

                    Button bouttonModifer = new Button();
                    Button bouttonSupprimer = new Button();

                    bouttonModifer.setText("Modifier");
                    bouttonSupprimer.setText("Supprimer");
                    
                    bouttonModifer.setStyle("-fx-background-color:black;-fx-text-fill: white;");
                    bouttonSupprimer.setStyle("-fx-background-color:black; -fx-text-fill: white;");
                            
                    HBox hBoxBoutton = new HBox();
                    hBoxBoutton.getChildren().addAll(bouttonModifer, bouttonSupprimer);

                    // Créer un ImageView et définir l'image chargée comme source
                    Image i = new Image("gui/" + item.getImage());
                    ImageView imageView = new ImageView(i);
                    // Définir la taille de l'ImageView
                    imageView.setFitWidth(200);
                    imageView.setFitHeight(150);

                    VBox contentVBox = new VBox();

                    contentVBox.getChildren().addAll(new Label("NOM: " + item.getNom_event()), new Label("DESCRIPTION: " + item.getDescription_event()), new Label("DATE DEBUT: " + item.getDate_debut_event()), new Label("DATE FIN: " + item.getDate_fin_event()), new Label("LIEU: " + item.getLieu_event()), new Label("BUDGET: " + item.getBudget_event()), new Label("DATE DEBUT: " + item.getDate_debut_event()), hBoxBoutton);

                    HBox fullView = new HBox();
                    fullView.getChildren().addAll(imageView, contentVBox);

                    setGraphic(fullView);

                    bouttonSupprimer.setOnAction(event -> {
                        // Récupérer l'objet Transport associé à la ligne
                        Evenement e = getItem();
                        crud.supprimerevent(e);

                        // Supprimer l'objet Transport de la liste
                        listview_aff.getItems().remove(e);

                        // Faire d'autres traitements ici si nécessaire
                    });

                    bouttonModifer.setOnAction(event -> {
                        // Récupérer l'objet Transport associé à la ligne
                        Evenement e = getItem();

                        try {
                            // Code pour passer à la page suivante
                            // Par exemple, pour passer à la page "PageSuivante.fxml" :
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEvenement.fxml"));
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.setUserData(e);
                            stage.setTitle("Modifier Evenement");

                            stage.show();
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }

                    });

                }
            }

        });

        menuAjouter.setOnAction(event -> {
            try {
                Parent newParent = FXMLLoader.load(getClass().getResource("ajouterEvenement.fxml"));
                Scene newScene = new Scene(newParent);
                Stage currentStage = (Stage) afficherEventAnchorPane.getScene().getWindow();
                currentStage.setScene(newScene);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

}
