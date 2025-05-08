package com.example.java_project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RaporttiHallinta extends Application {

    final String[] month = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    final String[] year = new String[] {"2025", "2026", "2027", "2028", "2029", "2030"};



    public static void main(String[] args) {
        System.out.println("HELLO WORLD!");
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {





        Scene scene = new Scene(getLayout());
        primaryStage.setScene(scene);
        primaryStage.setTitle("RAPORTOINTI");
        primaryStage.show();
    }

    public HBox getLayout() {


        Button asiakasData= new Button("Asiakkaat");
        Button mokkitData = new Button("Mökkit");
        Button varauksetData= new Button("Varaukset");
        Button tulotData= new Button("Tulot");
        HBox topLayout = new HBox(1, asiakasData, mokkitData, varauksetData, tulotData);


        Button kuukaus = new Button("Kuukaus");
        Button vuosi = new Button("Vuosi");
        Button palaa = new Button("Palaa");
        HBox aikavalit = new HBox(1, kuukaus, vuosi, palaa);


        /*

        Label idLabel = new Label("ID: ");
        TextField IdAsetus = new TextField("");

        Label osoiteLabel = new Label("Osoite: ");
        TextField osoiteAsetus = new TextField("");

        Label hintaLabel = new Label("Hinta: ");
        TextField hintaAsetus = new TextField("");

        Label kokoLabel = new Label("Koko: ");
        TextField kokoAsetus = new TextField("");
    */


        VBox rightSide = new VBox(1,
                topLayout,
                aikavalit //,
                //idLabel, IdAsetus,
                //osoiteLabel, osoiteAsetus,
                //hintaLabel, hintaAsetus,
                //kokoLabel, kokoAsetus);
                );
        rightSide.setPrefWidth(500);


        ListView<String> laskuList = new ListView<>();
        VBox leftSide = new VBox(10, laskuList);
        leftSide.setPrefWidth(600);

        HBox mainLayout = new HBox(20, rightSide, leftSide);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setPrefSize(1920, 1080);

        return  mainLayout;
    }
}
