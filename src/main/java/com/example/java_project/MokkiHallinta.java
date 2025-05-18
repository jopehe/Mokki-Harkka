package com.example.java_project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * VOI:
 * (X) LISÄTÄ MÖKIN
 * (X) POISTAA MÖKIN
 * (?) MUUTTA MÖKKIÄ
 */
public class MokkiHallinta extends  Application{


    private int id;
    private String osoite;
    private double hinta;
    private double koko;
    private int huoneLK;
    private boolean keittio;
    private boolean kylpyhuone;

    MokkiKomennot komennot = new MokkiKomennot();

    enum level {
            ADDMOKKI,
            MODIFYMOKKI,
            REMOVEMOKKI
    }

    level curlevel;


    int selectedIndex = -1;

    /// Textialueet mötti tietojen asetuksille
    //TextField mokkiIdAsetus;
    TextField osoiteAsetus;
    TextField hintaAsetus;
    TextField kokoAsetus;
    TextField huoneLkmAsetus;

    /// Painikkeet valittaville tiedoille
    RadioButton keittioOn;
    RadioButton kylpyhuoneOn;

    ListView<String> mokkiListView;


    Button tallennaButton;
    Button lisaaMokki;
    Button muokkaaMokkia;
    Button poistaMokki;


    private Mokki selectedMokki;

    private List<Mokki> mokkiList = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    MokkiHallinta(){
        osoiteAsetus = new TextField("");
        hintaAsetus = new TextField("");
        kokoAsetus = new TextField("");
        huoneLkmAsetus = new TextField("");
        mokkiListView = new ListView<>();

        curlevel = level.ADDMOKKI;
    }


    /**
     * Asettaa valikon perus tilaan jotta uusi mökki voidaan luoda
     */
    private void clearSelection(){
        osoiteAsetus.setText("");
        hintaAsetus.setText("");
        kokoAsetus.setText("");
        huoneLkmAsetus.setText("");

        keittioOn.setSelected(false);
        kylpyhuoneOn.setSelected(false);

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


    @Override
    public void start(Stage primaryStage) {
        curlevel = level.ADDMOKKI;

        updateTextArea();
        Scene scene = new Scene(getLayout());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Käyttäjähallinta");
        primaryStage.show();


        /*
        // Vasemman puolen komponentit (lomake)
        Label nameLabel = new Label("Nimi:");
        TextField nameField = new TextField();
        Button addButton = new Button("Lisää käyttäjä");
        Button emptyButton = new Button("Tyhjennä");

        //VBox formBox = new VBox(10, nameLabel, nameField, addButton, emptyButton);
        //formBox.setPrefWidth(200);



        Label idLabel = new Label("ID: ");
        mokkiIdAsetus = new TextField("");

        Label osoiteLabel = new Label("Osoite: ");
        osoiteAsetus = new TextField("");

        Label hintaLabel = new Label("Mökin hinta: ");
        hintaAsetus = new TextField("");

        Label kokoLabel = new Label("Mökin koko: ");
        kokoAsetus = new TextField("");

        Label huonelkmLable = new Label("Huoneiden lkm: ");
        huoneLkmAsetus = new TextField("");

        Label keittioLable = new Label("Keittio on: ");
        Label kylpyhuoneLable = new Label("Kylpyhuone on: ");


        Button tallennaButton = new Button("Tallenna");




        Button lisaaMokki = new Button("Lisaa mökki");
        Button muokkaaMokkia = new Button("Muokkaa mökkiä");
        Button poistaMokki = new Button("Poista mökki");



        HBox topLayout = new HBox(1, lisaaMokki, muokkaaMokkia, poistaMokki);






        keittioOn = new RadioButton();
        kylpyhuoneOn = new RadioButton();

        //
        VBox rightSide = new VBox(1,
                topLayout,
                idLabel, mokkiIdAsetus,
                osoiteLabel, osoiteAsetus,
                hintaLabel, hintaAsetus,
                kokoLabel, kokoAsetus,
                huonelkmLable, huoneLkmAsetus,
                keittioLable,keittioOn,
                kylpyhuoneLable,kylpyhuoneOn,
                tallennaButton);

        rightSide.setPrefWidth(500);



        ListView<String> mokkiList = new ListView<>();
        VBox leftSide = new VBox(10, mokkiList);
        leftSide.setPrefWidth(600);






        tallennaButton.setOnAction(e ->{

            String osoiteVal = osoiteAsetus.getText();
            String hintaVal = hintaAsetus.getText();
            String kokoVal = kokoAsetus.getText();
            String huoneLkVal = huoneLkmAsetus.getText();

            boolean onKylpyhuoneValittu = kylpyhuoneOn.isSelected();
            boolean onKeittioValittu = keittioOn.isSelected();


        });



        // Oikean puolen komponentit (käyttäjälista)
        //ListView<String> userList = new ListView<>();
       // userList.setPrefWidth(200);


        /*
        // Lisääminen listaan napista
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            if (!name.isEmpty()) {
                userList.getItems().add(name);
                nameField.clear();
            }
        });

        emptyButton.setOnAction(e -> {
             userList.getItems().clear();
        }); ///

        // Pääasettelu
        HBox mainLayout = new HBox(20, rightSide, leftSide);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setPrefSize(1920, 1080);


        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Käyttäjähallinta");
        primaryStage.show();
        */
    }


    /**
     * Palauttaa kaikki mokkit tauluun tallenetut mökki tiedot
     */
    public void updateTextArea(){
        System.out.println("SAVED");
        mokkiListView.getItems().clear();
        mokkiList = komennot.getAllMokit();

        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < mokkiList.size(); i++){
            stringList.add(mokkiList.get(i).getString());
        }
        ObservableList<String> oblist = FXCollections.observableArrayList(stringList);
        mokkiListView.setItems(oblist);
    }

    /**
     * ON alue jossa mökien eri tekstialueta voidaan muokata jotta ne voidaan muyöhemmin talentaa
     */
    private void textArea(){

        ///  Asettaa mökin osoitteen
        osoiteAsetus.setOnAction(e -> {
            osoite = osoiteAsetus.getText();
            System.out.println("Addres Changerd!");
        });

        ///  Asettaa mökin hinnan
        hintaAsetus.setOnAction(e -> {
            String text = hintaAsetus.getText();
            double val = getDoubleValue(text);
            hinta = val;
            hintaAsetus.setText("" + val);
            System.out.println("Price Changerd!");
        });

        ///  Asettaa mökin koon
        kokoAsetus.setOnAction(e -> {
            String text = kokoAsetus.getText();
            double val = getDoubleValue(text);
            koko = val;
            kokoAsetus.setText("" + val);

            System.out.println("Size Changerd!");
        });

        /// Asettaa huoneen lukumäärän
        huoneLkmAsetus.setOnAction(e -> {
            String text = huoneLkmAsetus.getText();
            int val = getIntValue(text);
            huoneLK = val;
            huoneLkmAsetus.setText("" + val);

            System.out.println("Count Changed!");
        });

        ///  Asettaa keittiön tilan
        keittioOn.setOnAction(e -> {
            keittio = keittioOn.isSelected();

            System.out.println("Keittio on: " + keittio);
        });

        ///  Asettaa kylpyhuoneen tilan
        kylpyhuoneOn.setOnAction(e ->{
            kylpyhuone = kylpyhuoneOn.isSelected();

            System.out.println("Kylpyhuone on: " + kylpyhuone);
        });

        /// Aseta näkumäksi uuden mökin lisäys
        lisaaMokki.setOnAction(e -> {
            tallennaButton.setText("TALLENNA");
            curlevel = level.ADDMOKKI;
        });

        /// Aseta näkymäksi mökin muokkaus
        muokkaaMokkia.setOnAction(e ->{
            curlevel = level.MODIFYMOKKI;
            tallennaButton.setText("MUOKKAA");
        });

        /// Poista valittu mokki
        poistaMokki.setOnAction(e -> {
            curlevel = level.REMOVEMOKKI;
            tallennaButton.setText("POISTA");
            System.out.println("INDEX: " + selectedIndex);
        });

        /// Tallenna muutokset
        tallennaButton.setOnAction(e ->{
            Mokki cur;
            int index;

            switch (curlevel){
                case ADDMOKKI:
                    if(checkOkValues()){
                        komennot.addNewMokki(osoite, hinta, koko, huoneLK, keittio, kylpyhuone);
                        updateTextArea();
                        clearSelection();
                        System.out.println("Saved data values! ");
                    }
                    else{
                        System.out.println("Not ok values! ");
                    }
                    break;
                case  MODIFYMOKKI:
                    System.out.println("TALLENETAAN MUUTOKSIA");

                    cur =  komennot.getSingleMokki(mokkiList.get(selectedIndex).getId());
                    index = cur.getId();

                    if(index != -1){
                        komennot.updateMokki(index, osoite, hinta, koko, huoneLK, keittio, kylpyhuone);
                        updateTextArea();
                        selectedIndex = -1;
                    }
                    break;
                case REMOVEMOKKI:
                    cur =  komennot.getSingleMokki(mokkiList.get(selectedIndex).getId());
                    index = cur.getId();

                    if(varmitusVastaus("MÖKKI_1") && selectedIndex != -1){
                        komennot.removeMokki(index);
                        updateTextArea();
                        selectedIndex = -1;

                    }
                    break;
            }
        });

        ///  Kun yhtä mökkilistan osista kosketaan
        mokkiListView.setOnMouseClicked(e -> {
            String selected = mokkiListView.getSelectionModel().getSelectedItem();
            selectedIndex = mokkiListView.getSelectionModel().getSelectedIndex();


            System.out.println("INDEX: " + selectedIndex);

            Mokki cur =  komennot.getSingleMokki(mokkiList.get(selectedIndex).getId());

            System.out.println("DATA GOTTEN: " + cur.getString());

            id = cur.getId();
            osoite = cur.getOsoite();
            koko = cur.getKoko();
            hinta = cur.getHinta();
            huoneLK = cur.getHuoneLK();
            keittio = cur.getKeittio();
            kylpyhuone = cur.getKeittio();


            osoiteAsetus.setText(osoite);
            hintaAsetus.setText("" + hinta);
            kokoAsetus.setText("" + koko);
            huoneLkmAsetus.setText("" + huoneLK);

            keittioOn.setSelected(keittio);
            kylpyhuoneOn.setSelected(kylpyhuone);
        });

    }


    /**
     * Luo varoituksen ennen kuin mökin poisto toteutetaan
     * @param text an teksti joka kirjoitetaan viestiin kertoen mikä mökki poistetaan
     * @return palauttaa bolean arvon siittä painettiinko ok vai cancel
     */
    private boolean varmitusVastaus(String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Varmistus");
        alert.setHeaderText(null);
        alert.setContentText("Haluatko poistaa mökin: " + text);
        alert.initModality(Modality.APPLICATION_MODAL);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }


    /**
     * Tarkistetaan onko huoneelle asetetut arvot sopivia
     * @return palautetaan totta josa arvot sopivat
     */
    private boolean checkOkValues(){
        boolean ok = true;
        if(osoite == null || osoite.trim().equals("")){ ok = false;  System.out.println("OSOITE"); }
        if(hinta == 0.0) { ok = false; System.out.println("HINTA"); }
        if(koko == 0.0) { ok = false; System.out.println("KOKO"); }
        if(huoneLK == 0) { ok = false; System.out.println("HUONELK"); }
        return ok;
    }


    /// Asettaa elementit joiden avulla uusi mökki voidaan luoda ja tallentaa
    private VBox setCreateNewMokki(){

        Label osoiteLabel = new Label("Osoite: ");
        osoiteAsetus = new TextField("");

        Label hintaLabel = new Label("Mökin hinta: ");
        hintaAsetus = new TextField("");

        Label kokoLabel = new Label("Mökin koko: ");
        kokoAsetus = new TextField("");

        Label huonelkmLable = new Label("Huoneiden lkm: ");
        huoneLkmAsetus = new TextField("");

        Label keittioLable = new Label("Keittio on: ");
        Label kylpyhuoneLable = new Label("Kylpyhuone on: ");



        tallennaButton = new Button("Tallenna");

        keittioOn = new RadioButton();
        kylpyhuoneOn = new RadioButton();



        HBox radioButtons = new HBox(
                keittioLable,keittioOn,
                kylpyhuoneLable,kylpyhuoneOn);

        /// Asettaa näkymän  näytön vasemalle puolelle
        VBox left_Side = new VBox(10,
                osoiteLabel, osoiteAsetus,
                hintaLabel, hintaAsetus,
                kokoLabel, kokoAsetus,
                huonelkmLable, huoneLkmAsetus,
                radioButtons,
                tallennaButton);

        left_Side.setPrefWidth(300);

        return  left_Side;
    }


    /**
     * Asettaa asetus näkymän jossa uusi mökki lisätään tietokantaan
     * @return
     */
    private VBox setModifyMokki(){
        //Label idLabel = new Label("ID: ");
        //mokkiIdAsetus = new TextField("");

        Label osoiteLabel = new Label("Osoite: ");
        osoiteAsetus = new TextField("");

        Label hintaLabel = new Label("Mökin hinta: ");
        hintaAsetus = new TextField("");

        Label kokoLabel = new Label("Mökin koko: ");
        kokoAsetus = new TextField("");

        Label huonelkmLable = new Label("Huoneiden lkm: ");
        huoneLkmAsetus = new TextField("");

        Label keittioLable = new Label("Keittio on: ");
        Label kylpyhuoneLable = new Label("Kylpyhuone on: ");

        tallennaButton = new Button("Tallenna");

        keittioOn = new RadioButton();
        kylpyhuoneOn = new RadioButton();

        HBox radioButtons = new HBox(
                keittioLable,keittioOn,
                kylpyhuoneLable,kylpyhuoneOn);

        /// Asettaa näkymän  näytön vasemalle puolelle
        VBox left_Side = new VBox(1,
                //topLayout,
                //idLabel, mokkiIdAsetus,
                osoiteLabel, osoiteAsetus,
                hintaLabel, hintaAsetus,
                kokoLabel, kokoAsetus,
                huonelkmLable, huoneLkmAsetus,
                radioButtons,
                tallennaButton);

        left_Side.setPrefWidth(500);

        return  left_Side;
    }

    public HBox getLayout(){

        lisaaMokki = new Button("Lisaa mökki");
        muokkaaMokkia = new Button("Muokkaa mökkiä");
        poistaMokki = new Button("Poista mökki");

        HBox topLayout = new HBox(1, lisaaMokki, muokkaaMokkia, poistaMokki);
        VBox left_Side = new VBox();

        left_Side.getChildren().addAll(topLayout, setCreateNewMokki());
        left_Side.setPrefWidth(500);

        VBox right_Side = new VBox(10, mokkiListView);
        right_Side.setPrefWidth(600);

        // Pääasettelu
        HBox mainLayout = new HBox(20, left_Side, right_Side);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setPrefSize(1920, 1080);

        textArea();
        updateTextArea();
        return  mainLayout;
    }
}
