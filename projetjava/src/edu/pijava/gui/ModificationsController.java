package edu.pijava.gui;

import edu.pijava.model.Users;
import edu.pijava.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModificationsController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ListView<Users> usersListView;

    @FXML
    private Button refreshButton;

    @FXML
    private Label userCountLabel;

    @FXML
    private TextField searchField;

    private UserService userService;

    private ObservableList<Users> userListObservable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userService = new UserService();
        userListObservable = FXCollections.observableArrayList(userService.afficherUtilisateur());
        usersListView.setItems(userListObservable);
        userCountLabel.setText("Total des utilisateurs : " + userListObservable.size());
    }

    @FXML
    private void handleRefresh() {
        userListObservable.setAll(userService.afficherUtilisateur());
        userCountLabel.setText("Total des utilisateurs : " + userListObservable.size());
    }

    @FXML
    private void handleSearch() {
        String searchQuery = searchField.getText().trim().toLowerCase();
        if (searchQuery.isEmpty()) {
            usersListView.setItems(userListObservable);
        } else {
            ObservableList<Users> filteredList = FXCollections.observableArrayList();
            for (Users user : userListObservable) {
                if (user.getNom().toLowerCase().contains(searchQuery)
                        || user.getPrenom().toLowerCase().contains(searchQuery)
                        || user.getEmail().toLowerCase().contains(searchQuery)) {
                    filteredList.add(user);
                }
            }
            usersListView.setItems(filteredList);
        }
        userCountLabel.setText("Total des utilisateurs : " + usersListView.getItems().size());
    }
    
    @FXML
    private void handleAddUser() throws IOException {
        // Charger le fichier FXML de la fenêtre addUser.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addUser.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        
        // Créer une nouvelle scène et y ajouter le layout chargé depuis le fichier FXML
        Scene scene = new Scene(root);
        
        // Créer une nouvelle fenêtre modale
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Ajouter utilisateur");
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    @FXML
    private void handleDeleteUser() {
        Users selectedUser = usersListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            userService.supprimerUtilisateur(selectedUser);
            userListObservable.remove(selectedUser);
            userCountLabel.setText("Total des utilisateurs : " + userListObservable.size());
        }
    }
    
    @FXML
private void handleEditUser() throws IOException {
    Users selectedUser = usersListView.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateUser.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        UpdateUserController updateUserController = fxmlLoader.getController();
        updateUserController.initData(selectedUser);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        // Rafraîchir la liste des utilisateurs après la mise à jour
        handleRefresh();
}
}


    