/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ewils.decentralize.view;

import be.ewils.decentralize.model.UserAccount;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author antoine
 */
public class CreateUserController implements Initializable {

    @FXML
    private TextField pseudo;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private Button actiontarget;

    @FXML
    protected void handleCreateAccountButtonAction(ActionEvent event) {
        UserAccount account = new UserAccount(pseudo.getText(), email.getText());
        account.setFirstName(firstName.getText());
        account.setLastName(lastName.getText());
        actiontarget.setText("Create Account for: " + account + " :: " + password.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
