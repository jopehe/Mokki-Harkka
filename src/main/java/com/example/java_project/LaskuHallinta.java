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

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class LaskuHallinta extends Application {
    TextField idAsetus;
    TextField osoiteAsetus;
    TextField paivaAsetus;
    TextField kokoAsetus;
    TextField ytunnusAsetus;
    TextField viiteAsetus;
    TextField hintaAsetus;
    TextField maksettuAsetus;

    Button lisaaLasku;
    Button muokkaaLaskua;
    Button poistaLasku;
    Button tallennaLasku;


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


        kom.createLasku(1, 3, Date.valueOf("2025-4-15"), Date.valueOf("2025-5-15"), "TimolaTM", "000001000", 50.00, false);



        //updateTextArea();

        Scene scene = new Scene(getLayout());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Lakujen Hallinta");
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

    public void textArea(){
        idAsetus.setOnAction(e ->{
            String text = idAsetus.getText();
            int val = Integer.parseInt(text);
            id = val;
            idAsetus.setText("" + val);
        });
        osoiteAsetus.setOnAction(e ->{
            String text = osoiteAsetus.getText();
            int val = Integer.parseInt(text);
            varaus_id = val;

        });
        paivaAsetus.setOnAction(e ->{
            String text = paivaAsetus.getText();
            Date dat = Date.valueOf(text);
            luonti_pvm = dat;
            paivaAsetus.setText(dat.toString());

        });
        kokoAsetus.setOnAction(e ->{
            String text = kokoAsetus.getText();
            Date dat = Date.valueOf(text);
            era_pvm = dat;
            kokoAsetus.setText(dat.toString());
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

        });
        muokkaaLaskua.setOnAction(e ->{

        });
        poistaLasku.setOnAction(e ->{

        });




    }

    public HBox getLayout() {

        updateTextArea();

        lisaaLasku= new Button("Lisaa lasku");
        muokkaaLaskua = new Button("Muokkaa laskua");
        poistaLasku= new Button("Poista lasku");
        HBox topLayout = new HBox(1, lisaaLasku, muokkaaLaskua, poistaLasku);



        Label idLabel = new Label("ID: ");
        TextField IdAsetus = new TextField("");

        Label osoiteLabel = new Label("Varaus id: ");
        TextField osoiteAsetus = new TextField("");

        Label paivaLabel = new Label("Luonti päivä: ");
        TextField paivaAsetus = new TextField("");

        Label kokoLabel = new Label("Erä päivä: ");
        TextField kokoAsetus = new TextField("");


        Label ytunnus = new Label("Yritys tunnus: ");
        TextField ytunnusAsetus = new TextField("");


        Label viiteLabel = new Label("Viite numero: ");
        TextField viiteAsetus = new TextField("");


        Label hintaLable = new Label("Hinta: ");
        TextField hintaAsetus = new TextField("");

        Label maksettuLAble = new Label("Maksettu: ");
        TextField maksettuAsetus = new TextField("");






        VBox rightSide = new VBox(1,
                topLayout,
                idLabel, IdAsetus,
                osoiteLabel, osoiteAsetus,
                paivaLabel, paivaAsetus,
                kokoLabel, kokoAsetus,
                ytunnus, ytunnusAsetus,
                viiteLabel, viiteAsetus,
                hintaLable, hintaAsetus,
                maksettuLAble, maksettuAsetus);

        rightSide.setPrefWidth(500);



        VBox leftSide = new VBox(10, laskuList);
        leftSide.setPrefWidth(600);

        HBox mainLayout = new HBox(20, rightSide, leftSide);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setPrefSize(1920, 1080);

        return  mainLayout;
    }
}
