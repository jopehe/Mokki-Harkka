package com.example.java_project;


import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Scriptin avulla voidaan luoda yhteys tietokantaan
 * Author: Joel Heiskanen
 * Date: 29.4.2025
 * Version: 1.0
 */
public class DatabaseConnection {
    public Connection dataBaseLink;

    /**
     * Haetaan yhteys "mokki_database" tietokantan jotta muutoksia voidaan tehdä sen tauluihin
     * @return palauttaa osoitteen mokki_database tietokantaan
     */
    public Connection getDatabaseConnection(){
        Properties prop = new Properties();

        //Lataa tiedot db.properties tiedostosta joka on oma kaikille käyttäjille
        try(InputStream input = new FileInputStream("db.properties")){
            prop.load(input);
        }
        catch (Exception E){
            System.out.println("E: " + E);
        }
        /// Asettaa tiedot db.properties tiedostosta
        String databaseUser = prop.getProperty("db.username");
        String databasePassword = prop.getProperty("db.password");
        String url = prop.getProperty("db.url") + "/" + "mokki_database";


        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dataBaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (Exception E){
            System.out.println("Exeption: " + E);
        }
        return dataBaseLink;
    }


    /**
     * Hakee yhteyden mysql kantaan jotta uusi tietokanta voidaaan luoda
     * @return palautettu tietokannan yhteys
     */
    public Connection getConnection(){
        Properties prop = new Properties();

        //Lataa tiedot db.properties tiedostosta joka on oma kaikille käyttäjille
        try(InputStream input = new FileInputStream("db.properties")){
            prop.load(input);
        }
        catch (Exception E){
            System.out.println("E: " + E);
        }


        /// Asettaa tiedot db.properties tiedostosta
        String databaseUser = prop.getProperty("db.username");
        String databasePassword = prop.getProperty("db.password");
        String url = prop.getProperty("db.url");

        ///Hakea titokannan annetulla paremterilla
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dataBaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (Exception E){
            System.out.println("Exeption: " + E);
        }
        /// Palauttaa tietokannan yhteyden
        return dataBaseLink;
    }
}
