/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.gui;

import edu.pijava.model.Transport;
import edu.pijava.model.Vehicule;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import edu.pijava.services.CRUDvehicule;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AffichervehiculeController implements Initializable {
    
     ObservableList list = FXCollections.observableArrayList();

    private TableColumn<Vehicule, String> marque_af_v;
    private TableColumn<Vehicule, String> Modele_af_v;
    private TableColumn<Vehicule, String> Immatriculation_af_v;
    private TableColumn<Vehicule, String> Disponibilte_af_v;
    private TableView<Vehicule> tabaffichervoiture;
    @FXML
    private ListView<Vehicule> listview_aff_v;
    @FXML
    private Button retour_v;
    @FXML
    private AnchorPane anchorpanafficher_v;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        
        
        CRUDvehicule crud = new CRUDvehicule();
        List<Vehicule> ListVehicule = crud.affichervehicule();

        listview_aff_v.getItems().addAll(ListVehicule);

        listview_aff_v.setCellFactory(param -> new ListCell<Vehicule>() {
            @Override
            protected void updateItem(Vehicule item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getId_vehicule()== 0) {
                    setText(null);
                } else {
                    //setText(Integer.toString(item.getId_transport()));

                    Button bouttonModifer = new Button();
                    Button bouttonSupprimer = new Button();

                    bouttonModifer.setText("Modifier");
                    bouttonSupprimer.setText("Supprimer");
                    HBox hBoxBoutton = new HBox();
                    hBoxBoutton.getChildren().addAll(bouttonModifer, bouttonSupprimer);

                    //////////////-ID
                    // Créer un ImageView et définir l'image chargée comme source
                    Image i = new Image("img/key_icon.png");
                    ImageView imageView = new ImageView(i);
                    // Définir la taille de l'ImageView
                    imageView.setFitWidth(20);
                    imageView.setFitHeight(20);
                    //hbox
                    HBox h = new HBox();
                    h.getChildren().addAll(imageView, new Label("IDENTIFIANT: " + item.getId_vehicule()));
                    /////////////////////

                    //////////////marque
                    // Créer un ImageView et définir l'image chargée comme source
                    Image i1 = new Image("img/marque.png");
                    ImageView imageView1 = new ImageView(i1);
                    // Définir la taille de l'ImageView
                    imageView1.setFitWidth(20);
                    imageView1.setFitHeight(20);
                    //hbox
                    HBox h1 = new HBox();
                    h1.getChildren().addAll(imageView1, new Label("MARQUE: " + item.getMarque()));
                    /////////////////////

                    //////////////MODEL
                    // Créer un ImageView et définir l'image chargée comme source
                    Image i2 = new Image("img/model.png");
                    ImageView imageView2 = new ImageView(i2);
                    // Définir la taille de l'ImageView
                    imageView2.setFitWidth(20);
                    imageView2.setFitHeight(20);
                    //hbox
                    HBox h2 = new HBox();
                    h2.getChildren().addAll(imageView2, new Label("MODEL: " + item.getModele()));
                    /////////////////////
                    
                     //////////////immatriculation
                    // Créer un ImageView et définir l'image chargée comme source
                    Image i3 = new Image("img/immatriculation.png");
                    ImageView imageView3 = new ImageView(i3);
                    // Définir la taille de l'ImageView
                    imageView3.setFitWidth(20);
                    imageView3.setFitHeight(20);
                    //hbox
                    HBox h3 = new HBox();
                    h3.getChildren().addAll(imageView3, new Label("IMMATRICULATION: " + item.getImmatriculation()));
                    /////////////////////
                    
                    //////////////-Disponibilite
                    // Créer un ImageView et définir l'image chargée comme source
                    Image i4 = new Image("img/disponibilite.jpg");
                    ImageView imageView4 = new ImageView(i4);
                    // Définir la taille de l'ImageView
                    imageView4.setFitWidth(20);
                    imageView4.setFitHeight(20);
                    //hbox
                    HBox h4 = new HBox();
                    h4.getChildren().addAll(imageView4, new Label("DISPONIBILTE: " + item.getDisponibilite()));
                    /////////////////////
                    
                    

                    // Créer un VBox pour aligner les labels
                    VBox vBox = new VBox(h, h1, h2, h3, h4, hBoxBoutton);

                    // Ajouter le VBox à la cellule
                    setGraphic(vBox);

                    bouttonSupprimer.setOnAction(event -> {
                        // Récupérer l'objet Transport associé à la ligne
                        Vehicule vehicule = getItem();
                        crud.supprimervehicule(vehicule.getId_vehicule());

                        // Supprimer l'objet Transport de la liste
                        listview_aff_v.getItems().remove(vehicule);

                        // Faire d'autres traitements ici si nécessaire
                    });

                    bouttonModifer.setOnAction(event -> {     
                        // Récupérer l'objet Transport associé à la ligne
                        Vehicule vehicule = getItem();

                        try {
                            // Code pour passer à la page suivante
                            // Par exemple, pour passer à la page "PageSuivante.fxml" :
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifiervehicule.fxml"));
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.setUserData(vehicule);
                            stage.setTitle("Modifier Vehicule");

                            stage.show();
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
 
                    });  

                }
            }
        });
        
        retour_v.setOnAction(event -> {
            try {
                Parent newParent = FXMLLoader.load(getClass().getResource("ajoutervehicule.fxml"));
                Scene newScene = new Scene(newParent);
                Stage currentStage = (Stage) anchorpanafficher_v.getScene().getWindow();
                currentStage.setScene(newScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        
    } 
}
    

