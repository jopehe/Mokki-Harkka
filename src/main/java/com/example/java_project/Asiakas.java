package com.example.java_project;

/**
 * Luokka, joka kuvaa asiakkaan tietoja.
 * Asiakkaalla on ID, nimi, puhelinnumero ja sähköpostiosoite.
 */
class Asiakas {
    private int id;          // Asiakkaan ID
    private String name;     // Asiakkaan nimi
    private String puhelinN; // Asiakkaan puhelinnumero
    private String email;    // Asiakkaan sähköpostiosoite


    /**
     * Pääohjelma, joka luo Asiakas-olion ja tulostaa sen tiedot.
     */
    public static void main(String[] args) {

        Asiakas asiakas1 = new Asiakas(1, "Asiakas Ihminen", "040000000", "asiakas@email.com");

        // Tulostetaan asiakasolion tiedot
        System.out.println(asiakas1.toString());
    }

    /**
     * Ylikirjoitettu toString-metodi, joka palauttaa Asiakas-olion tiedot merkkijonona.
     *
     * @return Asiakkaan tiedot merkkijonona
     */
    @Override
    public String toString() {
        return "Asiakkaan ID: " + id +
                "\nNimi: " + name +
                "\nPuhelin: " + puhelinN +
                "\nEmail: " + email;
    }

    /**
     * Oletusmuodostaja, joka alustaa asiakasolion oletusarvoilla.
     * ID on -1 ja kaikki merkkijonot tyhjiä.
     */
    public Asiakas() {
        id = -1;
        name = "";
        puhelinN = "";
        email = "";
    }

    /**
     * Parametrillinen konstruktorimetodi, joka alustaa Asiakas-olion annetuilla arvoilla.
     *
     * @param id_ Asiakkaan ID
     * @param name_ Asiakkaan nimi
     * @param puhelinN_ Asiakkaan puhelinnumero
     * @param email_ Asiakkaan sähköpostiosoite
     */
    public Asiakas(int id_, String name_, String puhelinN_, String email_) {
        id = id_;
        name = name_;
        puhelinN = puhelinN_;
        email = email_;
    }

    /**
     * Palauttaa asiakasolion tiedot merkkijonona (ID, nimi, puhelinnumero, sähköpostiosoite).
     *
     * @return Asiakkaan tiedot merkkijonona
     */
    public String getString() {
        return id + ", " + name + ", " + puhelinN + ", " + email;
    }

    /**
     * Palauttaa asiakkaan ID:n.
     *
     * @return Asiakkaan ID
     */
    public int getId() {
        return id;
    }

    /**
     * Palauttaa asiakkaan nimen.
     *
     * @return Asiakkaan nimi
     */
    public String getName() {
        return name;
    }

    /**
     * Palauttaa asiakkaan puhelinnumeron.
     *
     * @return Asiakkaan puhelinnumero
     */
    public String getPuhelinN() {
        return puhelinN;
    }

    /**
     * Palauttaa asiakkaan sähköpostiosoitteen.
     *
     * @return Asiakkaan sähköpostiosoite
     */
    public String getEmail() {
        return email;
    }

    /**
     * Asettaa asiakkaan ID:n.
     *
     * @param id Asiakkaan uusi ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Asettaa asiakkaan nimen.
     *
     * @param name Asiakkaan uusi nimi
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Asettaa asiakkaan puhelinnumeron.
     *
     * @param puhelinN Asiakkaan uusi puhelinnumero
     */
    public void setPuhelinN(String puhelinN) {
        this.puhelinN = puhelinN;
    }

    /**
     * Asettaa asiakkaan sähköpostiosoitteen.
     *
     * @param email Asiakkaan uusi sähköpostiosoite
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
