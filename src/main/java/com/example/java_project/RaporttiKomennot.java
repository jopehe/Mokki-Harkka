package com.example.java_project;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RaporttiKomennot extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //System.out.println("OK");
        raporttiVarauksetPerMokki();
        //raporttiVarauksetPerAsiakas();
        //selectThings(LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31));

    }

     public static LocalDate getToday(){
        return LocalDate.now();
     }


    /**
     * VALITSEE KAIKKI VARAUKSET JOITA jokainen henkilö on tehtnyt HENKILÖ ON TEHNYT
     */
    public static List<String> raporttiVarauksetPerMokkiAikavalilla(LocalDate start, LocalDate end){
        List<String> tulokset = new ArrayList<>();
        String sql = "SELECT mokki.id, mokki.osoite,  COUNT(varaus.id) AS varauksia " +
                "FROM mokki " +
                "JOIN varaus ON mokki.id = varaus.mokki_id " +
                "WHERE mokki_database.varaus.varaus_alku >= ? AND mokki_database.varaus.varaus_loppu <= ? " +
                "GROUP BY mokki.id";

        try (Connection con = new DatabaseConnection().getDatabaseConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(start));
            stmt.setDate(2, Date.valueOf(end));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String osoite = rs.getString("osoite");
                int varaukset = rs.getInt("varauksia");
                String rivi = "Mokki: " + id  + ", " + osoite +
                        " = Varauksia: " + varaukset;

                System.out.println(rivi);
                tulokset.add(rivi);
            }
        } catch (Exception e) {
            System.out.println("Error mökkien ja varausten hakemisessa: " + e);

        }
        return tulokset;
    }





    /**
     * Listaa varaukset per mökki  aika järjestyksessä
     */
    public static List<String> raporttiVarauksetPerMokki() {
        List<String> tulokset = new ArrayList<>();
        String sql = "SELECT mokki.id, mokki.osoite,  COUNT(varaus.id) AS varauksia " +
                "FROM mokki " +
                "JOIN varaus ON mokki.id = varaus.mokki_id " +
                "GROUP BY mokki.id";

        try (Connection con = new DatabaseConnection().getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                String osoite = rs.getString("osoite");
                int varaukset = rs.getInt("varauksia");
                String rivi = "Mokki: " + id  + ", " + osoite +
                        " = Varauksia: " + varaukset;

                System.out.println(rivi);
                tulokset.add(rivi);
            }
        } catch (Exception e) {
            System.out.println("Error mökkien ja varausten hakemisessa: " + e);
        }
        return tulokset;
    }

    /**
     * Listaa varaukset per asiakas
     */
    public static List<String> raporttiVarauksetPerAsiakas() {
        List<String> tulokset = new ArrayList<>();
        String sql = "SELECT asiakas.id, asiakas.nimi,  asiakas.email, asiakas.puhelinumero, COUNT(varaus.id) AS varauksia " +
                "FROM asiakas " +
                "JOIN varaus ON asiakas.id = varaus.asiakas_id " +
                "GROUP BY asiakas.id";

        try (Connection con = new DatabaseConnection().getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {

                int id = rs.getInt("id");
                String email = rs.getString("email");
                String nimi = rs.getString("nimi");
                String puh = rs.getString("puhelinumero");
                int varaukset = rs.getInt("varauksia");
                String rivi = "Asiakas: " + nimi  + ", " + email + ", " + puh +
                        " = Varauksia: " + varaukset;

                System.out.println(rivi);
                tulokset.add(rivi);
            }
        } catch (Exception e) {
            System.out.println("Error asiakkaiden ja varausten hakemisessa: " + e);

        }
        return tulokset;
    }


    /**
     * Listaa varaukset per mökki aika järjestyksessä
     */
    public static List<String> raporttiVarauksetPerAsiakasAikavalilla(LocalDate start, LocalDate end) {
        List<String> tulokset = new ArrayList<>();
        String sql = "SELECT asiakas.id, asiakas.nimi,  asiakas.email, asiakas.puhelinumero, COUNT(varaus.id) AS varauksia " +
                "FROM asiakas " +
                "JOIN varaus ON asiakas.id = varaus.asiakas_id " +
                "WHERE mokki_database.varaus.varaus_alku >= ? AND mokki_database.varaus.varaus_loppu <= ? " +
                "GROUP BY asiakas.id";

        try (Connection con = new DatabaseConnection().getDatabaseConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(start));
            stmt.setDate(2, Date.valueOf(end));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                String email = rs.getString("email");
                String nimi = rs.getString("nimi");
                String puh = rs.getString("puhelinumero");
                int varaukset = rs.getInt("varauksia");
                String rivi = "Asiakas: " + nimi  + ", " + email + ", " + puh +
                        " = Varauksia: " + varaukset;

                System.out.println(rivi);
                tulokset.add(rivi);
            }
        } catch (Exception e) {
            System.out.println("Error asiakkaiden ja varausten hakemisessa: " + e);

        }
        return tulokset;
    }
}
