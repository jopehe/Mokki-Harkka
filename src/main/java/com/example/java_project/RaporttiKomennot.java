package com.example.java_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RaporttiKomennot {







    /// HAkee listan kuinka mota varausta henkilö on tehnyt

    /// VOI hakea varauksia vuoden tarkuudella


    /// VOi nöhdä onko varaus maksettu ???


    // MOKKI ID ,VARATUT PAIVAT,                ,VARAUS PROSENTTI

    ///  SELECT  id, COUNT(id) AS days FROM mokki WHERE aloitus >= ??? AND  lopetus <= ????
    ///GROUP BY id


    /// VOIDAAN NÄHDÄ PALJONKO VOITTOA ON TULLUT VUODEN TAI KUUKAUDEN PERUSTEELLA



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
                int id = result.getInt("id");
                String osoite = result.getString("osoite");
                Double hinta = result.getDouble("hinta");
                Double koko = result.getDouble("koko");
                int lkm = result.getInt("huone_lkm");
                boolean keittio = result.getBoolean("keittio");
                boolean kylpyhuone = result.getBoolean("kylpyhuone");


                Mokki newMokki = new Mokki(id,  osoite, hinta, koko, lkm, keittio, kylpyhuone);
                mokit.add(newMokki);
                System.out.println(newMokki.getId());
                i++;
            }
        }catch (Exception E){
            System.out.println("Error getting mokkit: " + E);
        }
        return mokit;
    }

}
