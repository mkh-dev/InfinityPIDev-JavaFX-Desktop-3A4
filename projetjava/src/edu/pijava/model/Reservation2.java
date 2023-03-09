/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.model;

import java.sql.Timestamp;

/**
 *
 * @author ouesl
 */
public class Reservation2 {
           private int numRes;
       private int idUser;
       private int nbPlaces;
       private int idEvent;
       private String nom_event;
        private String description_event;
    private Timestamp date_debut_event;
    private Timestamp date_fin_event;
    private String lieu_event;

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

    public String getNom_event() {
        return nom_event;
    }

    public String getDescription_event() {
        return description_event;
    }

    public Timestamp getDate_debut_event() {
        return date_debut_event;
    }

    public Timestamp getDate_fin_event() {
        return date_fin_event;
    }

    public String getLieu_event() {
        return lieu_event;
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

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public void setDescription_event(String description_event) {
        this.description_event = description_event;
    }

    public void setDate_debut_event(Timestamp date_debut_event) {
        this.date_debut_event = date_debut_event;
    }

    public void setDate_fin_event(Timestamp date_fin_event) {
        this.date_fin_event = date_fin_event;
    }

    public void setLieu_event(String lieu_event) {
        this.lieu_event = lieu_event;
    }

    public Reservation2(int numRes, int idUser, int nbPlaces, int idEvent, String nom_event, String description_event, Timestamp date_debut_event, String lieu_event) {
        this.numRes = numRes;
        this.idUser = idUser;
        this.nbPlaces = nbPlaces;
        this.idEvent = idEvent;
        this.nom_event = nom_event;
        this.description_event = description_event;
        this.date_debut_event = date_debut_event;
        this.lieu_event = lieu_event;
    }

    public Reservation2() {
    }

    public Reservation2(int idUser, int nbPlaces, int idEvent, String nom_event, String description_event, Timestamp date_debut_event, String lieu_event) {
        this.idUser = idUser;
        this.nbPlaces = nbPlaces;
        this.idEvent = idEvent;
        this.nom_event = nom_event;
        this.description_event = description_event;
        this.date_debut_event = date_debut_event;
        this.lieu_event = lieu_event;
    }
    
    
    
}
