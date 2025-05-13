package com.example.java_project;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Time;


import java.util.*;
import java.time.*;

public class RaporttiHallinta extends Application {

    final String[] month = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    final String[] year = new String[] {"2025", "2026", "2027", "2028", "2029", "2030"};

    private int maxDate = 31;

    private int curYear = 2025;
    private int curMonth = 1;


    RaporttiKomennot kom = new RaporttiKomennot();


    private int selectedYear;
    private int selectedMonth;


    ListView<String> laskuList;
    List<String> items;


    public static void main(String[] args) {
        System.out.println("HELLO WORLD!");
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        LocalDate date = LocalDate.now();

        curYear = date.getYear();
        curMonth = date.getMonth().getValue();

        selectedMonth = curMonth;
        selectedYear = curYear;

        System.out.println("DATE: " + curYear + "-" + curMonth + "-" + maxDate);


        Scene scene = new Scene(getLayout());
        primaryStage.setScene(scene);
        primaryStage.setTitle("RAPORTOINTI");
        primaryStage.show();
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


        Button varauksetData= new Button("Varaukset");




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
            items = kom.raporttiVarauksetPerMokkiAikavalilla(
                    LocalDate.of(curYear, curMonth, 1),
                    LocalDate.of( curYear + 1, curMonth, 31));

            List<String> stringList = new ArrayList<>();
            for(int i = 0; i < items.size(); i++){
                stringList.add(items.get(i));
            }
            ObservableList<String> oblist = FXCollections.observableArrayList(stringList);
            laskuList.setItems(oblist);
        });



        asiakasDataAika.setOnAction(actionEvent -> {
            laskuList.getItems().clear();
            items = kom.raporttiVarauksetPerAsiakasAikavalilla(
                    LocalDate.of(curYear, curMonth, 1),
                    LocalDate.of(curYear + 1, curMonth, 31));

            List<String> stringList = new ArrayList<>();
            for(int i = 0; i < items.size(); i++){
                stringList.add(items.get(i));
            }
            ObservableList<String> oblist = FXCollections.observableArrayList(stringList);
            laskuList.setItems(oblist);
        });






        Button tulotData= new Button("Tulot");
        HBox topLayout = new HBox(1, asiakasData, mokkitData, asiakasDataAika, mokkitDataAika, varauksetData, tulotData);



        Button palaa = new Button("Palaa");
        HBox aikavalit = new HBox(1,  palaa);



        /// Asetetaan kuukaudet listaan
        ObservableList<String> items = FXCollections.observableArrayList(month);
        final ComboBox comboBox = new ComboBox(items);
        comboBox.setPromptText("" + curMonth);
        comboBox.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                curMonth = Integer.valueOf(t1.toString());
                System.out.println(curYear + "-" + curMonth);
            }
        });


        /// Asetetaan vuosi listaan
        ObservableList<String> itemsy = FXCollections.observableArrayList(year);
        final ComboBox comboBoxy = new ComboBox(itemsy);
        comboBoxy.setPromptText("" + curYear);
        comboBoxy.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                curYear = Integer.valueOf(t1.toString());
                System.out.println(curYear + "-" + curMonth);
            }
        });

        Label kaikki = new Label("Kaikki: ");
        Label aikavalilla = new Label("Aikavalilla: ");

        RadioButton kaikkiValinta = new RadioButton();
        RadioButton aikavaliValinta = new RadioButton();



        Label asiakasLable = new Label("Asiakkaat: ");
        Label mokkiLable = new Label("Mökkit: ");
        //Label varausLable = new Label("Varaukset: ");


        RadioButton asiakasValinta = new RadioButton();
        RadioButton mokkiValinta = new RadioButton();




        ToggleGroup group = new ToggleGroup();
        asiakasValinta.setToggleGroup(group);
        mokkiValinta.setToggleGroup(group);


        /// Currentday
        Button currentYear = new Button("TODAY");
        currentYear.setOnAction(e -> {
            selectedYear = curYear;
            selectedMonth = curMonth;
        });


        VBox rightSide = new VBox(1,
                topLayout,
                aikavalit ,
                comboBox,
                comboBoxy,
                currentYear
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
