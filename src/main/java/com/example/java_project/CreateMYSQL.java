package com.example.java_project;

import java.sql.*;
import javafx.application.Application;
import javafx.stage.Stage;
import  java.sql.Connection;


/**
 * Scripti sisältää erillaisia toimintoja joita tietokannan luomeseen/poistamisen voidaan käyttää
 * Author: Joel Heiskanen
 * Date: 29.4.2025
 * Version: 1.0
 */
public class CreateMYSQL extends Application {
    private ResultSet rs;


    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("HELLO ");
        createDatabase();
    }


    /**
     * Poistetaan "mokki_database"
     */
    public void removeDatabase(){
        try {
            /// POISTETAAN DATABASE
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectionDB = connection.getConnection();
            String dbquery = "DROP DATABASE mokki_database";

            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(dbquery);
            connectionDB.close();
            ;
            System.out.println("Database mokki_database on poistettu!");
        }catch (Exception E){
            System.out.println("Error removing database: " + E);
        }
    }

    /**
     * Luodaan uusi "mokki_database" database sekä kaikki taulut joita siihen kuuluu
     */
    public void createDatabase(){
        try{
            /// Haetaan osoite mysql juureen johon uusi tietokanta voidaan luoda
            DatabaseConnection connection = new DatabaseConnection();
            Connection connectionDB = connection.getConnection();

            String dbquery = "CREATE DATABASE IF NOT EXISTS mokki_database";

            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(dbquery);
            connectionDB.close();;
            System.out.println("Database mokki_database on luotu!");



            /// Luodaan uudet tablet
            Connection databaseConnection = connection.getDatabaseConnection();
            Statement dbStatement = databaseConnection.createStatement();

            String createAsiakasTable = "CREATE TABLE IF NOT EXISTS asiakas ("+
                    "id INT AUTO_INCREMENT PRIMARY KEY,"+
                    "nimi VARCHAR(50) NOT NULL,"+
                    "email VARCHAR(50) NOT NULL,"+
                    "puhelinumero VARCHAR(50) NOT NULL);";

            dbStatement.executeUpdate(createAsiakasTable);
            System.out.println("Asiakas table on luotu!");

            String createMokkiTable = "CREATE  TABLE IF NOT EXISTS mokki ("+
                    "id INT AUTO_INCREMENT PRIMARY KEY,"+
                    "osoite VARCHAR(50) NOT NULL," +
                    "hinta DECIMAL(10,2) NOT NULL," +
                    "koko DECIMAL(5,2) NOT NULL," +
                    "huone_lkm INT NOT NULL," +
                    "keittio BOOLEAN NOT NULL," +
                    "kylpyhuone BOOLEAN NOT NULL);";

            dbStatement.executeUpdate(createMokkiTable);
            System.out.println("Mökki table on luotu!");


            String createVarausTable = "CREATE  TABLE IF NOT EXISTS varaus ("+
                    "id INT AUTO_INCREMENT PRIMARY KEY,"+
                    "asiakas_id INT NOT NULL,"+
                    "mokki_id INT NOT NULL,"+
                    "hinta DECIMAL(10,2) NOT NULL," +
                    "varaus_alku DATE NOT NULL," +
                    "varaus_loppu DATE NOT NULL," +
                    "luonti_paiva DATE NOT NULL,"+
                    "FOREIGN KEY (asiakas_id) REFERENCES asiakas(id)," +
                    "FOREIGN KEY (mokki_id) REFERENCES mokki(id));";

            dbStatement.executeUpdate(createVarausTable);
            System.out.println("Varaus table on luotu!");



            String createLaskuTable = "CREATE  TABLE IF NOT EXISTS lasku(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "varaus_id INT NOT NULL," +
                    "luonti_pvm DATE NOT NULL," +
                    "era_pvm DATE NOT NULL," +
                    "y_tunnus VARCHAR(20) NOT NULL," +
                    "viitenumero VARCHAR(50) NOT NULL," +
                    "hinta DECIMAL(10,2) NOT NULL," +
                    "maksettu BOOLEAN NOT NULL," +
                    "FOREIGN KEY (varaus_id) REFERENCES varaus(id));";

            dbStatement.executeUpdate(createLaskuTable);
            System.out.println("Lasku table on luotu!");




            String createUserTable = "CREATE  TABLE IF NOT EXISTS user ("+
                    "id INT AUTO_INCREMENT PRIMARY KEY,"+
                    "username VARCHAR(30) NOT NULL,"+
                    "password VARCHAR(20) NOT NULL)";
            dbStatement.executeUpdate(createUserTable);



            databaseConnection.close();

        }catch (Exception E){
            System.out.println("Error trying to create database: " + E);
        }
    }


}