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
    private float netApayer;
    private int numRes;
    private String nomUser;

    public Facture() {
    }

    /**
     *
     * @param idFacture
     * @param numRes
     * @param netApayer
     * @param nomUser
     */
    public Facture( int numRes, float netApayer, String nomUser) {

        this.numRes = numRes;
        this.netApayer = netApayer;
        this.nomUser = nomUser;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public float getNetApayer() {
        return netApayer;
    }

    public int getNumRes() {
        return numRes;
    }

    /**
     *
     * @return
     */
    public String getNomUser() {
        return nomUser;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public void setNetApayer(float netApayer) {
        this.netApayer = netApayer;
    }

    public void setNumRes(int numRes) {
        this.numRes = numRes;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    @Override
    public String toString() {
        return "Facture{" + "idFacture=" + idFacture + ", netApayer=" + netApayer + ", numRes=" + numRes + ", nomUser=" + nomUser + '}';
    }

}
