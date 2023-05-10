package edu.pijava.model;

import java.util.Date;
import org.mindrot.jbcrypt.BCrypt;

public class Users {
    private int id;
    private String prenom;
    private String nom;
    private String email;
    private Date dateNaissance;
    private int numTel;
    private String userRole;
    private String password;
    private int is_verified;

    public Users() {
    }

    public Users(int id, String prenom, String nom, String email, Date dateNaissance, int numTel, String userRole, String password, int is_verified) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.userRole = userRole;
        this.password = password;
        this.is_verified = is_verified;
    }

    public Users(String prenom, String nom, String email, Date dateNaissance, int numTel, String userRole, String password, int is_verified) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.userRole = userRole;
        this.password = password;
        this.is_verified = is_verified;
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

    public int getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(int is_verified) {
        this.is_verified = is_verified;
    }

    @Override
    public String toString() {
        return "Users{" + "prenom=" + prenom + ", nom=" + nom + ", email=" + email + ", dateNaissance=" + dateNaissance + ", numTel=" + numTel + ", userRole=" + userRole + ", password=" + password + '}';
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

  
   
    

}

