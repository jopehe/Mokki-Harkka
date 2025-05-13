package com.example.java_project;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserKomennot {




    public void updateUsers(int id, String userName, String password){
        try {
            String word = "UPDATE user SET " +
                    "user_name = ?, " +
                    "password = ?";

            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            PreparedStatement statement = con.prepareStatement(word);

            statement.setString(1, userName);
            statement.setString(2, password);

            statement.executeUpdate();
            con.close();
            statement.close();
        } catch (Exception E){

        }
    }

    public void createUser(String userName, String password){
        try {
            String addUser = "INSERT INTO user (username, password) VALUES(?,?)";
            DatabaseConnection connection = new DatabaseConnection();
            Connection dbconection = connection.getDatabaseConnection();
            PreparedStatement statemet = dbconection.prepareStatement(addUser);

            statemet.setString(1, userName);
            statemet.setString(2, password);

        } catch (Exception E){

        }
    }


    public boolean findUser(String name, String password){
        String word = "SELECT COUNT(id) FROM user " +
                "WHERE user_name = ? AND ";
        //        "password = ?";

        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(word);

            int dat = result.getInt(1);

            if(dat >= 1){
                return true;
            }
        } catch (Exception E){
            System.out.println("Error trying to check user: " + E);
        }
        return false;
    }


    /**
     * Hakee id arvon joka käyttäjällä on
     * @param name käyttäjän käytäjä nimi
     * @param password käyttäjän salasana
     * @return palauttettu id arvo käyttäjälle
     */
    public int findUserId(String name, String password){
        String word = "SELECT id FROM user " +
                "WHERE user_name = ? AND ";
        //        "password = ?";

        try {
            DatabaseConnection connection = new DatabaseConnection();
            Connection con = connection.getDatabaseConnection();
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(word);


            return result.getInt(0);
        } catch (Exception E){
            System.out.println("Error trying to check user: " + E);
        }
        return -1;
    }

}
