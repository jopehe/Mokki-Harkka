package com.example.java_project;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Luokka, joka hallinnoi asiakasdatan käyttöliittymää.
 * Sisältää kentät ja toiminnot asiakkaiden lisäämiseen, muokkaamiseen ja poistamiseen.
 */
public class AsiakasHallinta {

    private AsiakasKomennot asiakasKomennot = new AsiakasKomennot();
    private ListView<String> asiakasListView = new ListView<>();
    private TextField idField = new TextField("");
    private TextField nameField = new TextField("");
    private TextField puhelinField = new TextField("");
    private TextField emailField = new TextField("");

    /**
     * Luo ja palauttaa asiakashallinnan pääasettelun, jossa on asiakaslista ja lomake.
     *
     * @return HBox-pohjainen asettelu käyttöliittymään
     */
    public HBox getLayout() {

        Button lisaaAsiakas = new Button("Lisaa asiakas");
        Button muokkaaAsiakas = new Button("Muokkaa asiakasta");
        Button poistaAsiakas = new Button("Poista asiakas");
        HBox topLayout = new HBox(1, lisaaAsiakas, muokkaaAsiakas, poistaAsiakas);

        Label idLabel = new Label("ID: ");
        Label nameLabel = new Label("Nimi: ");
        Label puhelinLabel = new Label("Puhelinumero: ");
        Label emailLabel = new Label("Email: ");

        VBox formLayout = new VBox(10,
                idLabel, idField,
                nameLabel, nameField,
                puhelinLabel, puhelinField,
                emailLabel, emailField
        );
        formLayout.setPrefWidth(300);

        asiakasListView.setPrefWidth(300);
        updateCustomerList();

        VBox rightSide = new VBox(1, topLayout, formLayout);
        rightSide.setPrefWidth(500);

        VBox leftSide = new VBox(10, asiakasListView);
        leftSide.setPrefWidth(600);

        HBox mainLayout = new HBox(20, rightSide, leftSide);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setPrefSize(1920, 1080);

        // Tapahtumakäsittelijä asiakkaan lisäämiselle
        lisaaAsiakas.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String puhelinN = puhelinField.getText();
                String email = emailField.getText();

                if (checkValidValues(name, puhelinN, email)) {
                    asiakasKomennot.addNewAsiakas(id, name, puhelinN, email);
                    updateCustomerList();
                    clearFields();
                } else {
                    showError("Tarkista tiedot!");
                }
            } catch (NumberFormatException ex) {
                showError("ID täytyy olla numero!");
            }
        });

        // Tapahtumakäsittelijä asiakkaan muokkaamiselle
        muokkaaAsiakas.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String puhelinN = puhelinField.getText();
                String email = emailField.getText();

                if (checkValidValues(name, puhelinN, email)) {
                    asiakasKomennot.modifyAsiakas(id, name, puhelinN, email);
                    updateCustomerList();
                    clearFields();
                } else {
                    showError("Tarkista tiedot!");
                }
            } catch (NumberFormatException ex) {
                showError("ID täytyy olla numero!");
            }
        });

        // Tapahtumakäsittelijä asiakkaan poistamiselle
        poistaAsiakas.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                asiakasKomennot.removeAsiakas(id);
                updateCustomerList();
                clearFields();
            } catch (NumberFormatException ex) {
                showError("ID täytyy olla numero!");
            }
        });

        return mainLayout;
    }

    /**
     * Päivittää asiakaslistan käyttöliittymässä AsiakasKomennot-luokan tietojen perusteella.
     */
    private void updateCustomerList() {
        asiakasListView.getItems().clear();
        for (Asiakas asiakas : asiakasKomennot.getAllAsiakas()) {
            asiakasListView.getItems().add(asiakas.getString());
        }
    }

    /**
     * Tyhjentää kaikki tekstikentät.
     */
    private void clearFields() {
        idField.clear();
        nameField.clear();
        puhelinField.clear();
        emailField.clear();
    }

    /**
     * Tarkistaa, että nimi, puhelinnumero ja sähköposti eivät ole tyhjiä.
     *
     * @param name asiakkaan nimi
     * @param puhelinN asiakkaan puhelinnumero
     * @param email asiakkaan sähköpostiosoite
     * @return true jos kaikki kentät ovat täytetty, muuten false
     */
    private boolean checkValidValues(String name, String puhelinN, String email) {
        return !(name.isEmpty() || puhelinN.isEmpty() || email.isEmpty());
    }

    /**
     * Näyttää virheilmoituksen pop-up-ikkunassa.
     *
     * @param message virheilmoituksen teksti
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }
}
