package com.example.java_project;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
/**
    * Scripti sisältää eri komenot miten mökki tietokannasta voidaan hakea tietoa
    * Author: Joel Heiskanen
    * Date: 29.4.2025
    * Version: 0.0.5
 * */

public class MokkiKomennot extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        launch();
    }

    public static void main(String[] args) {

        removeMokki(1);
        //addNewMokki("Lehmonkyla 12", 25.50, 15.00, 2, true, true);
    }


    /**
     * Poistaa mökkin tietokannan taulusta
     * @param id kuvaava arvo joka mökkille on asetettu
     */
    public static void removeMokki(int id){
        try{
            DatabaseConnection connection = new DatabaseConnection();
            Connection dbconection = connection.getDatabaseConnection();
            Statement dbstatement = dbconection.createStatement();

            String removeMokkiCom = "DELETE FROM mokki WHERE id =" + id;


            dbstatement.executeUpdate(removeMokkiCom);
            System.out.println("Mokki postettu.");
        }catch (Exception E){
            System.out.println("Error when removing new mökki to table!");
        }
    }

    /**
     * Lisää mokki tauluun uuden mökin
     * @param osoite
     * @param hinta
     * @param koko
     * @param huonelkm
     * @param keittio
     * @param kylpyhuone
     */
    public static void addNewMokki(String osoite, double hinta, double koko, int huonelkm, boolean keittio, boolean kylpyhuone){
        try{
            String addMokkiCom = "INSERT INTO mokki (osoite, hinta, koko, huone_lkm, keittio, kylpyhuone) VALUES(?,?,?,?,?,?)";


            DatabaseConnection connection = new DatabaseConnection();
            Connection dbconection = connection.getDatabaseConnection();
            PreparedStatement statemet = dbconection.prepareStatement(addMokkiCom);

            statemet.setString(1, osoite);
            statemet.setDouble(2, hinta);
            statemet.setDouble(3, koko);
            statemet.setInt(4, huonelkm);
            statemet.setBoolean(5,keittio);
            statemet.setBoolean(6,kylpyhuone);

            statemet.executeUpdate();
            statemet.close();
            dbconection.close();


            System.out.println("Mokki lisätty mokki tauluun!");
        }catch (Exception E){
            System.out.println("Error when adding new mökki to table: " + E);
        }
    }
}
