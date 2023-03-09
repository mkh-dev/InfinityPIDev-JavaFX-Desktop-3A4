/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.controllers;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import edu.pijava.model.Reservation;
import edu.pijava.services.ReservationCrud;
import edu.pijava.utils.MyConnection;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ouesl
 */
public class MesReservationsController implements Initializable {

    private AnchorPane pane1;
    @FXML
    private TableColumn<Reservation, String> fxnumres;
    @FXML
    private TableColumn<Reservation, String> fxnbplaces;
    @FXML
    private TableColumn<Reservation, String> fxidevent;

    ReservationCrud rc = new ReservationCrud();
    @FXML
    private TableView<Reservation> tableview;
    @FXML
    private TableColumn<Reservation, Void> fxactions;
    @FXML
    private Label fxnbre;
    @FXML
    private Button btnrefresh;
    ObservableList<Reservation> ReservationList;
    @FXML
    private Button fxretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
    }

    @FXML
    private void loadData() {
        fxnumres.setCellValueFactory(new PropertyValueFactory<>("numRes"));
        fxnbplaces.setCellValueFactory(new PropertyValueFactory<>("nbPlaces"));
        fxidevent.setCellValueFactory(new PropertyValueFactory<>("idEvent"));

        ObservableList<Reservation> ReservationList = FXCollections.observableArrayList(rc.afficherMesReservations(2));
        tableview.setItems(ReservationList);
        fxnbre.setText("Vous Avez  " + ReservationList.size() + "      réservations:");

        fxactions.setCellFactory(new Callback<TableColumn<Reservation, Void>, TableCell<Reservation, Void>>() {
            @Override
            public TableCell<Reservation, Void> call(final TableColumn<Reservation, Void> param) {
                final TableCell<Reservation, Void> cell = new TableCell<Reservation, Void>() {

                    private final Button btn = new Button("Modifier");
                    private final Button btn2 = new Button("Supprimer");
                    private final Button btn3 = new Button("payer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            // Récupérer la reservation correspondante
                            Reservation r = getTableView().getItems().get(getIndex());
                            ModifierReservationController modifController = new ModifierReservationController();
                            modifController.init(r.getNumRes());
                            // Ouvrir la fenêtre de modification pour cette reservation
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/ModifierReservation.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ModifierReservationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            ModifierReservationController controller = loader.getController();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.showAndWait();

                            // Rafraîchir la table des reservations
                            tableview.setItems(FXCollections.observableArrayList(rc.afficherMesReservations(2)));
                        });
                        btn2.setOnAction((ActionEvent event) -> {
                            // Récupérer l'offre correspondante
                            Reservation r = getTableView().getItems().get(getIndex());
                             AjouterFactureController factureController = new AjouterFactureController();
                           factureController.id=r.getNumRes();

                            // Suppression alert
                            ReservationCrud rc = new ReservationCrud();
                            rc.annulerReservation(r.getNumRes());
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("Réservation annulée!");
                            alert.show();

                            // Rafraîchir la table des reservations
                            tableview.setItems(FXCollections.observableArrayList(rc.afficherMesReservations(2)));
                        });
                        btn3.setOnAction((ActionEvent event) -> {
                            

                         FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/AjouterFacture.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                              AjouterFactureController controller = loader.getController();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.showAndWait();
                        });
                    }

                    // Cette méthode updateItem() sera appelée pour chaque ligne de la tableOffres,
                    // ce qui permettra de mettre un bouton "Modifier" pour chaque ligne
                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        HBox managebtn = new HBox(btn, btn2, btn3);
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(managebtn);

                        }
                    }
                };
                return cell;
            }
        });

    }

    @FXML
    private void close(javafx.scene.input.MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void back(ActionEvent event) {
        
                         FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/ReserverEvent.fxml"));
                            Parent root = null;
                            try {
                                root = loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(AjouterFactureController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             ReserverEventController controller = loader.getController();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.showAndWait();
    }

}