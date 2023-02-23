/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.model;

/**
 *
 * @author MALEK-ADMIN
 */
public class Reclamations {
    private int id;
    private String prenom;
    private String nom;
    private String email;

    public Reclamations(String prenom, String nom, String email) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }

    public Reclamations(int id, String prenom, String nom, String email) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }

    public Reclamations() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Reclamations{" + "prenom=" + prenom + ", nom=" + nom + ", email=" + email + '}';
    }
    
    
}
