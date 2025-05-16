package com.example.java_project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class LaskuHallinta extends Application {
    private TextField idAsetus;
    private TextField varausidAsetus;
    private TextField luontipvAsetus;
    private TextField erapvmAsetus;
    private TextField ytunnusAsetus;
    private TextField viiteAsetus;
    private TextField hintaAsetus;
    private TextField maksettuAsetus;

    private Button lisaaLasku;
    private Button muokkaaLaskua;
    private Button poistaLasku;


    LaskuKomennot kom = new LaskuKomennot();

    private List<Lasku> laskut= new ArrayList<>();
    private List<String> items = new ArrayList<>();
    private ListView<String> laskuList = new ListView<>();

    private int id;
    private int varaus_id;
    private Date luonti_pvm;
    private Date era_pvm;
    private String y_tunnus;
    private String viitenumero;
    private double hinta;
    private boolean maksettu;



    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {


        Scene scene = new Scene(getLayout());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Lakujen Hallinta");
        primaryStage.show();


    }

    private boolean getBooleanValue(String anser){
        try {
            return Boolean.parseBoolean(anser);
        } catch (Exception e) {
            System.out.println("Error: Boolean value could not be translated from, ' " + anser + " ' string.");
            return false;
        }
    }
    private Date getDateValue(String anser) {
        try {
            return Date.valueOf(anser);
        } catch (Exception e) {
            System.out.println("Error: Date value could not be translated from, '" + anser + "' string.");
            return null;
        }
    }
    private double getDoubleValue(String anser){
        try{
            double result = Double.parseDouble(anser);
            return result;

        } catch (Exception E){
            System.out.println("Error: Double value could not be translated from, ' " + anser + " ' string.");
        }
        return -1.0;
    }
    private int getIntValue(String anser){
        try{
            int result = Integer.parseInt(anser);
            return result;
        } catch (Exception E){
            System.out.println("Error: Int value could not be translated from, ' " + anser + " ' string. " + E);
        }
        return -1;
    }



    public void updateTextArea(){
        System.out.println("SAVED");
        laskuList.getItems().clear();
        laskut = kom.getAllLaskut();

        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < laskut.size(); i++){
            stringList.add(laskut.get(i).getString());
        }
        ObservableList<String> oblist = FXCollections.observableArrayList(stringList);
        laskuList.setItems(oblist);
    }

    public void textArea() {
//        idAsetus.setOnAction(e ->{
//            String text = idAsetus.getText();
//            int val = Integer.parseInt(text);
//            id = val;
//            idAsetus.setText("" + val);
//        });
        varausidAsetus.setOnAction(e ->{
            String text = varausidAsetus.getText();
            int val = Integer.parseInt(text);
            varaus_id = val;

        });
        luontipvAsetus.setOnAction(e ->{
            String text = luontipvAsetus.getText();
            Date dat = Date.valueOf(text);
            luonti_pvm = dat;
            luontipvAsetus.setText(dat.toString());

        });
        erapvmAsetus.setOnAction(e ->{
            String text = erapvmAsetus.getText();
            Date dat = Date.valueOf(text);
            era_pvm = dat;
            erapvmAsetus.setText(dat.toString());
        });
        ytunnusAsetus.setOnAction(e ->{
            String text = ytunnusAsetus.getText();
            y_tunnus = text;

        });
        viiteAsetus.setOnAction(e ->{
            String text = viiteAsetus.getText();
            viitenumero = text;
        });
        hintaAsetus.setOnAction(e ->{
            String text = hintaAsetus.getText();
            double val = Double.parseDouble(text);
            hinta = val;
            hintaAsetus.setText("" + val);

        });
        maksettuAsetus.setOnAction(e ->{
            String text = maksettuAsetus.getText();
            boolean val = Boolean.parseBoolean(text);
            maksettu = val;

        });

        lisaaLasku.setOnAction(e ->{
            try {
                int varausIdVal= getIntValue(varausidAsetus.getText());
                Date luontipvVal = getDateValue(luontipvAsetus.getText());
                Date erapvmVal = getDateValue(erapvmAsetus.getText());
                String ytunnusVal = ytunnusAsetus.getText();
                String viiteVal = viiteAsetus.getText();
                double hintaVal = getDoubleValue(hintaAsetus.getText());
                boolean maksettuVal=getBooleanValue(maksettuAsetus.getText());


                if (varausIdVal != -1 && hintaVal >= 0) {
                    LaskuKomennot.createLasku(varausIdVal,luontipvVal,erapvmVal,ytunnusVal,viiteVal,hintaVal,maksettuVal);
                    updateTextArea();
                    clearFields();
                } else {
                    showError("Tarkista syötetyt tiedot!");
                }
            } catch (Exception ex) {
                showError("Virhe varauksen lisäämisessä: " + ex.getMessage());
            }


        });


        muokkaaLaskua.setOnAction(e -> {
            try {
                int varausIdVal= getIntValue(varausidAsetus.getText());
                Date luontipvVal = getDateValue(luontipvAsetus.getText());
                Date erapvmVal = getDateValue(erapvmAsetus.getText());
                String ytunnusVal = ytunnusAsetus.getText();
                String viiteVal = viiteAsetus.getText();
                double hintaVal = getDoubleValue(hintaAsetus.getText());
                boolean maksettuVal=getBooleanValue(maksettuAsetus.getText());


                if (varausIdVal != -1) {
                    LaskuKomennot.updateLasku(varausIdVal,luontipvVal,erapvmVal,ytunnusVal,viiteVal,hintaVal,maksettuVal);
                    updateTextArea();
                    clearFields();
                } else {
                    showError("Varaus ID ei kelpaa");
                }
            } catch (Exception ex) {
                showError("Virhe laskun muokkauksessa: " + ex.getMessage());
            }
        });

        poistaLasku.setOnAction(e -> {
            try {
                String selected = laskuList.getSelectionModel().getSelectedItem();
                if (selected == null) {
                    showError("Valitse poistettava lasku listasta");
                    return;
                }

                int id = Integer.parseInt(selected.split(",")[0].trim());
                kom.poistaLasku(id);
                updateTextArea();
            } catch (Exception ex) {
                showError("Virhe laskun poistossa: " + ex.getMessage());
            }
        });
    }

    private void clearFields() {
        idAsetus.clear();
        varausidAsetus.clear();
        luontipvAsetus.clear();
        erapvmAsetus.clear();
        ytunnusAsetus.clear();
        viiteAsetus.clear();
        hintaAsetus.clear();
        maksettuAsetus.clear();

    }
    private void showError(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Virhe");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public HBox getLayout() {

        lisaaLasku= new Button("Lisaa lasku");
        muokkaaLaskua = new Button("Muokkaa laskua");
        poistaLasku= new Button("Poista lasku");
        HBox topLayout = new HBox(1, lisaaLasku, muokkaaLaskua, poistaLasku);



//        Label idLabel = new Label("ID: ");
//        idAsetus =new TextField();

        Label varausidLabel = new Label("Varaus id: ");
        varausidAsetus =new TextField();

        Label luontipvLabel = new Label("Luonti päivä: ");
        luontipvAsetus =new TextField();

        Label erapvmLabel = new Label("Erä päivä: ");
        erapvmAsetus=new TextField();


        Label ytunnusLabel = new Label("Yritys tunnus: ");
        ytunnusAsetus=new TextField();


        Label viiteLabel = new Label("Viite numero: ");
        viiteAsetus=new TextField();


        Label hintaLable = new Label("Hinta: ");
        hintaAsetus=new TextField();

        Label maksettuLAble = new Label("Maksettu: ");
        maksettuAsetus=new TextField();






        VBox rightSide = new VBox(1,
                topLayout,
//                idLabel, idAsetus,
                varausidLabel, varausidAsetus,
                luontipvLabel, luontipvAsetus,
                ytunnusLabel, ytunnusAsetus,
                erapvmLabel, erapvmAsetus,
                viiteLabel, viiteAsetus,
                hintaLable, hintaAsetus,
                maksettuLAble, maksettuAsetus);

        rightSide.setPrefWidth(500);



        VBox leftSide = new VBox(10, laskuList);
        leftSide.setPrefWidth(600);

        HBox mainLayout = new HBox(20, rightSide, leftSide);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setPrefSize(1920, 1080);

        textArea();
        updateTextArea();
        return  mainLayout;
    }
}
