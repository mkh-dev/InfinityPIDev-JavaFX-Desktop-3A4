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
        setPassword(password);
    }

    public Users(String prenom, String nom, String email, Date dateNaissance, int numTel, String userRole, String password) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        this.userRole = userRole;
        setPassword(password);
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
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        this.password = hashedPassword;
    }

    public String toString() {
        return prenom + " " + nom + ", " + email + ", " + dateNaissance + ", " + numTel + ", " + userRole + ", " + password;
    }
}
