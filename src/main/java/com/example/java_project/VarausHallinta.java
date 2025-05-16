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
    private Button lisaaLaskuButton;

    private TextField IdMAsetus;
    private TextField IdAsetus;
    private TextField hintaAsetus;
    private TextField aloitusAsetus;
    private TextField lopetusAsetus;
    private TextField luontiAsetus;

    ListView<String> varausListView;
    List<Varaus> varausList;

    private int selectedVarausId = -1;
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
            try {
                int mokkiIdVal = getIntValue(IdMAsetus.getText());
                int asiakasIdVal = getIntValue(IdAsetus.getText());
                double hintaVal = getDoubleValue(hintaAsetus.getText());
                Date vAloitusVal = getDateValue(aloitusAsetus.getText());
                Date vLopetusVal = getDateValue(lopetusAsetus.getText());
                Date luontiVal = getDateValue(luontiAsetus.getText());

                if (asiakasIdVal != -1 && mokkiIdVal != -1 && hintaVal >= 0) {
                    // Kutsutaan addNewVaraus oikein
                    VarausKomennot.addNewVaraus(asiakasIdVal, mokkiIdVal, hintaVal, vAloitusVal, vLopetusVal, luontiVal);
                    updateTextArea();
                    clearFields();
                } else {
                    showError("Tarkista syötetyt tiedot!");
                }
            } catch (Exception ex) {
                showError("Virhe varauksen lisäämisessä: " + ex.getMessage());
            }
        });

        /// Aseta näkymäksi mökin muokkaus
        muokkaaVarausta.setOnAction(e -> {try {
            int mokkiIdVal = getIntValue(IdMAsetus.getText());
            int asiakasIdVal = getIntValue(IdAsetus.getText());
            double hintaVal = getDoubleValue(hintaAsetus.getText());
            Date vAloitusVal = getDateValue(aloitusAsetus.getText());
            Date vLopetusVal = getDateValue(lopetusAsetus.getText());
            Date luontiVal = getDateValue(luontiAsetus.getText());

            if(asiakasIdVal != -1){
                VarausKomennot.muokkaaVarausta( asiakasIdVal, mokkiIdVal, hintaVal, vAloitusVal, vLopetusVal, luontiVal);
                updateTextArea();
                clearFields();
            } else {
                showError("Varaus ID ei kelpaa");
            }
        } catch (Exception ex) {
            showError("Virhe varauksen muokkauksessa: " + ex.getMessage());
        }
        });

        /// Poista valittu mokki
        poistaVaraus.setOnAction(e -> {
            try {
                String selected = varausListView.getSelectionModel().getSelectedItem();
                if(selected == null){
                    showError("Valitse poistettava varaus listasta");
                }
                // Oletetaan, että listan ensimmäinen kenttä on varausId, erotettu pilkulla
                int id = Integer.parseInt(selected.split(",")[0].trim());
                VarausKomennot.poistaVaraus(id);
                updateTextArea();
                clearFields();
            } catch (Exception ex) {
                showError("Virhe varauksen poistossa: " + ex.getMessage());
            }
        });



        lisaaLaskuButton.setOnAction(e -> {
            try {
                String selected = varausListView.getSelectionModel().getSelectedItem();
                if (selected == null) {
                    showError("Valitse varaus listasta, jolle haluat lisätä laskun");
                    return;
                }

                int varausIdVal = Integer.parseInt(selected.split(",")[0].trim());

                // Luo java.sql.Date objekti nykyhetkestä
                java.sql.Date luontiPvm = new java.sql.Date(System.currentTimeMillis());
                // 14 päivää myöhemmin
                java.sql.Date erapaiva = new java.sql.Date(System.currentTimeMillis() + 14L * 24 * 60 * 60 * 1000);

                String ytunnus = "1234567-8";
                String viitenumero = "1001";

                Varaus valittuVaraus = varausList.stream()
                        .filter(v -> v.getVarausId() == varausIdVal)
                        .findFirst()
                        .orElse(null);

                if (valittuVaraus == null) {
                    showError("Varausta ei löytynyt");
                    return;
                }

                double hinta = valittuVaraus.getHinta();

                if (VarausKomennot.checkLaskuOnTehty(varausIdVal)) {
                    showError("Lasku on jo tehty tälle varaukselle.");
                    return;
                }

                kom.createLasku(varausIdVal, luontiPvm, erapaiva, ytunnus, viitenumero, hinta, false);

                showError("Lasku lisätty onnistuneesti");
            } catch (Exception ex) {
                showError("Virhe laskun lisäämisessä: " + ex.getMessage());
            }
        });


    }

    private void clearFields() {
        IdMAsetus.clear();
        IdAsetus.clear();
        hintaAsetus.clear();
        aloitusAsetus.clear();
        lopetusAsetus.clear();
        luontiAsetus.clear();
    }

    private void showError(String message) {
        // Käyttää JavaFX:n Alert-ikkunaa virheen näyttämiseen
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Virhe");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public HBox getLayout() {

        lisaaVaraus = new Button("Lisaa varaus");
        muokkaaVarausta = new Button("Muokkaa varaus");
        poistaVaraus = new Button("Poista varaus");
        lisaaLaskuButton = new Button("Lisää lasku");

        HBox topLayout = new HBox(10,lisaaVaraus, muokkaaVarausta, poistaVaraus, lisaaLaskuButton);



        Label idMLabel = new Label("Mokki id: ");
        IdMAsetus = new TextField();

        Label idALabel = new Label("Asiakas id: ");
        IdAsetus = new TextField();

        Label hintaLabel = new Label("Hinta: ");
        hintaAsetus = new TextField();

        Label aloitusLabel = new Label("Aloitus: ");
        aloitusAsetus = new TextField();

        Label lopetusLabel = new Label("Lopetus: ");
        lopetusAsetus = new TextField();

        Label luontiLable = new Label("Luonti paiva: ");
        luontiAsetus = new TextField();




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

        textArea();
        updateTextArea();
        return  mainLayout;

    }
}
