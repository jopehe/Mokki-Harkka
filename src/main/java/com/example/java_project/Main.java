package com.example.java_project;


import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;


import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.DriverManager;
import java.util.Properties;

// alustava sopimus esitutkimuksesta
// alustava projektisuunnitelma esitutkimuksesta
// dokumentointisuunnitelma esitutkimuksesta
// mikä tahansa suunnitelma määrittelystä



public class Main extends Application {
    private ResultSet rs;


    @Override
    public void start(Stage stage) throws Exception {

        try {
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
        }




        /*
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        */
    }

    public static void main(String[] args) {
        launch();
    }
}