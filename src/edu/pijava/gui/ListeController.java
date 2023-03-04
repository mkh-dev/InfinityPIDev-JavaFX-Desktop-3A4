package edu.pijava.gui;

import edu.pijava.model.Users;
import edu.pijava.services.UserService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ListeController implements Initializable {

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

}


