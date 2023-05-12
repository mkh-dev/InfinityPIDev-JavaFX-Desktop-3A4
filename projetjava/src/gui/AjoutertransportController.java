
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import edu.pijava.model.Transport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import static javafx.scene.paint.Color.web;
import static javafx.scene.paint.Color.web;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import edu.pijava.services.CRUDtransport;
import netscape.javascript.JSObject;
import org.json.*;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import javafx.event.ActionEvent ;
import javafx.fxml.FXML ;
import javafx.geometry.Insets ;
import javafx.scene.control.ColorPicker ;
import javafx.scene.layout.Background ;
import javafx.scene.layout.BackgroundFill ;
import javafx.scene.layout.CornerRadii ;
import javafx.scene.layout.Pane ;
import javafx.scene.paint.Color ;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutertransportController implements Initializable {

    @FXML
    private Button Ajouter;
    @FXML
    private Button Annuler;
    @FXML
    private TextField lieu_arriver_aj;
    @FXML
    private TextField heure_depart_aj;
    @FXML
    private ComboBox<String> type_transport_aj;
    @FXML
    private TextField lieu_depart_aj;
    @FXML
    private TextField prix_transport_aj;
    @FXML
    private DatePicker date_depart_aj;
    @FXML
    private Button voir_ajoute;
    @FXML
    private AnchorPane anchorajoutertransport;
    @FXML
    private Button suivant_t;
    @FXML
    private AnchorPane anchore_suivant_t;
    @FXML
    private ColorPicker text_colour_btn;
    @FXML
    private ColorPicker back_colour_btn;
    @FXML
    private ColorPicker border_colour_btn;

    /* @FXML
    private WebView location_web;*/

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
        type_transport_aj.setItems(options);

        voir_ajoute.setOnAction((event) -> {
            try {
                Parent newParent = FXMLLoader.load(getClass().getResource("affichertransport.fxml"));
                Scene newScene = new Scene(newParent);
                Stage currentStage = (Stage) anchorajoutertransport.getScene().getWindow();
                currentStage.setScene(newScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        suivant_t.setOnAction((event) -> {
            try {
                Parent newParent = FXMLLoader.load(getClass().getResource("ajoutervehicule.fxml"));
                Scene newScene = new Scene(newParent);
                Stage currentStage = (Stage) anchore_suivant_t.getScene().getWindow();
                currentStage.setScene(newScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        //
        /*    
        // Créer une WebView pour charger la page Web
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Charger la page Web contenant du code JavaScript pour utiliser l'API Geolocation
        webEngine.loadContent("<html><body><script>" +
                "if (navigator.geolocation) {" +
                "  navigator.geolocation.getCurrentPosition(showPosition);" +
                "} else {" +
                "  alert('La géolocalisation n\'est pas prise en charge par ce navigateur.');" +
                "}" +
                "function showPosition(position) {" +
                "  alert('Latitude: ' + position.coords.latitude + ' Longitude: ' + position.coords.longitude);" +
                "}" +
                "</script></body></html>");

        // Obtenir l'objet JS de la page Web pour interagir avec le code JavaScript
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == State.SUCCEEDED) {
                JSObject jsObject = (JSObject) webEngine.executeScript("window");
                jsObject.setMember("geolocationExample", this);
            }
        });

        
        
        this.location_web=webView;*/
        //
        /* // Obtenir la position géographique à partir de l'API GeoIP
        String jsonText = GeoIP.getPosition();
        JSONObject jsonObject = new JSONObject(jsonText);
        String latitude = jsonObject.getString("latitude");
        String longitude = jsonObject.getString("longitude");

        // Charger la carte Google Maps dans la WebView
        WebEngine webEngine = web.getEngine();
        webEngine.load("https://www.google.com/maps/embed/v1/view?center=" + latitude + "," + longitude +
                "&zoom=15&key=YOUR_API_KEY");*/
 /*  WebEngine engine = location_web.getEngine();

        engine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) engine.executeScript("window");
                window.setMember("app", this);
            }
        });

        engine.loadContent("<html><body><script>"
                + "function getLocation() {"
                + "  if (navigator.geolocation) {"
                + "    navigator.geolocation.getCurrentPosition(showPosition);"
                + "  } else {"
                + "    alert('La géolocalisation n\'est pas prise en charge par ce navigateur.');"
                + "  }"
                + "}"
                + "function showPosition(position) {"
                + "  var latitude = position.coords.latitude;"
                + "  var longitude = position.coords.longitude;"
                + "  document.getElementById('location').innerHTML = 'Latitude: ' + latitude + '<br>Longitude: ' + longitude;"
                + "  getCityByCoordinates(latitude, longitude);"
                + "}"
                + "function getCityByCoordinates(latitude, longitude) {"
                + "  var xhr = new XMLHttpRequest();"
                + "  xhr.open('GET', 'https://freegeoip.app/json/' + latitude + ',' + longitude);"
                + "  xhr.onreadystatechange = function() {"
                + "    if (xhr.readyState === 4 && xhr.status === 200) {"
                + "      var response = JSON.parse(xhr.responseText);"
                + "      document.getElementById('city').innerHTML = 'City: ' + response.city;"
                + "    }"
                + "  }"
                + "  xhr.send();"
                + "}"
                + "</script>"
                + "<h1>Location:</h1>"
                + "<div id='location'></div>"
                + "<h1>City:</h1>"
                + "<div id='city'></div>"
                + "<button onclick='getLocation()'>Get Location</button>"
                + "</body></html>");*/
 


 

 
 
 
    }

    public String getJsonFromUrl(String url) throws IOException {
        URLConnection connection = new URL(url).openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public String getCityNameFromLocation(double latitude, double longitude) throws IOException {
        String url = "https://freegeoip.app/json/" + latitude + "," + longitude;
        String json = getJsonFromUrl(url);
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getString("city");
    }

    @FXML
    private void savetransport(ActionEvent event) {

        //int id_transport = Integer.parseInt(id_transport.getText());
        String heure_depart = heure_depart_aj.getText();
        String date_depart = date_depart_aj.getValue().toString();
        String type_transport = type_transport_aj.getValue();
        String lieu_depart = lieu_depart_aj.getText();
        String lieu_arriver = lieu_arriver_aj.getText();
        float prix_transport = Float.parseFloat(prix_transport_aj.getText());
        String text_color = text_colour_btn.getValue().toString();
        String back_color = back_colour_btn.getValue().toString();
        String border_color = border_colour_btn.getValue().toString();
        
        
       
        Transport t = new Transport(5, heure_depart, date_depart, type_transport, lieu_depart, lieu_arriver, prix_transport,text_color,back_color,border_color);
        CRUDtransport crud = new CRUDtransport();

        crud.ajoutertransport(t);
        Stage stage = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoutervehicule.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void getPosition() {
        try {
            // Appeler l'API ip-api pour obtenir les coordonnées géographiques
            String url = "http://ip-api.com/json";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Analyser la réponse JSON et extraire les coordonnées géographiques
            JSONObject json = new JSONObject(response.toString());
            System.out.println(response.toString());
            String town = json.getString("regionName");
            BigDecimal lat/*itude*/ = json.getBigDecimal("lat");
            String country = json.getString("country");
            String city = json.getString("city");
            BigDecimal lon/*itude*/ = json.getBigDecimal("lon");

            lieu_depart_aj.setText(country + "," + town + "," + city);

            // Afficher les coordonnées géographiques dans la WebView
            /* WebEngine webEngine = location_web.getEngine();
            webEngine.loadContent("<html><body><h1>Position actuelle :</h1><p>" + country + "," + town + ","+city+" </p></body></html>");*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void change_colour_text(ActionEvent event) {
  Color myColor = text_colour_btn.getValue();
  text_colour_btn.setBackground(new Background(new BackgroundFill(myColor, null, null)));
    }

    @FXML
    private void change_colour_back(ActionEvent event) {
        
  Color myColor = back_colour_btn.getValue();
  back_colour_btn.setBackground(new Background(new BackgroundFill(myColor, null, null)));
    }

    @FXML
    private void change_colour_border(ActionEvent event) {
        
  Color myColor = border_colour_btn.getValue();
  border_colour_btn.setBackground(new Background(new BackgroundFill(myColor, null, null)));
    }

}


