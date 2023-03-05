/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.model;

import java.util.Date;

/**
 *
 * @author MALEK-ADMIN
 */
public class Users {
    private int id;
    private String prenom;
    private String nom;
    private String email;
    private Date dateNaissance;
    private int numTel;
    private String userRole;
    private String password;

    public Users() {
    }

    public Users(int id, String prenom, String nom, String email, Date dateNaissance, int numTel, String userRole, String password) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.userRole = userRole;
        this.password = password;
    }
    
    

    public Users(String prenom, String nom, String email, Date dateNaissance, int numTel, String userRole, String password) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.userRole = userRole;
        this.password = password;
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

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   public String toString() {
    return prenom + " " + nom + ", " + email + ", " + dateNaissance + ", " + numTel + ", " + userRole + ", " + password;
}
}

