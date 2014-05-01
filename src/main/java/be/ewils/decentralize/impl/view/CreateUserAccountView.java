/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ewils.decentralize.impl.view;

import be.ewils.decentralize.impl.model.UserAccount;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateUserAccountView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label pseudoLabel = new Label("Pseudo:");
        grid.add(pseudoLabel, 0, 1);

        final TextField pseudoTextField = new TextField();
        grid.add(pseudoTextField, 1, 1);
        
        Label userFirstNameLabel = new Label("User Name:");
        grid.add(userFirstNameLabel, 0, 2);

        final TextField userFirstNameTextField = new TextField();
        grid.add(userFirstNameTextField, 1, 2);
        
        Label userLastNameLabel = new Label("Last Name:");
        grid.add(userLastNameLabel, 0, 3);

        final TextField userLastNameTextField = new TextField();
        grid.add(userLastNameTextField, 1, 3);
        
        Label emailLabel = new Label("e-mail:");
        grid.add(emailLabel, 0, 4);

        final TextField emailTextField = new TextField();
        grid.add(emailTextField, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 8);
        
        Button btn = new Button("Sign in");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                UserAccount userAccount = new UserAccount(pseudoTextField.getText(), emailTextField.getText());
                userAccount.setFirstName(userFirstNameTextField.getText());
                userAccount.setLastName(userLastNameTextField.getText());
                System.out.println("registered " + userAccount);
            }
        });        
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 6);        

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
