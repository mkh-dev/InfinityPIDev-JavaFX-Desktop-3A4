package edu.pijava.gui;


import edu.pijava.model.produit;
import edu.pijava.services.produit_service;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class ListeController1 implements Initializable {


    @FXML
    private ListView<produit> usersListView;


    @FXML
    private Label userCountLabel;

    @FXML
   private TextField searchField;


    private ObservableList<produit> userListObservable;
    @FXML
    private Button handleButtonAction;
    @FXML
    private Button Supprimerprod;
    @FXML
    private Button btnrevenir;
    @FXML
    private Button btnmodify;
    static int id_prod;
public void init(int id_prod){
    this.id_prod = id_prod;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produit_service ps = new produit_service();
        userListObservable = FXCollections.observableArrayList(ps.afficherprod());
        usersListView.setItems(userListObservable);
        userCountLabel.setText("Total des produits : " + userListObservable.size());
    }

    @FXML
private void handleRefresh() {
    produit_service ps = new produit_service();
    userListObservable.setAll(ps.afficherprod());
    userCountLabel.setText("Total des produits : " + userListObservable.size());
    
}

@FXML
private void handleButtonAction(ActionEvent event) throws IOException {
    Parent destinationParent = FXMLLoader.load(getClass().getResource("Third.fxml"));
    Scene destinationScene = new Scene(destinationParent);
   Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(destinationScene);
    window.show();
}
 //   @FXML
   // private void handleSearch() {
     //   String searchQuery = searchField.getText().trim().toLowerCase();
       // if (searchQuery.isEmpty()) {
       //     usersListView.setItems(userListObservable);
       // } else {
        //    ObservableList<Users> filteredList = FXCollections.observableArrayList();
          //  for (Users user : userListObservable) {
            //    if (user.getNom().toLowerCase().contains(searchQuery)
              //          || user.getPrenom().toLowerCase().contains(searchQuery)
                //        || user.getEmail().toLowerCase().contains(searchQuery)) {
                 //   filteredList.add(user);
               // }
            //}
            //usersListView.setItems(filteredList);
        //}
        //userCountLabel.setText("Total des utilisateurs : " + usersListView.getItems().size());
    //}

    @FXML
    private void Supprimerprod(ActionEvent event) {
        if (!usersListView.getSelectionModel().isEmpty()) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sur de vouloir supprimer le produit d'id num " + usersListView.getSelectionModel().getSelectedItem().getId_prod()+ " ?", ButtonType.YES, ButtonType.NO);
alert1.showAndWait();
        
         produit selectedSERVICE =  usersListView.getSelectionModel().getSelectedItem();

         produit_service ps= new produit_service();
         ps.supprimerprod(selectedSERVICE);
        if (alert1.getResult() == ButtonType.YES) {
         ps.supprimerprod(selectedSERVICE);
    };
    
        Alert alert2 = new Alert(AlertType.INFORMATION, "Produit Supprimé.", ButtonType.OK);
    alert2.showAndWait();
   
        
    }
    }

    @FXML
    private void btnrevenir(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("product.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnmodify(ActionEvent event) {
        
         produit selectedSERVICE =  usersListView.getSelectionModel().getSelectedItem();
        if (selectedSERVICE == null) {
            // Afficher un message d'erreur pour informer l'utilisateur qu'il doit sélectionner un utilisateur avant de pouvoir le modifier
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une réservation à modifier.");
            alert.showAndWait();
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pijava/gui/ModifyProduct.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ModifyProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModifyProductController modifierproductController = loader.getController();
        modifierproductController.init(selectedSERVICE.getId_prod());

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        //Rafraîchir la liste des utilisateurs après la mise à jour
        //refresh();
    }


}


