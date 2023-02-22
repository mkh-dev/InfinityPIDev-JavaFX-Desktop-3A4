/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Objects;

/**
 *
 * @author HP
 */
public class Transport {
    int id_transport;
    String date_depart,heure_depart;
    String type_transport,lieu_depart,lieu_arriver;
    Float prix_transport; 
    
    public Transport(int id_transport,String heure_depart,String date_depart,String type_transport,String lieu_depart,String lieu_arriver,Float prix_transport){
    
       this.id_transport=id_transport;
       this.heure_depart=heure_depart;
       this.date_depart=date_depart;
       this.type_transport=type_transport;
       this.lieu_depart=lieu_depart;
       this.lieu_arriver=lieu_arriver;
       this.prix_transport=prix_transport;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id_transport;
        hash = 11 * hash + Objects.hashCode(this.heure_depart);
        hash = 11 * hash + Objects.hashCode(this.date_depart);
        return hash;
    }
    
     @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transport other = (Transport) obj;
        if (this.id_transport != other.id_transport) {
            return false;
        }
        if (!Objects.equals(this.type_transport, other.type_transport)) {
            return false;
        }
        return true;
    }

    public int getId_transport() {
        return id_transport;
    }

    public void setId_transport(int id_transport) {
        this.id_transport = id_transport;
    }

    public String getHeure_depart() {
        return heure_depart;
    }

    public void setHeure_depart(String heure_depart) {
        this.heure_depart = heure_depart;
    }

    public String getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(String date_depart) {
        this.date_depart = date_depart;
    }

    public String getType_transport() {
        return type_transport;
    }

    public void setType_transport(String type_transport) {
        this.type_transport = type_transport;
    }

    public String getLieu_depart() {
        return lieu_depart;
    }

    public void setLieu_depart(String lieu_depart) {
        this.lieu_depart = lieu_depart;
    }

    public String getLieu_arriver() {
        return lieu_arriver;
    }

    public void setLieu_arriver(String lieu_arriver) {
        this.lieu_arriver = lieu_arriver;
    }

    public Float getPrix_transport() {
        return prix_transport;
    }

    public void setPrix_transport(Float prix_transport) {
        this.prix_transport = prix_transport;
    }
}


