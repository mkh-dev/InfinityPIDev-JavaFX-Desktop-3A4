/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import edu.pijava.model.Transport;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import edu.pijava.services.CRUDtransport;

import javafx.event.ActionEvent ;
import javafx.scene.layout.Background ;
import javafx.scene.layout.BackgroundFill ;
import javafx.scene.paint.Color ;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class ModifiertransportController implements Initializable {

    @FXML
    private TextField heur_depart_mod_tr;
    @FXML
    private TextField prix_tranport_mod_tr;
    @FXML
    private TextField lieu_arriver_mod_tr;
    @FXML
    private TextField lieu_depart_mod_tr;
    @FXML
    private DatePicker date_depart_mod_tr;
    @FXML
    private ComboBox<String> type_transport_mod_tr;
    @FXML
    private Button modifier_tra;

    Transport transport;
    int dataSet = 0;
    @FXML
    private AnchorPane modifierAnchorePane;
    @FXML
    private AnchorPane anchorpane_mod_tr;
    @FXML
    private Button Retour_modifier;
    
    @FXML
    private ColorPicker text_color_m;
    @FXML
    private ColorPicker back_color_m;
    @FXML
    private ColorPicker border_color_m;

    void Retour_modifier_trasport_button(ActionEvent event) {
    }

    ;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<String> options = FXCollections.observableArrayList(
                "Moto",
                "Voiture",
                "Mini bus",
                "Grand Bus",
                "Camion"
        );
        type_transport_mod_tr.setItems(options);
        // TODO
    }

    @FXML
    private void getData() {
        if (dataSet == 0) {
            Stage currentStage = (Stage) modifierAnchorePane.getScene().getWindow();
            this.transport = (Transport) currentStage.getUserData();

            /*remplissage des champs*/
            heur_depart_mod_tr.setText(this.transport.getHeure_depart());

            // Définir le format de la date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // Convertir la chaîne de caractères en LocalDate
            LocalDate date = LocalDate.parse(transport.getDate_depart(), formatter);

            date_depart_mod_tr.setValue(date);
            type_transport_mod_tr.setValue(this.transport.getType_transport());
            lieu_depart_mod_tr.setText(this.transport.getLieu_depart());
            lieu_arriver_mod_tr.setText(this.transport.getLieu_arriver());
            prix_tranport_mod_tr.setText(Float.toString(this.transport.getPrix_transport()));

            dataSet = 1;
        }
    }

    @FXML
    private void modifierTrasnsportButton() {
        String heure_depart = heur_depart_mod_tr.getText();
        String date_depart = date_depart_mod_tr.getValue().toString();
        String type_transport = type_transport_mod_tr.getValue();
        String lieu_depart = lieu_depart_mod_tr.getText();
        String lieu_arriver = lieu_arriver_mod_tr.getText();
        float prix_transport = Float.parseFloat(prix_tranport_mod_tr.getText());
        String text_color = text_color_m.getValue().toString();
        String back_color = back_color_m.getValue().toString();
        String border_color = border_color_m.getValue().toString();
        

        Transport t = new Transport(5, heure_depart, date_depart, type_transport, lieu_depart, lieu_arriver, prix_transport,text_color,back_color,border_color);
        t.setId_transport(transport.getId_transport());
        CRUDtransport crud = new CRUDtransport();
        crud.modifiertransport(t);

        try {
            Parent newParent = FXMLLoader.load(getClass().getResource("affichertransport.fxml"));
            Scene newScene = new Scene(newParent);
            Stage currentStage = (Stage) anchorpane_mod_tr.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void Retour_modifier_transport_button(ActionEvent event) {

        Retour_modifier.setOnAction(ev -> {
            try {
                Parent newParent = FXMLLoader.load(getClass().getResource("Affichertransport.fxml"));
                Scene newScene = new Scene(newParent);
                Stage currentStage = (Stage) modifierAnchorePane.getScene().getWindow();
                currentStage.setScene(newScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    @FXML
    private void change_color_text_mod(ActionEvent event) {
        
        
  Color myColor = text_color_m.getValue();
  text_color_m.setBackground(new Background(new BackgroundFill(myColor, null, null)));
    }

    @FXML
    private void change_color_back_mod(ActionEvent event) {
             
  Color myColor = back_color_m.getValue();
  back_color_m.setBackground(new Background(new BackgroundFill(myColor, null, null)));
    }

    @FXML
    private void change_color_border_mod(ActionEvent event) {
             
  Color myColor = border_color_m.getValue();
  border_color_m.setBackground(new Background(new BackgroundFill(myColor, null, null)));
    }

}

