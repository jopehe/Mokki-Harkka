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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class VarausHallinta extends Application {


    private Button lisaaVaraus;
    private Button muokkaaVarausta;
    private Button poistaVaraus;

    private Button tallennaButton;

    private TextField IdMAsetus;
    private TextField IdAsetus;
    private TextField hintaAsetus;
    private TextField aloitusAsetus;
    private TextField lopetusAsetus;
    private TextField luontiAsetus;

    ListView<String> varausListView;
    List<Varaus> varausList;

    private int varausId ;
    private int mokkiId;
    private int asiakasId;
    private double hinta;
    private Date vAloitus;
    private Date vLopetus;
    private Date luontiP;

    VarausKomennot kom;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(getLayout());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Varausten Hallinta");
        primaryStage.show();

    }

    public VarausHallinta(){
        varausListView = new ListView<>();
        varausList = new ArrayList<>();
        kom = new VarausKomennot();
    }



    /**
     * Methodi kääntää string arvon int arvoksi
     * @param anser on string arvo josta käänetään int arvo
     * @return palauttaa -1 jos kääntö is ole mahdollista, muulloin palauttaa tulkitun arvon
     */
    private int getIntValue(String anser){
        try{
            int result = Integer.parseInt(anser);
            return result;
        } catch (Exception E){
            System.out.println("Error: Int value could not be translated from, ' " + anser + " ' string. " + E);
        }
        return -1;
    }

    /**
     * Methodi käänjtä string arvon double arvoksi
     * @param anser on string arvo josta käänteään double arvo
     * @return palauttaa -1.0 jos kääntö ei ole mohdollista, muulloin palautetaan tulkitun arvon.
     */
    private double getDoubleValue(String anser){
        try{
            double result = Double.parseDouble(anser);
            return result;

        } catch (Exception E){
            System.out.println("Error: Double value could not be translated from, ' " + anser + " ' string.");
        }
        return -1.0;
    }

    /**
     * Methodi kääntää string arvon boolean arvoksi
     * @param anser on string arvo josta käänetään boolean arvo
     * @return palauttaa false jos kääntö ei ole mahdollista, mulloin palauttaa tulkitun arvon.
     */
    private boolean getBooleanValue(String anser){
        try {
            Boolean result = Boolean.getBoolean(anser);
            return result;
        } catch (Exception E){
            System.out.println("Error: Boolean value could not be translated from, ' " + anser + " ' string.");
        }
        return false;
    }


    private Date getDateValue(String anser){
        try {
            Date result = Date.valueOf(anser);
            return result;
        } catch (Exception E){
            System.out.println("Error: Date value could not be translated from, ' " + anser + " ' string.");
        }
        return Date.valueOf("0000-0-0");
    }





    /**
     * Palauttaa kaikki mokkit tauluun tallenetut mökki tiedot
     */
    public void updateTextArea(){
        System.out.println("SAVED");
        varausListView.getItems().clear();
        varausList = kom.getVaraukset();

        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < varausList.size(); i++){
            stringList.add(varausList.get(i).toString());
        }
        ObservableList<String> oblist = FXCollections.observableArrayList(stringList);
        varausListView.setItems(oblist);
    }



    /**
     * ON alue jossa mökien eri tekstialueta voidaan muokata jotta ne voidaan muyöhemmin talentaa
     */
    private void textArea() {

        ///  Asettaa mökin osoitteen
        IdMAsetus.setOnAction(e -> {
            String text = IdMAsetus.getText();
            int val = getIntValue(text);
            varausId = val;
        });

        ///  Asettaa mökin hinnan
        IdAsetus.setOnAction(e -> {
            String text = IdAsetus.getText();
            int val = getIntValue(text);
            mokkiId = val;
            IdAsetus.setText("" + val);
        });

        ///  Asettaa mökin koon
        hintaAsetus.setOnAction(e -> {
            String text = hintaAsetus.getText();
            double val = getDoubleValue(text);
            hinta = val;
            hintaAsetus.setText("" + val);
        });

        /// Asettaa huoneen lukumäärän
        aloitusAsetus.setOnAction(e -> {
            String text = aloitusAsetus.getText();
            Date val = getDateValue(text);
            vAloitus = val;
            aloitusAsetus.setText("" + val);
        });

        ///  Asettaa keittiön tilan
        lopetusAsetus.setOnAction(e -> {
            String text = lopetusAsetus.getText();
            Date val = getDateValue(text);
            vLopetus = val;
            lopetusAsetus.setText("" + val);
        });

        ///  Asettaa kylpyhuoneen tilan
        luontiAsetus.setOnAction(e -> {
            String text = luontiAsetus.getText();
            Date val = getDateValue(text);
            luontiP = val;
            luontiAsetus.setText("" + val);
        });



        /// Aseta näkumäksi uuden mökin lisäys
        lisaaVaraus.setOnAction(e -> {
        });

        /// Aseta näkymäksi mökin muokkaus
        muokkaaVarausta.setOnAction(e -> {
        });

        /// Poista valittu mokki
        poistaVaraus.setOnAction(e -> {
        });



        /// Tallenna muutokset
        tallennaButton.setOnAction(e -> {


        });


    }


    public HBox getLayout() {

        updateTextArea();



        lisaaVaraus = new Button("Lisaa varaus");
        muokkaaVarausta = new Button("Muokkaa varaus");
        poistaVaraus = new Button("Poista varaus");
        HBox topLayout = new HBox(1, lisaaVaraus, muokkaaVarausta, poistaVaraus);



        Label idMLabel = new Label("Mokki id: ");
        IdMAsetus = new TextField("");

        Label idALabel = new Label("Asiakas id: ");
        IdAsetus = new TextField("");

        Label hintaLabel = new Label("Hinta: ");
        hintaAsetus = new TextField("");

        Label aloitusLabel = new Label("Aloitus: ");
        aloitusAsetus = new TextField("");

        Label lopetusLabel = new Label("Lopetus: ");
        lopetusAsetus = new TextField("");

        Label luontiLable = new Label("Luonti paiva: ");
        luontiAsetus = new TextField("");


        tallennaButton = new Button("Tallenna");


        VBox rightSide = new VBox(1,
                topLayout,
                idMLabel, IdMAsetus,
                idALabel, IdAsetus,
                hintaLabel, hintaAsetus,
                aloitusLabel, aloitusAsetus,
                lopetusLabel, lopetusAsetus,
                luontiLable, luontiAsetus
        );
        rightSide.setPrefWidth(500);



        VBox leftSide = new VBox(10, varausListView);
        leftSide.setPrefWidth(600);

        HBox mainLayout = new HBox(20, rightSide, leftSide);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setPrefSize(1920, 1080);

        return  mainLayout;
    }
}
