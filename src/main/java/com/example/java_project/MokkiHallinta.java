package com.example.java_project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class MokkiHallinta extends  Application{


    private int mokki;
    private String osoite;
    private String kuvaus;
    private String hinta;
    private String koko;
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


    /**
     * Hakee tietokannasta kaikki mokit
     */
    public void getMokkit(){

    }

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





    public void SetView(Stage primarystage ){




        HBox layout = new HBox();


    }

    @Override
    public void start(Stage primaryStage) {
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


        keittioOn = new RadioButton();
        kylpyhuoneOn = new RadioButton();

        //
        VBox rightSide = new VBox(1,
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
        });*/

        // Pääasettelu
        HBox mainLayout = new HBox(20, rightSide, leftSide);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setPrefSize(1920, 1080);


        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Käyttäjähallinta");
        primaryStage.show();
    }

    public HBox getView(){
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


        keittioOn = new RadioButton();
        kylpyhuoneOn = new RadioButton();

        HBox radioButtons = new HBox(
                keittioLable,keittioOn,
                kylpyhuoneLable,kylpyhuoneOn);


        //
        VBox rightSide = new VBox(1,
                idLabel, mokkiIdAsetus,
                osoiteLabel, osoiteAsetus,
                hintaLabel, hintaAsetus,
                kokoLabel, kokoAsetus,
                huonelkmLable, huoneLkmAsetus,
                radioButtons,
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
        });*/

        // Pääasettelu
        HBox mainLayout = new HBox(20, rightSide, leftSide);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setPrefSize(1920, 1080);

        return  mainLayout;
    }


}
