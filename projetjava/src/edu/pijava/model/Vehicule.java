/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.pijava.model;

import java.util.Objects;

/**
 *
 * @author HP
 */
public class Vehicule {
    int id_vehicule;
    String marque,modele,immatriculation,Disponibilite;
    
    public Vehicule(int id_vehicule,String marque,String modele,String immatriculation,String Disponibilite)
    {
        this.id_vehicule=id_vehicule;
        this.marque=marque;
        this.modele=modele;
        this.immatriculation=immatriculation;
        this.Disponibilite=Disponibilite;
    }

    public Vehicule(int i, String string, String toyota, String c8, String ce18656l, String oui) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id_vehicule;
        hash = 11 * hash + Objects.hashCode(this.marque);
        hash = 11 * hash + Objects.hashCode(this.modele);
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
        final Vehicule other = (Vehicule) obj;
        if (this.id_vehicule != other.id_vehicule) {
            return false;
        }
        if (!Objects.equals(this.marque, other.marque)) {
            return false;
        }
        return true;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getDisponibilite() {
        return Disponibilite;
    }

    public void setDisponibilite(String Disponibilite) {
        this.Disponibilite = Disponibilite;
    }
    
}