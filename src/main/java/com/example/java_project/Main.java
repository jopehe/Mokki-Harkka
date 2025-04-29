package com.example.java_project;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.DriverManager;
import java.util.Properties;

// alustava sopimus esitutkimuksesta
// alustava projektisuunnitelma esitutkimuksesta
// dokumentointisuunnitelma esitutkimuksesta
// mikä tahansa suunnitelma määrittelystä



public class Main extends Application {
    private ResultSet rs;


    private BorderPane rootLayout;

    @Override
    public void start(Stage stage) throws Exception {


        MokkiHallinta mokkit = new MokkiHallinta();

        rootLayout = new BorderPane();

        Button asiakatB = new Button("Asiakas");
        Button mokkiB = new Button("Mökki");
        Button varausB = new Button("Varaus");
        Button laskuB = new Button("Lasku");
        Button raportiB = new Button("Raportointi");


        asiakatB.setOnAction(e ->{
            rootLayout.setCenter(getAsiakasLayout()
            );
        });

        mokkiB.setOnAction(e ->{
            rootLayout.setCenter(mokkit.getView()
            );
        });
        varausB.setOnAction(e ->{
            rootLayout.setCenter(getVarausLayout()
            );
        });
        laskuB.setOnAction(e ->{
            rootLayout.setCenter(getLaskuLayout()
            );
        });
        raportiB.setOnAction(e ->{
            rootLayout.setCenter(getRaportiLayout()
            );
        });

        HBox tobBar = new HBox(2, asiakatB, mokkiB, varausB, laskuB, raportiB);
        tobBar.setPadding(new Insets(5));
        rootLayout.setTop(tobBar);


        Scene scene = new Scene(rootLayout, 600, 800);
        stage.setMinWidth(600);
        stage.setMaxHeight(800);
        stage.setMaxWidth(1000);
        stage.setMaxHeight(1200);


        stage.setTitle("Mokki varaus ohjelma");
        stage.setScene(scene);
        stage.show();

    }


    public void haeDataa(){

        try {

            String asiakas =   "CREATE TABLE Asiakas (asiakas_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "nimi VARCHAR(50) NOT NULL," +
                    "sahkoposti VARCHAR(50) NOT NULL," +
                    "puhelin VARCHAR(20) NOT NULL);";

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            /// Alustava kysely
            String sql = "SELECT email FROM users WHERE id < 3;";

            Properties prop = new Properties();


            //Lataa tiedot db.properties tiedostosta joka on oma kaikille käyttäjille
            try(InputStream input = new FileInputStream("db.properties")){
                prop.load(input);
            }

            /// Asetetaan eri "db.properties" tiedostoon asetetut tiedot anetuihin muutujiin
            String url = prop.getProperty("db.url");
            String username = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");


            /// Luodaan yhteys databaseen anetuilla tiedoilla
            Connection con = DriverManager.getConnection(url, username, password);
            /// Aluetetaan lysely
            Statement st = con.createStatement();
            /// Haetaan kyselyn vastaukset anetulla lauseella
            rs = st.executeQuery(sql);

            //Käydään rivehä niin pitkän kunnes kaikki haetut arviot on käyty läpi
            //Eka rivi tulee skipata koska siinäö ei ole arvoja jota haetaan taulusta
            while(rs.next()){
                String word = rs.getString(1);
                System.out.println(word);
            }

        }catch (SQLException E){
            System.out.println("Exeption: " + E);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public HBox getAsiakasLayout(){
        HBox layout = new HBox();
        layout.getChildren().add(new Button("ASIAKAS VIEW"));
        return layout;
    }

    public Pane getMokkiLayout(){
        VBox layout = new VBox();
        layout.getChildren().add(new Button("MÖKKI VIEW"));
        return layout;
    }

    public Pane getVarausLayout(){
        VBox layout = new VBox();
        layout.getChildren().add(new Button("VARAUS VIEW"));
        return layout;
    }

    public Pane getLaskuLayout(){
        VBox layout = new VBox();
        layout.getChildren().add(new Button("LASKU VIEW"));
        return layout;
    }

    public Pane getRaportiLayout(){
        VBox layout = new VBox();
        layout.getChildren().add(new Button("RAPORTTI VIEW"));
        return layout;
    }


    public static void main(String[] args) {
        launch();
    }
}