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

public class TransporteurController implements Initializable {
    
    @FXML
    public Button btnTransport;

         @FXML
    private Button deconnexionButton;
                      @FXML
    private Button btReclam;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setBtnTransport(btnTransport);
         setBtnReclam(btReclam);
    }    
    
    public void openTransport() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ajoutertransport.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    
    public void setBtnTransport(Button btnReclamation) {
        this.btnTransport = btnTransport;
        this.btnTransport.setOnAction(e -> {
            try {
                openTransport();
            } catch (IOException ex) {
               
            }
        });
    }
    
    
    public void openReclam() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ReclamationsTransporteur.fxml"));
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
