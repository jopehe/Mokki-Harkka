package com.example.java_project;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LaskuHallinta {




    public HBox getLayout() {


        Button lisaaLasku= new Button("Lisaa lasku");
        Button muokkaaLaskua = new Button("Muokkaa laskua");
        Button poistaLasku= new Button("Poista lasku");
        HBox topLayout = new HBox(1, lisaaLasku, muokkaaLaskua, poistaLasku);



        Label idLabel = new Label("ID: ");
        TextField IdAsetus = new TextField("");

        Label osoiteLabel = new Label("Osoite: ");
        TextField osoiteAsetus = new TextField("");

        Label hintaLabel = new Label("Hinta: ");
        TextField hintaAsetus = new TextField("");

        Label kokoLabel = new Label("Koko: ");
        TextField kokoAsetus = new TextField("");



        VBox rightSide = new VBox(1,
                topLayout,
                idLabel, IdAsetus,
                osoiteLabel, osoiteAsetus,
                hintaLabel, hintaAsetus,
                kokoLabel, kokoAsetus);
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
