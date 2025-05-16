package com.example.java_project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaskuKomennot {



    public static List<Lasku> getAllLaskut(){

        String word = "SELECT id, varaus_id, luonti_pvm, era_pvm, y_tunnus, viitenumero, hinta, maksettu " +
                "FROM lasku";

        List<Lasku> laskut = new ArrayList<>();
        try{
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(word);

            //int i = 1;
            while(result.next()){

                System.out.println("Kaikki mokit ladattu");
                int id = result.getInt("id");
                int varausid = result.getInt("varaus_id");
                Date luontiPvm = result.getDate("luonti_pvm");
                Date eraPvm = result.getDate("era_pvm");
                String y_tunnus = result.getString("y_tunnus");
                String viiteNum = result.getString("viitenumero");
                double hinta = result.getDouble("hinta");
                boolean maksettu = result.getBoolean("maksettu");

                Lasku newLasku = new Lasku(id,  varausid, luontiPvm, eraPvm, y_tunnus, viiteNum, hinta, maksettu);
                laskut.add(newLasku);

                //i++;
            }
        }catch (Exception E){
            System.out.println("Error getting laskut: " + E);
        }
        return laskut;
    }



    public static void createLasku( int varaus, Date luontiPvm, Date eraPvm, String ytun, String viitenum, double hinta, boolean maksettu){
        try {
            String addUser = "INSERT INTO lasku (varaus_id, luonti_pvm, era_pvm, y_tunnus, viitenumero, hinta, maksettu) VALUES(?,?,?,?,?,?,?)";
            DatabaseConnection connection = new DatabaseConnection();
            Connection dbconection = connection.getDatabaseConnection();
            PreparedStatement statement = dbconection.prepareStatement(addUser);

            statement.setInt(1, varaus);
            statement.setDate(2, luontiPvm);
            statement.setDate(3, eraPvm);

            statement.setString(4, ytun);
            statement.setString(5, viitenum);
            statement.setDouble(6, hinta);
            statement.setBoolean(7, maksettu);


            statement.executeUpdate();

        } catch (Exception E){
            System.out.println("Error creating new user: " + E);
        }
    }


    public static void updateLasku(int varaus, Date luontiPvm, Date eraPvm, String ytun, String viitenum, double hinta, boolean maksettu) {
        try {
            String sql = "UPDATE lasku SET " +
                    "luonti_pvm = ?, " +
                    "era_pvm = ?, " +
                    "y_tunnus = ?, " +
                    "viitenumero = ?, " +
                    "hinta = ?, " +
                    "maksettu = ? " +
                    "WHERE varaus_id = ?";

            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setDate(1, luontiPvm);
            statement.setDate(2, eraPvm);
            statement.setString(3, ytun);
            statement.setString(4, viitenum);
            statement.setDouble(5, hinta);
            statement.setBoolean(6, maksettu);
            statement.setInt(7, varaus);  // WHERE-ehto

            int rowsUpdated = statement.executeUpdate();
            System.out.println("Päivitetyt rivit: " + rowsUpdated);

            con.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();  // Näytä virhe konsolissa
        }
    }


    public void poistaLasku(int id) {
        try {
            String sql = "DELETE FROM lasku WHERE id = ?";
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Lasku id:llä " + id + " poistettu onnistuneesti.");
            } else {
                System.out.println("Laskua id:llä " + id + " ei löytynyt.");
            }
            statement.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error deleting lasku: " + e);
        }
    }
}
