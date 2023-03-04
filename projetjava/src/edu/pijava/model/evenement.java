/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.model;


import java.sql.Date;
import java.sql.Timestamp;

public class evenement {
    private int id_event;
    private String nom_event;
    private String description_event;
    private Timestamp date_debut_event;
    private Timestamp date_fin_event;
    private String lieu_event;
    private double budget_event;
    private int id_transport;
    private String image;
    private int id_cat_event;
    public evenement() {
        
        this.id_event = 0;
        this.nom_event = "";
        this.description_event = "";
        this.lieu_event = "";
        this.budget_event = 0;
        this.id_transport = 0;
        this.image = "";
        this.id_cat_event = 0;
    }

    public evenement( String nom_event, String description_event, Timestamp date_debut_event, Timestamp date_fin_event, String lieu_event, double budget_event, int id_transport, String image,int id_cat_event) {
        this.nom_event = nom_event;
        this.description_event = description_event;
        this.date_debut_event = date_debut_event;
        this.date_fin_event = date_fin_event;
        this.lieu_event = lieu_event;
        this.budget_event = budget_event;
        this.id_transport = id_transport;
        this.image = image;
        this.id_cat_event = id_cat_event;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getDescription_event() {
        return description_event;
    }

    public void setDescription_event(String description_event) {
        this.description_event = description_event;
    }

    public Timestamp getDate_debut_event() {
        return date_debut_event;
    }

    public void setDate_debut_event(Timestamp date_debut_event) {
        this.date_debut_event = date_debut_event;
    }

    public Timestamp getDate_fin_event() {
        return date_fin_event;
    }

    public void setDate_fin_event(Timestamp date_fin_event) {
        this.date_fin_event = date_fin_event;
    }

    public String getLieu_event() {
        return lieu_event;
    }

    public void setLieu_event(String lieu_event) {
        this.lieu_event = lieu_event;
    }

    public double getBudget_event() {
        return budget_event;
    }

    public void setBudget_event(double budget_event) {
        this.budget_event = budget_event;
    }

    public int getId_transport() {
        return id_transport;
    }

    public void setId_transport(int id_transport) {
        this.id_transport = id_transport;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_cat_event() {
        return id_cat_event;
    }

    public void setId_cat_event(int id_cat_event) {
        this.id_cat_event = id_cat_event;
    }

    @Override
    public String toString() {
        return "evenement{" + "id_event=" + id_event + ", nom_event=" + nom_event + ", description_event=" + description_event + ", date_debut_event=" + date_debut_event + ", date_fin_event=" + date_fin_event + ", lieu_event=" + lieu_event + ", budget_event=" + budget_event + ", id_transport=" + id_transport + ", image=" + image + ", id_cat_event=" + id_cat_event + '}';
    }

   
    }
  
    
    
    
    
    
    
    
