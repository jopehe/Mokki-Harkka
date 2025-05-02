package com.example.java_project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MokkiHallinta extends  Application{


    private int id;
    private String osoite;
    private String kuvaus;
    private double hinta;
    private double koko;
    private int huoneLK;
    private boolean keittio;
    private boolean kylpyhuone;



    Label mokkiidLab;
    Label osoiteLab;



    TextField mokkiIdAsetus;
    TextField osoiteAsetus;
    TextField hintaAsetus;
    TextField kokoAsetus;
    TextField huoneLkmAsetus;

    RadioButton keittioOn;
    RadioButton kylpyhuoneOn;

    ListView<String> mokkiLista;


    Button tallennaButton;
    Button lisaaMokki;
    Button muokkaaMokkia;
    Button poistaMokki;



    public static void main(String[] args) {
        launch(args);
    }



    //LUO KÄYTTÄJÄ
        //ANNA TIEDOT
            //TALLENA??

    //MUUTA TIETOJA
        //HAE KÄYTTÄJÄ
            //AVAA KÖYTTÄJÄ
                //MUUTA TIETOJA
                    //TALLENA TIEDOT


    //POISTA KÄYTTÄJÄ
        //HAE KÄYTTÄJÄ
            //POISTA KÄYTTÄJÄ
                //HALUATKO???



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



        List<Mokki> mokkit = new ArrayList<Mokki>();
        MokkiKomennot komennot = new MokkiKomennot();
        mokkit = komennot.getAllMokit();

        List<String> stringList = new ArrayList<>();
        for(int i = 0; i < mokkit.size(); i++){

            System.out.println(mokkit.get(i).getString());
            stringList.add(mokkit.get(i).getString());
        }

        ObservableList<String> oblist = FXCollections.observableArrayList(stringList);
        mokkiLista = new ListView<>();
        mokkiLista.setItems(oblist);





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


    private void textArea(){

        mokkiIdAsetus.setOnAction(e -> {
            String text = mokkiIdAsetus.getText();
            int idN = getIntValue(text);
            id = idN;
            mokkiIdAsetus.setText("" + idN);

            System.out.println("ID Changerd!");
        });

        osoiteAsetus.setOnAction(e -> {
            osoite = osoiteAsetus.getText();

            System.out.println("Addres Changerd!");
        });

        hintaAsetus.setOnAction(e -> {
            String text = hintaAsetus.getText();
            double val = getDoubleValue(text);
            hinta = val;
            hintaAsetus.setText("" + val);

            System.out.println("Price Changerd!");
        });

        kokoAsetus.setOnAction(e -> {
            String text = kokoAsetus.getText();
            double val = getDoubleValue(text);
            koko = val;
            kokoAsetus.setText("" + val);

            System.out.println("Size Changerd!");
        });

        huoneLkmAsetus.setOnAction(e -> {
            String text = huoneLkmAsetus.getText();
            int val = getIntValue(text);
            huoneLkmAsetus.setText("" + val);

            System.out.println("Count Changed!");
        });

        keittioOn.setOnAction(e -> {
            keittio = keittioOn.isSelected();

            System.out.println("Keittio on: " + keittio);
        });

        kylpyhuoneOn.setOnAction(e ->{
            kylpyhuone = kylpyhuoneOn.isSelected();

            System.out.println("Kylpyhuone on: " + kylpyhuone);
        });


        /// Aseta näkumäksi uuden mökin lisäys
        lisaaMokki.setOnAction(e -> {

        });
        /// Aseta näkymäksi mökin muokkaus
        muokkaaMokkia.setOnAction(e ->{

        });
        /// Poista valittu mokki
        poistaMokki.setOnAction(e -> {

        });

        /// Tallenna muutokset
        tallennaButton.setOnAction(e ->{

        });


        mokkiLista.setOnMouseClicked(e -> {
            String selected = mokkiLista.getSelectionModel().getSelectedItem();
            int selectedIndex = mokkiLista.getSelectionModel().getSelectedIndex();
            System.out.println("MOUSE CLICK: " + selected + " index: " + selectedIndex);
        });


    }


    /// Asettaa elementit joiden avulla uusi mökki voidaan luoda ja tallentaa
    private VBox setCreateNewMokki(){
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



        tallennaButton = new Button("Tallenna");


        keittioOn = new RadioButton();
        kylpyhuoneOn = new RadioButton();

        HBox radioButtons = new HBox(
                keittioLable,keittioOn,
                kylpyhuoneLable,kylpyhuoneOn);



        /// Asettaa näkymän  näytön vasemalle puolelle
        VBox left_Side = new VBox(1,
                //topLayout,
                idLabel, mokkiIdAsetus,
                osoiteLabel, osoiteAsetus,
                hintaLabel, hintaAsetus,
                kokoLabel, kokoAsetus,
                huonelkmLable, huoneLkmAsetus,
                radioButtons,
                tallennaButton);

        left_Side.setPrefWidth(500);

        return  left_Side;
    }


    private VBox setModifyMokki(){
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



        tallennaButton = new Button("Tallenna");


        keittioOn = new RadioButton();
        kylpyhuoneOn = new RadioButton();

        HBox radioButtons = new HBox(
                keittioLable,keittioOn,
                kylpyhuoneLable,kylpyhuoneOn);



        /// Asettaa näkymän  näytön vasemalle puolelle
        VBox left_Side = new VBox(1,
                //topLayout,
                idLabel, mokkiIdAsetus,
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






        VBox right_Side = new VBox(10, mokkiLista);
        right_Side.setPrefWidth(600);



        // Pääasettelu
        HBox mainLayout = new HBox(20, left_Side, right_Side);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setPrefSize(1920, 1080);


        textArea();

        return  mainLayout;
    }


}
