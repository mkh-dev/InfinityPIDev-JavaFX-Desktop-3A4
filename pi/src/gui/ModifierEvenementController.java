/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import Entities.CategorieEvenement;
import Entities.Evenement;
import Services.CRUDCategorieEvenement;
import Services.CRUDEvenement;
import com.itextpdf.text.pdf.parser.Path;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author miguel
 */
public class ModifierEvenementController implements Initializable {

    // Définir l'URL de l'API Bing Maps REST Services pour récupérer des suggestions d'adresses
    final String API_URL = "http://dev.virtualearth.net/REST/v1/Autosuggest?query=%s&key=%s&culture=fr-FR";

    // Définir la clé de l'API Bing Maps REST Services
    final String API_KEY = "1ypPjTb0ZEq5p7TlO9G9~ZGsxNCOLW7O7WVc_a0HnCw~Ap-qvOn7q7Ae3cr4WqJBE9y8CVABfKzMhdbrIwjRkVUYaYzVSZw10aZK8NijFdH4";

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tflieu;
    @FXML
    private Button btnajouter;
    @FXML
    private DatePicker datePicker1;
    @FXML
    private DatePicker datePicker2;
    @FXML
    private TextArea tfdescription;
    @FXML
    private Button btnselectproduct;
    @FXML
    private ListView<String> sugestionAddress;
    @FXML
    private ComboBox ftcatevent;
    @FXML
    private ComboBox fttransport;
    @FXML
    private Button ftimage;
    @FXML
    private Label imageNameLabel;
    @FXML
    private Label ftbudget;

    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();

    int budgetTotal;
    int budgetProduit = 0;
    int budgetTransport = 0;

    Evenement evt;
    int dataSet = 0;

    AjouterEvenementController Control = new AjouterEvenementController();
    @FXML
    private AnchorPane modifierAnchorePane;
    @FXML
    private ImageView imageActuel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        // Ajouter un écouteur sur le champ de texte pour récupérer les suggestions d'adresses à mesure que l'utilisateur saisit des données
        tflieu.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            // Effectuer une requête HTTP à l'API Bing Maps REST Services pour récupérer des suggestions d'adresses basées sur les données saisies par l'utilisateur
            if (!newValue.isEmpty() || newValue != null) {
                try {
                    // Encoder les données saisies par l'utilisateur pour les inclure dans l'URL de l'API
                    String encodedQuery = URLEncoder.encode(newValue, StandardCharsets.UTF_8.toString());

                    // Créer l'URL de l'API en remplaçant %s par la clé de l'API et les données saisies par l'utilisateur
                    URL apiUrl = new URL(String.format(API_URL, encodedQuery, API_KEY));

                    // Ouvrir une connexion HTTP à l'API
                    HttpURLConnection conn = (HttpURLConnection) apiUrl.openConnection();
                    conn.setRequestMethod("GET");

                    // Lire la réponse de l'API
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // Analyser la réponse JSON de l'API pour récupérer les suggestions d'adresses
                    JSONObject json = new JSONObject(response.toString());
                    System.out.println("response" + response.toString());
                    JSONArray results = json.getJSONArray("resourceSets").getJSONObject(0).getJSONArray("resources").getJSONObject(0).getJSONArray("value");
                    List<String> addresses = new ArrayList<>();
                    for (int i = 0; i < results.length(); i++) {

                        JSONObject result = results.getJSONObject(i);
                        JSONObject address = result.getJSONObject("address");
                        String adminDistrict2 = address.getString("formattedAddress");

                        addresses.add(adminDistrict2);
                    }

                    // Mettre à jour les suggestions d'adresses dans l'interface utilisateur JavaFX
                    Platform.runLater(() -> {
                        sugestionAddress.getItems().clear();
                        sugestionAddress.getItems().addAll(addresses);
                    });

                    // Fermer la connexion HTTP à l'API
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Ajouter un écouteur sur la ListView pour mettre à jour le champ de texte lorsque l'utilisateur sélectionne une suggestion
        sugestionAddress.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                tflieu.setText(newValue);
                sugestionAddress.getItems().clear();
            }
        });

        CRUDCategorieEvenement crud = new CRUDCategorieEvenement();

        List<String> items = crud.ListNomCat();
        List<CategorieEvenement> item = crud.affichercatevent();
        ObservableList<String> observableItems = FXCollections.observableArrayList(items);

        ftcatevent.setItems(observableItems);

        List<String> itemsT = Control.ListNomTrans();
        //List<CategorieEvenement> item = crud.affichercatevent();
        ObservableList<String> observableItemsT = FXCollections.observableArrayList(itemsT);

        fttransport.setItems(observableItemsT);

        btnselectproduct.setOnAction((event) -> {
            try {
                // charger la fenêtre popup à partir de son fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduitPopUp.fxml"));
                Parent root = loader.load();

// créer une nouvelle fenêtre et définir sa scène
                Stage popupStage = new Stage();
                popupStage.setScene(new Scene(root));

// récupérer le contrôleur de la fenêtre popup
                AddProduitPopUpController popupController = loader.getController();

// définir l'action à exécuter lorsque le bouton "Add" est cliqué
                popupController.getAddButton().setOnAction((ActionEvent ev) -> {
                    // fermer la fenêtre popup et retourner le chiffre sélectionné
                    popupStage.close();
                    budgetProduit = popupController.getSelectedNumber();
                    // faire quelque chose avec le chiffre sélectionné

                    budgetTotal = budgetProduit + budgetTransport;

                    ftbudget.setText(Integer.toString(budgetTotal));
                });

// afficher la fenêtre popup
                popupStage.show();
            } catch (IOException ex) {
                System.out.println(ex);
            }

        });

        fttransport.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                budgetTransport = Control.getPrixTransport(newValue.toString());
                budgetTotal = budgetProduit + budgetTransport;

                ftbudget.setText(Integer.toString(budgetTotal));
            }
        });

    }

    @FXML
    private void onTfLieuKeyTyped(ActionEvent event) {
    }

    @FXML
    private void ModiferEvent(ActionEvent event) {

        int id_event = 0;
        String nom_event = tfnom.getText();
        String description_event = tfdescription.getText();
        String date_debut_event = datePicker1.getValue().toString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dateDebut = null;
        try {
            java.util.Date date = dateFormat.parse(date_debut_event);
            long time = date.getTime();
            dateDebut = new java.sql.Date(time);
        } catch (ParseException ex) {
            Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String date_fin_event = datePicker2.getValue().toString();

        java.sql.Date dateFin = null;
        try {
            java.util.Date date = dateFormat.parse(date_fin_event);
            long time = date.getTime();
            dateFin = new java.sql.Date(time);
        } catch (ParseException ex) {
            System.out.println(ex);
        }

        String lieu_event = tflieu.getText();

        String str = ftbudget.getText();
        int index = str.indexOf(".");
        if (index != -1) {
            str = str.substring(0, index);
        }

        double budget_event = Integer.parseInt(str);
        int id_transport = Control.getIdTransport(fttransport.getValue().toString());
        String image = imageNameLabel.getText();

        CRUDCategorieEvenement crud = new CRUDCategorieEvenement();

        int id_cat_event = crud.getCatId(ftcatevent.getValue().toString());

        Evenement E = new Evenement(nom_event, description_event, dateDebut, dateFin, lieu_event, budget_event, id_transport, image, id_cat_event);
        E.setId_event(this.evt.getId_event());

        CRUDEvenement crudE = new CRUDEvenement();
        crudE.ModifierEvenement(E);

        try {
            Parent newParent = FXMLLoader.load(getClass().getResource("AfficherEvenement.fxml"));
            Scene newScene = new Scene(newParent);
            Stage currentStage = (Stage) modifierAnchorePane.getScene().getWindow();
            currentStage.setScene(newScene);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void selectImage(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sélectionner un fichier");
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {

            try {
                // Créer un dossier de destination pour l'image
                File destinationFolder = new File("src/gui/Data/Images");
                if (!destinationFolder.exists()) {
                    destinationFolder.mkdirs();
                }

                // Copier l'image sélectionnée dans le dossier de destination
                java.nio.file.Path sourcePath = Paths.get(selectedFile.toURI());
                java.nio.file.Path destinationPath = Paths.get(destinationFolder.getPath(), selectedFile.getName());
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

                // Obtenir le lien de l'emplacement de la copie de l'image
                String imagePath = destinationPath.toUri().toString();

                String chaine = imagePath;

                // Découpage de la chaîne à partir de "Data"
                int index = chaine.indexOf("Data");
                if (index == -1) {
                    System.out.println("La chaîne ne contient pas \"Data\"");
                    return;
                }
                String sousChaine = chaine.substring(index);

                imageNameLabel.setText(sousChaine);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    private void getData() {
        if (dataSet == 0) {
            Stage currentStage = (Stage) modifierAnchorePane.getScene().getWindow();
            this.evt = (Evenement) currentStage.getUserData();

            /*remplissage des champs*/
            tfnom.setText(this.evt.getNom_event());

            // Définir le format de la date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // Convertir la chaîne de caractères en LocalDate
            LocalDate date = LocalDate.parse(this.evt.getDate_debut_event().toString(), formatter);

            datePicker1.setValue(date);

            LocalDate date2 = LocalDate.parse(this.evt.getDate_fin_event().toString(), formatter);

            datePicker2.setValue(date2);
            
            System.out.println("idTrans:"+this.evt.getId_transport());
            fttransport.setValue(getNomTransport(this.evt.getId_transport()));

            tflieu.setText(this.evt.getLieu_event());

            tfdescription.setText(this.evt.getDescription_event());

            double budg = this.evt.getBudget_event();
            ftbudget.setText(Double.toString(budg));

            imageActuel.setImage(new Image("gui/" + this.evt.getImage()));

            System.out.println("idcat:"+this.evt.getId_cat_event());
            ftcatevent.setValue(getNomCategorie(this.evt.getId_cat_event()));

            imageNameLabel.setText(this.evt.getImage());

            dataSet = 1;
        }
    }

    public String getNomTransport(int idT) {
        String nom = "";
        try {
            String req = "SELECT type_transport FROM transport where id_transport='" + idT + "'";
            Statement ste = conn.createStatement();
            ResultSet result = ste.executeQuery(req);

            while (result.next()) {

                nom = result.getString("type_transport");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("trans:"+nom);
        return nom;
    }

    public String getNomCategorie(int idT) {
        String nom = "";
        try {
            String req = "SELECT cat_event FROM categorie_event where id_cat_event='" + idT + "'";
            Statement ste = conn.createStatement();
            ResultSet result = ste.executeQuery(req);

            while (result.next()) {

                nom = result.getString("cat_event");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("cat:"+nom);
        return nom;
    }

}
