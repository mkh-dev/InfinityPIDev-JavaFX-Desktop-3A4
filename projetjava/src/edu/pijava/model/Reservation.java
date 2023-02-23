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
public class Reservation {
       private int numRes;
       private int idUser;
       private int nbPlaces;
       private int idEvent;

    public Reservation(int idUser, int nbPlaces, int idEvent) {
        this.idUser = idUser;
        this.nbPlaces = nbPlaces;
        this.idEvent = idEvent;
    }

    public Reservation(int numRes, int idUser, int nbPlaces, int idEvent) {
        this.numRes = numRes;
        this.idUser = idUser;
        this.nbPlaces = nbPlaces;
        this.idEvent = idEvent;
    }

    public Reservation() {
    }

    public int getNumRes() {
        return numRes;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setNumRes(int numRes) {
        this.numRes = numRes;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    @Override
    public String toString() {
        return "Reservation{" + "numRes=" + numRes + ", idUser=" + idUser + ", nbPlaces=" + nbPlaces + ", idEvent=" + idEvent + '}';
    }

  

    
}