package com.example.java_project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Luokka sisältää toiminnot asiakastietojen käsittelyyn tietokannassa.
 * Tällä luokalla voi hakea, lisätä, muokata ja poistaa asiakkaita.
 */
public class AsiakasKomennot {

    /**
     * Hakee kaikki asiakkaat tietokannasta ja palauttaa ne listana.
     *
     * @return Lista Asiakas-olioista, jotka löytyvät tietokannasta
     */
    public static List<Asiakas> getAllAsiakas() {
        List<Asiakas> asiakasList = new ArrayList<>();
        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            Statement statement = con.createStatement();

            String getAsiakasCom = "SELECT * FROM asiakas";
            ResultSet result = statement.executeQuery(getAsiakasCom);

            while (result.next()) {
                int id = result.getInt("id");
                String nimi = result.getString("nimi");
                String puhelinN = result.getString("puhelinumero");
                String email = result.getString("email");

                Asiakas asiakas = new Asiakas(id, nimi, puhelinN, email);
                asiakasList.add(asiakas);
            }

        } catch (Exception e) {
            System.out.println("Error getting customers: " + e);
        }
        return asiakasList;
    }

    /**
     * Lisää uuden asiakkaan tietokantaan annetuilla tiedoilla.
     *
     * @param id asiakkaan yksilöllinen ID
     * @param nimi asiakkaan nimi
     * @param puhelinumero asiakkaan puhelinnumero
     * @param email asiakkaan sähköpostiosoite
     */
    public static void addNewAsiakas(int id, String nimi, String puhelinumero, String email) {
        try {
            String addAsiakasCom = "INSERT INTO asiakas (id, nimi, puhelinumero, email) VALUES(?, ?, ?, ?)";

            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            PreparedStatement statement = con.prepareStatement(addAsiakasCom);

            statement.setInt(1, id);
            statement.setString(2, nimi);
            statement.setString(3, puhelinumero);
            statement.setString(4, email);

            statement.executeUpdate();
            statement.close();
            con.close();
            System.out.println("Asiakas added successfully with ID: " + id);
        } catch (Exception e) {
            System.out.println("Error when adding new customer: " + e);
        }
    }

    /**
     * Päivittää olemassa olevan asiakkaan tiedot tietokannassa.
     *
     * @param id asiakkaan ID, jonka tietoja halutaan päivittää
     * @param nimi uusi nimi
     * @param puhelinN uusi puhelinnumero
     * @param email uusi sähköpostiosoite
     */
    public static void modifyAsiakas(int id, String nimi, String puhelinN, String email) {
        try {
            String modifyAsiakasCom = "UPDATE asiakas SET nimi = ?, puhelinumero = ?, email = ? WHERE id = ?";

            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            PreparedStatement statement = con.prepareStatement(modifyAsiakasCom);

            statement.setString(1, nimi);
            statement.setString(2, puhelinN);
            statement.setString(3, email);
            statement.setInt(4, id);

            statement.executeUpdate();
            statement.close();
            con.close();
            System.out.println("Asiakas modified successfully!");
        } catch (Exception e) {
            System.out.println("Error when modifying customer: " + e);
        }
    }

    /**
     * Poistaa asiakkaan tietokannasta annetun ID:n perusteella.
     *
     * @param id asiakkaan ID, joka poistetaan
     */
    public static void removeAsiakas(int id) {
        try {
            String removeAsiakasCom = "DELETE FROM asiakas WHERE id = ?";

            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            PreparedStatement statement = con.prepareStatement(removeAsiakasCom);
            statement.setInt(1, id);
            statement.executeUpdate();

            statement.close();
            con.close();
            System.out.println("Asiakas removed successfully!");
        } catch (Exception e) {
            System.out.println("Error when removing customer: " + e);
        }
    }
}
