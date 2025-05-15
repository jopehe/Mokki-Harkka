package com.example.java_project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserHallinta extends Application {

    private String userNameCur = "";
    private String passwordCur = "";

    UserKomennot kom = new UserKomennot();

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        //kom.createUser("mikko123", "123123");
        System.out.println("WORK");

        getLayout();
        Scene s = new Scene(getLayout(), 800, 600);
        stage.setScene(s);
        stage.setTitle("LOG IN: ");
        stage.show();
    }

    public BorderPane getLayout() {
        BorderPane bp = new BorderPane();

        TextField password = new TextField("Password: ");
        TextField username = new TextField("Username: ");
        password.setMaxWidth( 100);
        username.setMaxWidth( 100);


        password.setOnAction(e ->{
            passwordCur = password.getText();
        });

        username.setOnAction(e -> {
            userNameCur = username.getText();
        });




        Button login = new Button("Log in");

        login.setOnAction(e ->{
            if(kom.findUser(userNameCur, passwordCur)){
                System.out.println("USER FOUND:  " + userNameCur + ", " + passwordCur);
            }
            else{
                System.out.println("USER NOT FOUND: " + userNameCur + ", " + passwordCur);
            }
        });

        login.setPrefWidth(100);

        VBox topLayout = new VBox(1, username, password,  login);
        topLayout.setAlignment(Pos.CENTER);


        bp.setCenter(topLayout);


        return bp;
    }

}
