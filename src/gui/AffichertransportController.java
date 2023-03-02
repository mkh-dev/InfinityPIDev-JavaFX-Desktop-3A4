/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Transport;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.CRUDtransport;

public class AffichertransportController implements Initializable {

    ObservableList list = FXCollections.observableArrayList();

    @FXML
    private ListView<Transport> listview_aff_tr;

    private Button modifier_tr;


    @FXML
    private Button retour_tr;
    @FXML
    private AnchorPane anchorPaneAfficher;
    @FXML
    private Button statistique;

    void select_modifier(ActionEvent event) {

    }

    void select_supp(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*Transport transport = new Transport();
        ObservableList<Transport> transportListObservable = FXCollections.observableArrayList(transport.getAllTransports());
        transportListView.setItems(transportListObservable);
        transportCountLabel.setText("Total des transports : " + transportListObservable.size());*/

        CRUDtransport crud = new CRUDtransport();
        List<Transport> ListTransport = crud.affichertransport();

        listview_aff_tr.getItems().addAll(ListTransport);

        listview_aff_tr.setCellFactory(param -> new ListCell<Transport>() {
            @Override
            protected void updateItem(Transport item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getId_transport() == 0) {
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
                    h.getChildren().addAll(imageView, new Label("IDENTIFIANT: " + item.getId_transport()));
                    /////////////////////

                    //////////////heur_depart
                    // Créer un ImageView et définir l'image chargée comme source
                    Image i1 = new Image("img/heur_depart_icon.png");
                    ImageView imageView1 = new ImageView(i1);
                    // Définir la taille de l'ImageView
                    imageView1.setFitWidth(20);
                    imageView1.setFitHeight(20);
                    //hbox
                    HBox h1 = new HBox();
                    h1.getChildren().addAll(imageView1, new Label("HEUR DEPART: " + item.getHeure_depart()));
                    /////////////////////

                    //////////////-date_depart
                    // Créer un ImageView et définir l'image chargée comme source
                    Image i2 = new Image("img/date_depart_icon.jpg");
                    ImageView imageView2 = new ImageView(i2);
                    // Définir la taille de l'ImageView
                    imageView2.setFitWidth(20);
                    imageView2.setFitHeight(20);
                    //hbox
                    HBox h2 = new HBox();
                    h2.getChildren().addAll(imageView2, new Label("DATE DEPART: " + item.getDate_depart()));
                    /////////////////////
                    
                     //////////////type tranport
                    // Créer un ImageView et définir l'image chargée comme source
                    Image i3 = new Image("img/type_transport_icon.png");
                    ImageView imageView3 = new ImageView(i3);
                    // Définir la taille de l'ImageView
                    imageView3.setFitWidth(20);
                    imageView3.setFitHeight(20);
                    //hbox
                    HBox h3 = new HBox();
                    h3.getChildren().addAll(imageView3, new Label("TYPE TRANSPORT: " + item.getType_transport()));
                    /////////////////////
                    
                    //////////////-lieu depart
                    // Créer un ImageView et définir l'image chargée comme source
                    Image i4 = new Image("img/lieu_depart.png");
                    ImageView imageView4 = new ImageView(i4);
                    // Définir la taille de l'ImageView
                    imageView4.setFitWidth(20);
                    imageView4.setFitHeight(20);
                    //hbox
                    HBox h4 = new HBox();
                    h4.getChildren().addAll(imageView4, new Label("LIEU DEPART: " + item.getLieu_depart()));
                    /////////////////////
                    
                    //////////////lieu arriver
                    // Créer un ImageView et définir l'image chargée comme source
                    Image i5 = new Image("img/lieu_arriver.png");
                    ImageView imageView5 = new ImageView(i5);
                    // Définir la taille de l'ImageView
                    imageView5.setFitWidth(20);
                    imageView5.setFitHeight(20);
                    //hbox
                    HBox h5 = new HBox();
                    h5.getChildren().addAll(imageView5, new Label("LIEU ARRIVER: " + item.getLieu_arriver()));
                    //////////////////
                    
                    //////////////-prix tranport
                    // Créer un ImageView et définir l'image chargée comme source
                    Image i6 = new Image("img/prix_transport.jpg");
                    ImageView imageView6 = new ImageView(i6);
                    // Définir la taille de l'ImageView
                    imageView6.setFitWidth(20);
                    imageView6.setFitHeight(20);
                    //hbox
                    HBox h6 = new HBox();
                    h6.getChildren().addAll(imageView6, new Label("PRIX TRANSPORT: " + item.getPrix_transport()));
                    /////////////////////

                    // Créer un VBox pour aligner les labels
                    VBox vBox = new VBox(h, h1, h2, h3, h4, h5, h6, hBoxBoutton);

                    // Ajouter le VBox à la cellule
                    setGraphic(vBox);

                    bouttonSupprimer.setOnAction(event -> {
                        // Récupérer l'objet Transport associé à la ligne
                        Transport transport = getItem();
                        crud.supprimertransport(transport.getId_transport());

                        // Supprimer l'objet Transport de la liste
                        listview_aff_tr.getItems().remove(transport);

                        // Faire d'autres traitements ici si nécessaire
                    });

                    bouttonModifer.setOnAction(event -> {
                        // Récupérer l'objet Transport associé à la ligne
                        Transport transport = getItem();

                        try {
                            // Code pour passer à la page suivante
                            // Par exemple, pour passer à la page "PageSuivante.fxml" :
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifiertransport.fxml"));
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.setUserData(transport);
                            stage.setTitle("Modifier Transport");

                            stage.show();
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }

                    });

                }
            }
        });

        //List<Transport> ListTransport=crud.affichertransport();
        retour_tr.setOnAction(event -> {
            try {
                Parent newParent = FXMLLoader.load(getClass().getResource("ajoutertransport.fxml"));
                Scene newScene = new Scene(newParent);
                Stage currentStage = (Stage) anchorPaneAfficher.getScene().getWindow();
                currentStage.setScene(newScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        statistique.setOnAction(event -> {
            try {
                Parent newParent = FXMLLoader.load(getClass().getResource("statistique.fxml"));
                Scene newScene = new Scene(newParent);
                Stage currentStage = (Stage) anchorPaneAfficher.getScene().getWindow();
                currentStage.setScene(newScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

}


/*

gigBouttonModifier.setOnAction((ev) -> {
            try {
                // Code pour passer à la page suivante
                // Par exemple, pour passer à la page "PageSuivante.fxml" :
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierGig.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setUserData(gig);
                stage.setTitle("Modifier Gig");

                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AjouterGigController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
 */
