package com.example.java_project;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

        getAllMokit();
        //removeMokki(1);
        //addNewMokki("Lehmonkyla 12", 25.50, 15.00, 2, true, true);
    }

    /**
     * Hakee kaikki mökit jotka on tallennettu tietokantaan
     * @return lista mökki olioita jotka on tallennettu tietokantaan
     */
    public static List<Mokki> getAllMokit(){
        List<Mokki> mokit = new ArrayList<>();
        try{
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            Statement statement = con.createStatement();

            String getMokkitCom = "SELECT * FROM mokki ";

            ResultSet result = statement.executeQuery(getMokkitCom);

            int i = 1;
            while(result.next()){
                System.out.println("Kaikki mokit ladattu");
                String osoite = result.getString("osoite");
                Double hinta = result.getDouble("hinta");
                Double koko = result.getDouble("koko");
                int lkm = result.getInt("huone_lkm");
                boolean keittio = result.getBoolean("keittio");
                boolean kylpyhuone = result.getBoolean("kylpyhuone");


                Mokki newMokki = new Mokki(i,  osoite, hinta, koko, lkm, keittio, kylpyhuone);
                mokit.add(newMokki);
                System.out.println(newMokki.toString());
                i++;
            }
        }catch (Exception E){
            System.out.println("Error getting mokkit: " + E);
        }
        return mokit;
    }

    /**
     * Lataa mökki datan yhtä hakua kohden
     * @param result on vastaus joka executeQuary on saanut anetuilla parametreillä
     * @param index joka haetulla mökillä on
     * @return palautta mökki olion joka sisältää halutun datan
     */
    private static Mokki getMokkiData(ResultSet result, int index){
        try{
            String osoite = result.getString("osoite");
            Double hinta = result.getDouble("hinta");
            Double koko = result.getDouble("koko");
            int lkm = result.getInt("huone_lkm");
            boolean keittio = result.getBoolean("keittio");
            boolean kylpyhuone = result.getBoolean("kylpyhuone");

            return new Mokki(index,  osoite, hinta, koko, lkm, keittio, kylpyhuone);
        }catch (Exception E){
            System.out.println("Mokki data was not loaded: " + E);
        }
        return new Mokki();
    }


    /**
     * Palautta kaikki mökit joilla on samanlaiset arvoit kuin
     * @param osoite on minkä laisia osoiteita haetaan
     * @return on lista mokkeja jotka vastaavat annetujen parametrien hajemia arvoja
     */
    private static List<Mokki> getAllLikeOsoite(String osoite){
        List<Mokki> mokit = new ArrayList<>();
        try{
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            Statement statement = con.createStatement();

            String getOsoiteLike = "SELECT * FROM mokki WHERE mokki LIKE %" + osoite + "%";

            ResultSet reult = statement.executeQuery(getOsoiteLike);
            int i = 1;

            while(reult.next()){
                Mokki mok = getMokkiData(reult, i);
                mokit.add(mok);
                i++;

            }
        }catch (Exception E){
            System.out.println("Error getting mokkit: " + E);
        }
        return mokit;
    }

    /**
     * Palauttaa määrän yhdistettyjä tauluja joita on annetuun id arvoon
     * @paran id on arvo mökille joihin yhdistettyjen taulujen määrää haetaan
     * @return palauttaa luku määrän tauluja jotka on yhdistetty
    */
    private static int getConnectionAmount(int id){
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            Statement statement = con.createStatement();

            String getAmount = "SELECT id FROM mokki WHERE id IN(SELECT mokki_id FROM varaus)";

            ResultSet res = statement.executeQuery(getAmount);
            int i = 0;
            while (res.next()){
                i++;
            }
            return i;
        }
        catch (Exception E){
            System.out.println("Error getin amount of connection to mökki table: " + E);
        }
        return -1;
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

            String removeMokkiCom = "DELETE FROM mokki WHERE id = ?";
            PreparedStatement pst = dbconection.prepareStatement(removeMokkiCom);
            pst.setInt(1, id);
            pst.executeUpdate();

            //dbstatement.executeUpdate(removeMokkiCom);
            System.out.println("Mokki postettu.");

            //connection.
            dbconection.close();
        }catch (Exception E){
            System.out.println("Error when removing new mökki to table!");
        }

    }

    /**
     * Lisää mokki tauluun uuden mökin
     * @param osoite mökkiä vastaava osoite
     * @param hinta mökkin hinta
     * @param koko mökin koko
     * @param huonelkm huoneiden lukumäärä
     * @param keittio onko mökissä keittiö
     * @param kylpyhuone onko mökissä kylpyhuone
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
