package com.example.java_project;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserHallinta extends Application {

    public String userNameCur = "";
    public String passwordCur = "";

    UserKomennot kom = new UserKomennot();

    public Button loginButton;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        //kom.createUser("Hello", "World");
        System.out.println("WORK");

        getLayout();
        Scene s = new Scene(getLayout(), 800, 600);
        stage.setScene(s);
        stage.setTitle("LOG IN: ");
        stage.show();
    }


    public UserHallinta(){
        loginButton = new Button("Log in");
    }

    public HBox getLayout() {

        Button tallenna = new Button("Tallenna");
        Label name = new Label("Nimi: ");
        Label salasana = new Label("Salasana: ");

        TextField nimiF = new TextField();
        TextField salaF = new TextField();

        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Lisää", "Muokkaa", "Poista")
        );
        cb.setValue("Lisää");



        cb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String selected = cb.getItems().get(t1.intValue()).toString();
                System.out.println("SELECTED: " + selected + " : " + t1.intValue());

                if(t1.intValue() == 1 || t1.intValue() == 2){
                    nimiF.setText("");
                    salaF.setText("");

                    nimiF.setEditable(false);
                    salaF.setEditable(false);

                    nimiF.setDisable(true);
                    salaF.setDisable(true);
                }
                else{
                    nimiF.setEditable(true);
                    salaF.setEditable(true);

                    nimiF.setDisable(false);
                    salaF.setDisable(false);
                }
            }
        });


        HBox vox = new HBox(1, cb, tallenna);
        VBox rightSide = new VBox(1,
                vox,
                name, nimiF,
                salasana, salaF
        );
        rightSide.setPrefWidth(500);


        ListView<String> list = new ListView<>();
        VBox leftSide = new VBox(10, list);
        leftSide.setPrefWidth(600);

        HBox mainLayout = new HBox(20, rightSide, leftSide);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setPrefSize(1920, 1080);

        return mainLayout;
    }

    public BorderPane getStartLayout() {
        BorderPane bp = new BorderPane();
        //loginButton = new Button("Log in");

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




        loginButton.setPrefWidth(100);

        VBox topLayout = new VBox(5, username, password, loginButton);
        topLayout.setAlignment(Pos.CENTER);

        bp.setCenter(topLayout);
        return bp;
    }

}
