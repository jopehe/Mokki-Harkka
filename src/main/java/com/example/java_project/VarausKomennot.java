package com.example.java_project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VarausKomennot {


    /// SELECT ALL VARAUKSET
    public static List<Varaus> getVaraukset(){
        String word = "SELECT id, asiakas_id, mokki_id, hinta, varaus_alku, varaus_loppu, luonti_paiva FROM varaus";
        List<Varaus> varaukset = new ArrayList<>();

        try{
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(word);

            while(result.next()){

                System.out.println("Kaikki varaukset ladattu");
                int id = result.getInt("id");
                int asiakasid = result.getInt("asiakas_id");
                int mokkiid = result.getInt("mokki_id");
                double hinta = result.getDouble("hinta");
                Date varausA = result.getDate("varaus_alku");
                Date varausL = result.getDate("varaus_loppu");
                Date lp = result.getDate("luonti_paiva");
                Varaus newVaraus = new Varaus(id, mokkiid, asiakasid, hinta, varausA, varausL, lp);
                varaukset.add(newVaraus);
            }
        }catch (Exception E){
            System.out.println("Error getting laskut: " + E);
        }
        return varaukset;
    }


    /**
     * Adds new varaus
     * @param asiakasId
     * @param mokkiId
     * @param hinta
     * @param alku
     * @param loppu
     * @param luontiP
     */
    public static void addNewVaraus(int asiakasId, int mokkiId, double hinta, Date alku, Date loppu, Date luontiP) {
        try {
            String sql = "INSERT INTO varaus (asiakas_id, mokki_id, hinta, varaus_alku, varaus_loppu, luonti_paiva) VALUES (?, ?, ?, ?, ?, ?)";
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setInt(1, asiakasId);
            statement.setInt(2, mokkiId);
            statement.setDouble(3, hinta);
            statement.setDate(4, alku);
            statement.setDate(5, loppu);
            statement.setDate(6, luontiP);

            statement.executeUpdate();
            con.close();

            System.out.println("Uusi varaus lisätty");
        } catch (Exception e) {
            System.out.println("Error adding new varaus: " + e);
        }
    }



    /// POISTA VARAUS
    public static void poistaVaraus(int id){
        try{
            DatabaseConnection connection = new DatabaseConnection();
            Connection dbconnection = connection.getDatabaseConnection();

            String removeVarausCom = "DELETE FROM varaus WHERE id = ?";
            PreparedStatement pst = dbconnection.prepareStatement(removeVarausCom);
            pst.setInt(1, id);
            pst.executeUpdate();
            pst.close();
            dbconnection.close();

            System.out.println("Varaus poistettu id=" + id);
        }catch (Exception e){
            System.out.println("Error when removing varaus from table! " + e);
        }
    }

    /// MUOKKAA VARAUS
    public static void muokkaaVarausta(int asiakasId, int mokkiId, double hinta, Date alku, Date loppu, Date luontiPvm) {
        String sql = "UPDATE varaus SET " +
                "hinta = ?, " +
                "varaus_alku = ?, " +
                "varaus_loppu = ?, " +
                "luonti_paiva = ? " +
                "WHERE asiakas_id = ? AND mokki_id = ?";

        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setDouble(1, hinta);
            statement.setDate(2, alku);
            statement.setDate(3, loppu);
            statement.setDate(4, luontiPvm);
            statement.setInt(5, asiakasId);
            statement.setInt(6, mokkiId);

            int rowsAffected = statement.executeUpdate();
            statement.close();
            con.close();

            if (rowsAffected > 0) {
                System.out.println("Varaus asiakkaalle id=" + asiakasId + " ja mökille id=" + mokkiId + " päivitetty onnistuneesti.");
            } else {
                System.out.println("Ei löytynyt päivitettävää varausta asiakkaalle id=" + asiakasId + " ja mökille id=" + mokkiId);
            }
        } catch (Exception e) {
            System.out.println("ERROR: Varausta ei voitu päivittää. " + e);
        }
    }


    /// SELECK ALL ID IN USERS
    public static List<Integer> getAllAsiakasId(){
        List<Integer> asiakasIdt = new ArrayList<>();
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM asiakas");
            while(rs.next()){
                asiakasIdt.add(rs.getInt("id"));
            }
            con.close();
        } catch(Exception e){
            System.out.println("Error getting all asiakas IDs: " + e);
        }
        return asiakasIdt;
    }


    ///  SELECT ALL ID IN MOKKIT
    public static List<Integer> getAllMokkiId(){
        List<Integer> mokkiIdt = new ArrayList<>();
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM mokki");
            while(rs.next()){
                mokkiIdt.add(rs.getInt("id"));
            }
            con.close();
        } catch(Exception e){
            System.out.println("Error getting all mokki IDs: " + e);
        }
        return mokkiIdt;
    }


    /// CHECK IF LASKU ON TEHTY VARAUKSESTA
    public static boolean checkLaskuOnTehty(int varausId){
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            PreparedStatement pst = con.prepareStatement("SELECT COUNT(*) FROM lasku WHERE varaus_id = ?");
            pst.setInt(1, varausId);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                con.close();
                return count > 0;
            }
            con.close();
        } catch(Exception e){
            System.out.println("Error checking lasku for varaus_id " + varausId + ": " + e);
        }
        return false;
    }


}
