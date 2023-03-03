/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.model;

/**
 *
 * @author ouesl
 */
public class Facture {

    private int idFacture;
    private int netApayer;
    private int numRes;
    private int idUser;

    public Facture() {
    }

    /**
     *
     * @param netApayer
     * @param idUser
     */
    
    
    public Facture( int netApayer, int idUser) {

        this.netApayer = netApayer;
        this.idUser = idUser;
    }

    public Facture(int numRes, int netApayer, int idUser) {
        this.numRes = numRes;
        this.netApayer = netApayer;
        this.idUser = idUser;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public int getNetApayer() {
        return netApayer;
    }

    public int getNumRes() {
        return numRes;
    }

    /**
     *
     * @return
     */
    public int getIdUser() {
        return idUser;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public void setNetApayer(int netApayer) {
        this.netApayer = netApayer;
    }

    public void setNumRes(int numRes) {
        this.numRes = numRes;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Facture{" + "idFacture=" + idFacture + ", netApayer=" + netApayer + ", numRes=" + numRes + ", idUser=" + idUser + '}';
    }

    public void setPrenom(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
