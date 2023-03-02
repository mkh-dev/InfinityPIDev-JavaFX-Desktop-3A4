/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CRUDtransport;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class StatistiqueController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private BarChart<String, Number> barChart;
    
    CRUDtransport crud = new CRUDtransport();
    @FXML
    private Button retour_statistique;
    @FXML
    private AnchorPane anchopane;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Map<String, Integer> stats = crud.getSTatsTypeTrans();
        
        
        
        /*     "Moto",
                "Voiture",
                "Mini bus",
                "Grand Bus",
                "Camion"*/
        
        // Créer les données pour chaque barre
        XYChart.Data<String, Number> dataA = new XYChart.Data<>("Voiture", stats.get("Voiture"));
        XYChart.Data<String, Number> dataB = new XYChart.Data<>("MiniBus", stats.get("MiniBus"));
        XYChart.Data<String, Number> dataC = new XYChart.Data<>("Moto", stats.get("Moto"));
        XYChart.Data<String, Number> dataD = new XYChart.Data<>("GrandBus", stats.get("GrandBus"));
        XYChart.Data<String, Number> dataE = new XYChart.Data<>("Camion", stats.get("Camion"));

        // Créer une série de données et ajouter les données pour chaque barre
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Données");
        series.getData().add(dataA);
        series.getData().add(dataB);
        series.getData().add(dataC);
        series.getData().add(dataD);
        series.getData().add(dataE);

        // Ajouter la série de données au barchart
        barChart.getData().add(series);
     /*   */
     
     
     
     retour_statistique.setOnAction(event -> {
            try {
                Parent newParent = FXMLLoader.load(getClass().getResource("affichertransport.fxml"));
                Scene newScene = new Scene(newParent);
                Stage currentStage = (Stage) anchopane.getScene().getWindow();
                currentStage.setScene(newScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
     
     
    }    
    
}



/*

je veux crée un interface pour afficher les statistuqes sure un barchart, 
j'ai 3 donnée a representer a,b et c  ,montre excatctement la démarche a suivre pour pouvoir 
aficher ses données sure le barchart sachant que le code dois etre 
dans le controller de mon interface StatistiqueController
*/