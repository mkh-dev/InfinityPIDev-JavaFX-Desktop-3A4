package edu.pijava.gui;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PartenaireController implements Initializable {
    
    @FXML
    public Button btnPartenaire;
    @FXML
    public Button btnCategorie;
         @FXML
    private Button deconnexionButton;
               @FXML
    private Button btReclam;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setBtnPartenaire(btnPartenaire);
        setBtnCategorie(btnCategorie);
         setBtnReclam(btReclam);
    }    
    
    public void openPartenaire() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CategorieProd.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void openCategorie() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("product.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void setBtnPartenaire(Button btnReclamation) {
        this.btnPartenaire = btnPartenaire;
        this.btnPartenaire.setOnAction(e -> {
            try {
                openPartenaire();
            } catch (IOException ex) {
               
            }
        });
    }
    
    public void setBtnCategorie(Button btnProduit) {
        this.btnCategorie = btnCategorie;
        this.btnCategorie.setOnAction(e -> {
            try {
                openCategorie();
            } catch (IOException ex) {
               
            }
        });
    }
    
    public void openReclam() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationsPartenaire.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void setBtnReclam(Button btReclam) {
        this.btReclam = btReclam;
        this.btReclam.setOnAction(e -> {
            try {
                openReclam();
            } catch (IOException ex) {
               
            }
        });
    }
    
    
    @FXML
    public void deconnexion(ActionEvent event) throws IOException {
        // Code pour déconnecter l'utilisateur de son compte
        // ...

        // Rediriger l'utilisateur vers la page de connexion
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) deconnexionButton.getScene().getWindow();
        stage.setScene(scene);
    }

}
