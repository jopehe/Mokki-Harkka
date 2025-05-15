package com.example.java_project;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Sisältää tiedot mitä yksi lasku olio sisältää
 */
public class Lasku {

    private int id;
    private int varausId;
    private Date laskuPVM;
    private String yt;
    private Date eraP;
    private String viiteNum;
    private double hinta;
    private boolean maksettu;

    @Override
    public String toString(){
        return "" + varausId + yt + eraP + viiteNum + hinta;
    }

    public Lasku(){
        id = -1;
        varausId = -1;
        laskuPVM = Date.valueOf("0000-0-0");
        yt = "0000000001";
        eraP = Date.valueOf("0000-0-0");
        viiteNum = "000000000";
        hinta = -1.0;
    }

    public Lasku(int id_,  int varausid, Date luontiPvm, Date eraPav, String y_tunnus, String viiteNum_, double hinta_,  boolean maksettu_){

        id = id_;
        varausId = varausid;
        laskuPVM = luontiPvm;
        yt = y_tunnus;
        eraP = eraPav;
        viiteNum = viiteNum_;
        hinta = hinta_;
        maksettu = maksettu_;
    }


    public String getString(){
        return id + ", " + varausId + ", " + laskuPVM + ", " + yt + ", " + eraP + ", " + viiteNum + ", " + hinta + ", " + maksettu;
    }


    public String getViiteNum() {
        return viiteNum;
    }

    public void setViiteNum(String viiteNum) {
        this.viiteNum = viiteNum;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public Date getEraP() {
        return eraP;
    }

    public void setEraP(Date eraP) {
        this.eraP = eraP;
    }

    public String getYt() {
        return yt;
    }

    public void setYt(String yt) {
        this.yt = yt;
    }

    public Date getLaskuPVM() {
        return laskuPVM;
    }

    public void setLaskuPVM(Date laskuPVM) {
        this.laskuPVM = laskuPVM;
    }

    public int getVarausId() {
        return varausId;
    }

    public void setVarausId(int varausId) {
        this.varausId = varausId;
    }
}
