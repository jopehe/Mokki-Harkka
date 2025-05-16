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
                int varausid = result.getInt("asiakas_id");
                int mokkiid = result.getInt("mokki_id");
                double hinta = result.getDouble("hinta");
                Date varausA = result.getDate("varaus_alku");
                Date varausL = result.getDate("varaus_loppu");
                Date lp = result.getDate("luonti_paiva");
                Varaus newLasku = new Varaus(id,  varausid, mokkiid, hinta, varausA, varausL, lp);
                varaukset.add(newLasku);
            }
        }catch (Exception E){
            System.out.println("Error getting laskut: " + E);
        }
        return varaukset;
    }


    /**
     * Adds new varaus
     * @param ida
     * @param idm
     * @param hinta
     * @param alku
     * @param loppu
     * @param lpaiva
     */
    public static void addNewVaraus( int ida, int idm, double hinta, Date alku, Date loppu, Date lpaiva){
        try {
            String addUser = "INSERT INTO lasku (varaus_id, luonti_pvm, era_pvm, y_tunnus, viitenumero, hinta, maksettu) VALUES(?,?,?,?,?,?,?)";
            DatabaseConnection connection = new DatabaseConnection();
            Connection dbconection = connection.getDatabaseConnection();
            PreparedStatement statement = dbconection.prepareStatement(addUser);

            statement.setInt(1, ida);
            statement.setInt(2, idm);
            statement.setDouble(3, hinta);

            statement.setDate(4, alku);
            statement.setDate(5, loppu);
            statement.setDate(6, lpaiva);

            statement.executeUpdate();

        } catch (Exception E){
            System.out.println("Error creating new user: " + E);
        }

    }

    /// POISTA VARAUS
    public static void poistaVaraus(int id){
        try{
            DatabaseConnection connection = new DatabaseConnection();
            Connection dbconection = connection.getDatabaseConnection();
            Statement dbstatement = dbconection.createStatement();

            String removeMokkiCom = "DELETE FROM varaus WHERE id = ?";
            PreparedStatement pst = dbconection.prepareStatement(removeMokkiCom);
            pst.setInt(1, id);
            pst.executeUpdate();

            //dbstatement.executeUpdate(removeMokkiCom);
            System.out.println("Varaus postettu.");

            //connection.
            dbconection.close();
        }catch (Exception E){
            System.out.println("Error when removing varaus to table!");
        }
    }

    /// MUOKKAA VARAUS
    public static void muokkaaVarausa(int id, int ida, int idm, double hinta, Date alku, Date loppu, Date lpaiva){
        String word = "UPDATE mokki SET" +
                " id = ?"+
                " asiakas_id = ?, " +
                " mokki_id = ?, " +
                " hinta = ?, " +
                " varaus_alku = ?," +
                " varaus_loppu = ?, " +
                " luonti_paiva = ? " +
                " WHERE id =  ?";

        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            PreparedStatement statement = con.prepareStatement(word);

            statement.setInt(1, id);
            statement.setInt(2, ida);
            statement.setInt(3, idm);
            statement.setDouble(4, hinta);
            statement.setDate(5, alku);
            statement.setDate(6, loppu);
            statement.setDate(7, lpaiva);

            statement.executeUpdate();
            con.close();
            statement.close();

        }catch (Exception E){
            System.out.println("ERROR: varaus " + id + "  could not be updated. " + E);
        }
    }


    /// SELECK ALL ID IN USERS


    ///  SELECT ALL ID IN MOKKIT


    /// CHECK IF LASKU ON TEHTY VARAUKSESTA




    ///

}
