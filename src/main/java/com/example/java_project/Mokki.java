package com.example.java_project;

/**
 * Sisältää tiedot mitä yksi mökki olio sisältää
 */
public class Mokki {
    private int mokki;
    private String osoite;
    private String hinta;
    private String koko;
    private int huoneLK;
    private boolean keittio;
    private boolean kylpyhuone;


    public static void main(String[] args) {


        Mokki mok = new Mokki(1, "Mäntymäki 11", "20.50e", "20a", 2, true, false);



        System.out.println(mok.toString());
    }



    @Override
    public String toString(){
        return
            "Mökkin id: " + mokki +
            "\nOsoite: " + osoite +
            "\nHinta: " + hinta +
            "\nKoko: " + koko +
            "\nHuoneiden lukumäärä: " + huoneLK +
            "\nKeittiö: " + keittio +
            "\nKylpyhuone: " + kylpyhuone;
    }

    /**
     * Paraetriton alistaka mokille
     */
    public Mokki(){
        mokki = -1;
        osoite = "";
        hinta = "";
        koko = "";
        huoneLK = -1;
        keittio = false;
        kylpyhuone = false;
    }

    /**
     * Alustaja mmökille kaikilla sille tarvittavilla parametreillä
     * @param mokki_ on int id arvo
     * @param osoite_ on string
     * @param kuvaus_ on string
     * @param hinta_ on string
     * @param koko_ on string
     * @param huoneLK_ on int arvo
     * @param keittio_ on boolean
     * @param kylpyhuone_ on boolean
     */
    public Mokki(int mokki_, String osoite_, String hinta_, String koko_, int huoneLK_, boolean keittio_, boolean kylpyhuone_){
        mokki = mokki_;
        osoite = osoite_;
        hinta = hinta_;
        koko = koko_;
        huoneLK = huoneLK_;
        keittio = keittio_;
        kylpyhuone = kylpyhuone_;
    }



    /**
     * Palauttaa mokki idn
     * @return mokkin id
     */
    public  int getMokki() { return  mokki; }

    /**
     * Palauttaa mokin osoitteen
     * @return string osoite
     */
    public String getOsoite() { return  osoite; }


    /**
     * Palauttaa mokkin hinnan
     * @return string hinta
     */
    public String getHinta() { return  hinta; }

    /**
     * Palauttaa mokkin koon
     * @return string koko mokkille
     */
    public String getKoko() { return  koko; }

    /**
     * Palauttaa huoneiden lukumäärän
     * @return int lukumäärrä huoneita
     */
    public int getHuoneLK() { return  huoneLK; }

    /**
     * Palauttaa mikälli mokissa on keittio
     * @return totta jos keittio on
     */
    public boolean getKeittio() { return  keittio; }

    /**
     * Palauttaa mikäli mokissa on kylpyhuone
     * @return totta jos kylpyhuone on
     */
    public  boolean getKylphuone() { return  kylpyhuone; }


    /**
     * Asettaa mokin idn
     * @param mokki_ on int id arvo
     */
    public void setMokki(int mokki_){ mokki=mokki_; }

    /**
     * Asettaa osoitteen mokkiin
     * @param osoite_ string arvo
     */
    public void setOsoite(String osoite_) {osoite = osoite_;}


    /**
     * Settaa hinnan mokille
     * @param hinta_ on string arvo
     */
    public void setHinta(String hinta_) { hinta = hinta_;}

    /**
     * Asettaa koon asunnolle
     * @param koko_ om string arvo
     */
    public void setKoko(String koko_) { koko = koko_; }

    /**
     * Asettaa lukumäärän huoneita
     * @param huoneLK_ on int arvo
     */
    public void setHuoneLK(int huoneLK_){huoneLK = huoneLK_; }

    /**
     * Asettaa mikäli asunnolla on keittiö
     * @param keittio_ on boolean arvo
     */
    public void setKeittio(boolean keittio_){keittio = keittio_;}

    /**
     * Asettaa mikäli asunolla on kylpyhuone
     * @param kulpyhuone_ on boolean arvo
     */
    public void setKulpyhuone(boolean kulpyhuone_) {kylpyhuone = kulpyhuone_;}


}
