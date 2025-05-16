package com.example.java_project;

import java.sql.Date;

/**
 * Sisältää tiedot mitä yksi varaus sisöltää
 */
public class Varaus {

    private int varausId ;
    private int mokkiId;
    private int asiakasId;
    private double hinta;
    private Date vAloitus;
    private Date vLopetus;
    private Date luontiP;

    @Override
    public String toString(){
        return ""+ varausId + ", " + mokkiId + ", " + asiakasId + ", " + hinta + ", " + vAloitus + ", " + vLopetus + ", " + luontiP;
    }


    /**
     *
     */
    public Varaus(){
        this.varausId= -1;
        mokkiId = -1;
        asiakasId = -1;
        hinta = -1.0;
        vAloitus = null;
        vLopetus = null;
        luontiP = null;
    }

    /**
     *
     * @param id_
     * @param mokkiId_
     * @param asiakasId_
     * @param hinta_
     * @param aloitus
     * @param lopetus
     * @param luonti
     */
    public Varaus(int id_, int mokkiId_, int asiakasId_, double hinta_, Date aloitus, Date lopetus, Date luonti){
        this.varausId = id_;
        mokkiId = mokkiId_;
        asiakasId = asiakasId_;
        hinta = hinta_;
        vAloitus = aloitus;
        vLopetus = lopetus;
        luontiP = luonti;
    }



    public int getVarausId() {
        return varausId;
    }

    public void setVarausId(int varausId) {
        this.varausId = varausId;
    }

    public int getMokkiId() {
        return mokkiId;
    }

    public void setMokkiId(int mokkiId) {
        this.mokkiId = mokkiId;
    }

    public int getAsiakasId() {
        return asiakasId;
    }

    public void setAsiakasId(int asiakasId) {
        this.asiakasId = asiakasId;
    }

    public double getHinta() {
        return hinta;
    }

    public void setHinta(double hinta) {
        this.hinta = hinta;
    }

    public Date getvAloitus() {
        return vAloitus;
    }

    public void setvAloitus(Date vAloitus) {
        this.vAloitus = vAloitus;
    }

    public Date getvLopetus() {
        return vLopetus;
    }

    public void setvLopetus(Date vLopetus) {
        this.vLopetus = vLopetus;
    }

    public Date getLuontiP() {
        return luontiP;
    }

    public void setLuontiP(Date luontiP) {
        this.luontiP = luontiP;
    }


}
