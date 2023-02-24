package edu.pijava.gui;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PartenaireController implements Initializable {
    
    @FXML
    public Button btnReclamation;
    @FXML
    public Button btnProduit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setBtnReclamation(btnReclamation);
        setBtnProduit(btnProduit);
    }    
    
    public void openReclamation() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationsPartenaire.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void openProduit() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void setBtnReclamation(Button btnReclamation) {
        this.btnReclamation = btnReclamation;
        this.btnReclamation.setOnAction(e -> {
            try {
                openReclamation();
            } catch (IOException ex) {
               
            }
        });
    }
    
    public void setBtnProduit(Button btnProduit) {
        this.btnProduit = btnProduit;
        this.btnProduit.setOnAction(e -> {
            try {
                openProduit();
            } catch (IOException ex) {
               
            }
        });
    }
}
