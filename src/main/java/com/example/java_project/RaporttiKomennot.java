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
        raporttiVarauksetPerMokki();
    }

     public static LocalDate getToday(){
        return LocalDate.now();
     }


    /**
     * Hakee määrän tuloja joita mökeistä on saatu aikavälillä
     * @return palautettu string lista eri mökeistä
     */
    public static List<String> raporttiVarauksetPerMokkiAikavalilla(LocalDate start, LocalDate end){
        List<String> tulokset = new ArrayList<>();
        String sql = "SELECT mokki.id, mokki.osoite,  COUNT(varaus.id) AS varauksia " +
                "FROM mokki " +
                "JOIN varaus ON mokki.id = varaus.mokki_id " +
                "WHERE varaus.varaus_alku >= ? AND varaus.varaus_loppu <= ? " +
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
                String rivi = "Mökki: " + id  + ", osoite: " + osoite +
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
     * Hakee määrän tuloja joita mökistä on saatu
     * @return palautettu string lista eri mökeistä
     */
    public  static List<String> tulotPerMokki(){
        List<String> tulokset = new ArrayList<>();
        String sql = "SELECT mokki.id, SUM(varaus.hinta) AS Tulot " +
                "FROM mokki " +
                "JOIN varaus ON mokki.id = varaus.mokki_id " +
                "GROUP BY mokki.id";
        try (Connection con = new DatabaseConnection().getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int tulot = rs.getInt("Tulot");
                String rivi = "Mökki: " + id +
                        " = Tulot: " + tulot;

                System.out.println(rivi);
                tulokset.add(rivi);
            }
        } catch (Exception e) {
            System.out.println("Error mökkien ja varausten hakemisessa: " + e);
        }
        return tulokset;
    }



    public  static List<String> tulotPerMokkiPaivitain(LocalDate start, LocalDate end){
        List<String> tulokset = new ArrayList<>();
        String sql = "SELECT mokki.id, SUM(varaus.hinta) AS Tulot " +
                "FROM mokki " +
                "JOIN varaus ON mokki.id = varaus.mokki_id " +
                "WHERE varaus.varaus_alku >= ? AND varaus.varaus_loppu <= ?" +
                "GROUP BY mokki.id";
        try (Connection con = new DatabaseConnection().getDatabaseConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(start));
            stmt.setDate(2, Date.valueOf(end));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int tulot = rs.getInt("Tulot");
                String rivi = "Mökki: " + id +
                        " = Tulot: " + tulot;

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
                String rivi = "Mökki: " + id  + ", osoite: " + osoite +
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
                "WHERE varaus.varaus_alku >= '2025-01-01' AND varaus.varaus_loppu <= '2026-01-01' " +
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
                String rivi = "Asiakas: " + nimi  + ", sähköposti: " + email + ", puh: " + puh +
                        " = Varauksia: " + varaukset;

                //System.out.println(rivi);
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
    public static List<String> raporttiVarauksetPerAsiakasAikavalilla(String start, String end) {
        List<String> tulokset = new ArrayList<>();
        String sql = "SELECT asiakas.id, asiakas.nimi,  asiakas.email, asiakas.puhelinumero, COUNT(varaus.id) AS varauksia " +
                "FROM asiakas " +
                "JOIN varaus ON asiakas.id = varaus.asiakas_id " +
                "WHERE varaus.varaus_alku >= ? AND varaus.varaus_loppu <= ? " +
                "GROUP BY asiakas.id";

        try (Connection con = new DatabaseConnection().getDatabaseConnection();
            PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, start);
            stmt.setString(2, end);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("id");
                String email = rs.getString("email");
                String nimi = rs.getString("nimi");
                String puh = rs.getString("puhelinumero");
                int varaukset = rs.getInt("varauksia");
                String rivi = "Asiakas: " + nimi  + ", sähköposti: " + email + ", puh: " + puh +
                        " = Varauksia: " + varaukset;

                //System.out.println(rivi);
                tulokset.add(rivi);
            }
        } catch (Exception e) {
            System.out.println("Error asiakkaiden ja varausten hakemisessa: " + e);

        }
        return tulokset;
    }







}
