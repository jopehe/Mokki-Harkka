package com.example.java_project;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.*;
import java.time.*;

public class RaporttiHallinta extends Application {

    LocalDate startDate;
    LocalDate endDate;

    RaporttiKomennot kom = new RaporttiKomennot();

    ListView<String> laskuList;
    List<String> items;


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

    public RaporttiHallinta(){
        LocalDate date = LocalDate.now();
        startDate = date;
        endDate = date;
    }

    /**
     * Asettaa näkymän jossa eri raportointia koskevat tiedot sijaitsevat
     * @return
     */
    public HBox getLayout() {


        Button asiakasData= new Button("Asiakkaat");
        Button mokkitData = new Button("Mökkit");


        Button asiakasDataAika = new Button("Asiakkaat ajalla");
        Button mokkitDataAika = new Button("Mökkit ajalla");


        Button tulotPerMokki = new Button("Mökki tulot");
        Button tulotPerMokkiAika = new Button("Mökki tulot ajalla");


        tulotPerMokki.setOnAction(e ->{
            laskuList.getItems().clear();
            items = kom.tulotPerMokki();
            List<String> stringList = new ArrayList<>();
            for(int i = 0; i < items.size(); i++){
                stringList.add(items.get(i));
            }
            ObservableList<String> oblist = FXCollections.observableArrayList(stringList);
            laskuList.setItems(oblist);
        });


        tulotPerMokkiAika.setOnAction(e ->{
            laskuList.getItems().clear();
            items = kom.tulotPerMokkiPaivitain(startDate, endDate);
            List<String> stringList = new ArrayList<>();
            for(int i = 0; i < items.size(); i++){
                stringList.add(items.get(i));
            }
            ObservableList<String> oblist = FXCollections.observableArrayList(stringList);
            laskuList.setItems(oblist);
        });




        ///  Hae kaikki asiakaat joilla on varauksia
        asiakasData.setOnAction(actionEvent -> {
            laskuList.getItems().clear();
            items = kom.raporttiVarauksetPerAsiakas();
            List<String> stringList = new ArrayList<>();
            for(int i = 0; i < items.size(); i++){
                stringList.add(items.get(i));
            }
            ObservableList<String> oblist = FXCollections.observableArrayList(stringList);
            laskuList.setItems(oblist);
        });

        ///  Hae kaikki mokkit joilla on varauksia
        mokkitData.setOnAction(actionEvent -> {
            laskuList.getItems().clear();
            items = kom.raporttiVarauksetPerMokki();
            List<String> stringList = new ArrayList<>();
            for(int i = 0; i < items.size(); i++){
                stringList.add(items.get(i));
            }
            ObservableList<String> oblist = FXCollections.observableArrayList(stringList);
            laskuList.setItems(oblist);
        });

        mokkitDataAika.setOnAction(actionEvent -> {
            laskuList.getItems().clear();
            List<String> stringList = new ArrayList<>();
            items = kom.raporttiVarauksetPerMokkiAikavalilla(startDate, endDate);
            for(int i = 0; i < items.size(); i++){
                stringList.add(items.get(i));
            }
            ObservableList<String> oblist = FXCollections.observableArrayList(stringList);
            laskuList.setItems(oblist);
        });



        asiakasDataAika.setOnAction(actionEvent -> {
            laskuList.getItems().clear();
            List<String> stringList = new ArrayList<>();

            System.out.println("DATES: " + startDate.toString() + " : " + endDate.toString());
            items = kom.raporttiVarauksetPerAsiakasAikavalilla(startDate.toString(), endDate.toString());
            for(int i = 0; i < items.size(); i++){
                System.out.println("OK: " + i);
                stringList.add(items.get(i));
            }
            ObservableList<String> oblist = FXCollections.observableArrayList(stringList);
            laskuList.setItems(oblist);
        });


        Label normaalivalilla = new Label("Normaali: ");
        Label aikavalilla = new Label("Aikavälillä: ");
        Label tulotvalilla = new Label("Tulot: ");



        HBox norm = new HBox(5, normaalivalilla, asiakasData, mokkitData);
        HBox aikanorm = new HBox(5, aikavalilla, mokkitDataAika, asiakasDataAika);
        HBox tulot = new HBox(5, tulotvalilla, tulotPerMokki, tulotPerMokkiAika);


        Button palaa = new Button("Palaa");
        HBox aikavalit = new HBox(1,  palaa);

        Button tulotData= new Button("Tulot");
        VBox topLayout = new VBox(1,
                norm,
                aikanorm,
                tulot,
                tulotData
        );




        Label sLabel = new Label("Start date: ");
        Label eLabel = new Label("End date: ");
        LocalDate t = LocalDate.now();
        TextField startDay = new TextField("" + t);
        TextField endDay = new TextField("" + t);




        startDay.setOnAction(e ->{
            String text = startDay.getText();
            try{
                LocalDate d = LocalDate.parse(text);
                startDate = d;
                System.out.println("" + d);
            } catch (DateTimeException E){
                LocalDate dn = LocalDate.now();
                startDate = dn;
                startDay.setText("" + dn);
            }
        });

        endDay.setOnAction(e ->{
            String text = endDay.getText();
            try{
                LocalDate d = LocalDate.parse(text);
                endDate = d;
                System.out.println("" + d);
            } catch (DateTimeException E){
                LocalDate dn = LocalDate.now();
                endDate = dn;
                endDay.setText("" + dn);
            }
        });


        HBox things = new HBox(10, sLabel, startDay, eLabel, endDay);


        VBox rightSide = new VBox(1,
                topLayout,
                aikavalit,
                things
        );
        rightSide.setPrefWidth(500);


        laskuList = new ListView<>();
        VBox leftSide = new VBox(10, laskuList);
        leftSide.setPrefWidth(600);

        HBox mainLayout = new HBox(20, rightSide, leftSide);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setPrefSize(1920, 1080);

        return  mainLayout;
    }
}
