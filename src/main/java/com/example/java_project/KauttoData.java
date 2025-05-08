package com.example.java_project;

/**
 *
 */
public class KauttoData {


    private int id;
    private int wDays;
    private int nDays;
    private double wPros;

    /**
     * @return
     */
    @Override
    public String toString(){
        return "id: " + id + " , working days: " + wDays + " , not working days: " + nDays + " work %:  " + wPros;
    }


    /**
     *
     */
    public KauttoData(){
        this.id = -1;
        this.wDays = -1;
        this.nDays = -1;
        this.wPros = -1.0;
    }


    /**
     * @param id
     * @param wday
     * @param nday
     * @param wpros
     */
    public KauttoData(int id, int wday, int nday, double wpros){
        this.id = id;
        this.wDays = wday;
        this.nDays = nday;
        this.wPros = wpros;
    }


    /**
     * @return
     */
    public double getwPros() {
        return wPros;
    }

    /**
     * @param wPros
     */
    public void setwPros(double wPros) {
        this.wPros = wPros;
    }

    /**
     * @return
     */
    public int getnDays() {
        return nDays;
    }

    /**
     * @param nDays
     */
    public void setnDays(int nDays) {
        this.nDays = nDays;
    }

    /**
     * @return
     */
    public int getwDays() {
        return wDays;
    }

    /**
     * @param wDays
     */
    public void setwDays(int wDays) {
        this.wDays = wDays;
    }

    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
}

