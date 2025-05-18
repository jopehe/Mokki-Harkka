package com.example.java_project;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.DriverManager;
import java.util.Properties;



/**
 * Main scripti sisältää ohjelman aloitukseen tarvittavat järjestelmät
 */
public class Main extends Application {
    private ResultSet rs;
    private BorderPane rootLayout;


    UserHallinta user;
    MokkiHallinta mokkit;
    AsiakasHallinta asiakas ;
    VarausHallinta varaus;
    LaskuHallinta lasku ;
    RaporttiHallinta raportit;

    boolean login;
    boolean admin;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        CreateMYSQL.createDatabase();


        login = false;
        admin = true;


        user = new UserHallinta();
        mokkit = new MokkiHallinta();
        asiakas = new AsiakasHallinta();
        varaus = new VarausHallinta();
        lasku = new LaskuHallinta();
        raportit = new RaporttiHallinta();


        rootLayout = new BorderPane();

        Button asiakatB = new Button("Asiakas");
        Button mokkiB = new Button("Mökki");
        Button varausB = new Button("Varaus");
        Button laskuB = new Button("Lasku");
        Button raportiB = new Button("Raportointi");
        Button kauttajatB = new Button("Käyttäjät");
        Button logOut = new Button("LOGOUT");


        asiakatB.setOnAction(e ->{
            rootLayout.setCenter(getAsiakasLayout()
            );
        });

        mokkiB.setOnAction(e ->{
            rootLayout.setCenter(getMokkiLayout()
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

        kauttajatB.setOnAction(e ->{
            rootLayout.setCenter(getKauttajatLayot());

        });

        logOut.setOnAction(e ->{
            login = false;
            System.out.println("LOG OUT");
            rootLayout.setCenter(null);
            rootLayout.setTop(null);
            rootLayout.setCenter(user.getStartLayout());
        });


        user.loginButton.setOnAction(e ->{
            if(user.kom.findUser(user.getUsername(), user.getPassword())){
                System.out.println("USER FOUND:  " + user.userNameCur + ", " + user.passwordCur);
                login = true;
                HBox tobBar;
                if(admin){
                    tobBar = new HBox(2, asiakatB, mokkiB, varausB, laskuB, raportiB, kauttajatB, logOut);
                }
                else{
                    tobBar = new HBox(2, asiakatB, mokkiB, varausB, laskuB, raportiB, logOut);
                }

                tobBar.setPadding(new Insets(5));
                rootLayout.setTop(tobBar);
                rootLayout.setCenter(null);
            }
            else{
                System.out.println("USER NOT FOUND: " + user.userNameCur + ", " + user.passwordCur);
            }
        });

        HBox tobBar = new HBox(2, asiakatB, mokkiB, varausB, laskuB, raportiB, logOut);
        logOut.setAlignment(Pos.TOP_RIGHT);
        tobBar.setPadding(new Insets(5));
        rootLayout.setTop(tobBar);



        if(!login){
            System.out.println("SET LOG IN");
            rootLayout = new BorderPane();
            rootLayout.setCenter(user.getStartLayout());
        }


        Scene scene = new Scene(rootLayout, 800, 800);
        stage.setMinWidth(600);
        stage.setMaxHeight(800);
        stage.setMaxWidth(1000);
        stage.setMaxHeight(1200);

        stage.setTitle("Mokki varaus ohjelma");
        stage.setScene(scene);
        stage.show();
    }


    /**
     * palauttaa asiakas näkymän
     * @return
     */
    public HBox getAsiakasLayout(){
        HBox layout = new HBox();
        layout.getChildren().add(asiakas.getLayout());
        return layout;
    }

    /**
     * palauttaa mökit näkymän
     * @return
     */
    public Pane getMokkiLayout(){
        VBox layout = new VBox();
        layout.getChildren().add(mokkit.getLayout());
        return layout;
    }

    /**
     * Palauttaa varaus näkymän
     * @return
     */
    public Pane getVarausLayout(){
        VBox layout = new VBox();
        layout.getChildren().add(varaus.getLayout());
        return layout;
    }

    /**
     * Palauttaa lasku näkymän
     * @return
     */
    public Pane getLaskuLayout(){
        VBox layout = new VBox();
        layout.getChildren().add(lasku.getLayout());
        return layout;
    }

    /**
     * Palautta raportointi näkymän
     * @return
     */
    public Pane getRaportiLayout(){
        VBox layout = new VBox();
        layout.getChildren().add(raportit.getLayout());
        return layout;
    }


    public Pane getKauttajatLayot(){
        VBox layout = new VBox();
        layout.getChildren().add(user.getLayout());
        return layout;
    }


    /**
     * Testi methodi jonka avulla yhteys tietokantaan tehdään
     */
    public void haeDataaTEST(){

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

}