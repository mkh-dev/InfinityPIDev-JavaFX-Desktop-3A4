/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.services;


import edu.pijava.model.Users;
import edu.pijava.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MALEK-ADMIN
 */
public class UserService {
    Connection cnx2;
    public UserService(){
    cnx2= MyConnection.getInstance().getCnx();
}

    

public void ajouterUtilisateur2(Users usr){
    try {
        String requete2="INSERT INTO users (prenom, nom, email, dateNaissance, numTel, userRole, password) VALUES (?, ?, ?, ?, ?,?, ?)";
        try (PreparedStatement pst = cnx2.prepareStatement(requete2)) {
            pst.setString(1,usr.getPrenom());
            pst.setString(2,usr.getNom());
            pst.setString(3,usr.getEmail());
            SimpleDateFormat sdf;
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateNaissance = sdf.format(usr.getDateNaissance());
            pst.setString(4,dateNaissance);
            pst.setString(5, String.valueOf(usr.getNumTel()));
            pst.setString(6,usr.getUserRole());
            pst.setString(7,usr.getPassword());
            pst.executeUpdate();
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }   
}



    
    public List<Users> afficherUtilisateur (){
        List<Users> myList = new ArrayList<>();
        try {
           
            String requete3 ="SELECT * FROM users";
            Statement st = cnx2.createStatement();
            ResultSet rs= st.executeQuery(requete3);
            while(rs.next()){
                Users u = new Users();
                u.setId(rs.getInt(1));
                u.setPrenom(rs.getString("prenom"));
                u.setNom(rs.getString("nom"));
                u.setDateNaissance(rs.getDate("dateNaissance"));
                u.setEmail(rs.getString("email"));
                u.setNumTel(rs.getInt("numTel"));
                u.setUserRole(rs.getString("userRole"));
                u.setPassword(rs.getString("password"));
                myList.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return myList;
        
    }

 public void supprimerUtilisateur(Users utilisateur) {
    try {
        String requete = "DELETE FROM users WHERE id=?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, utilisateur.getId());
        pst.executeUpdate();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}


    
    
    
    public void modifierUtilisateur(Users utilisateur) {
    try {
        String requete = "UPDATE users SET prenom=?, nom=?, email=?, dateNaissance=?, numTel=?, userRole=?, password=? WHERE id=?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setString(1, utilisateur.getPrenom());
        pst.setString(2, utilisateur.getNom());
        pst.setString(3, utilisateur.getEmail());
        pst.setDate(4, new java.sql.Date(utilisateur.getDateNaissance().getTime()));
        pst.setInt(5, utilisateur.getNumTel());
        pst.setString(6, utilisateur.getUserRole());
        pst.setString(7, utilisateur.getPassword());
        pst.setInt(8, utilisateur.getId());
        pst.executeUpdate();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}


    
    
    public Users getUtilisateurParId(int id) {
    Users utilisateur = null;
    try {
        String requete = "SELECT * FROM users WHERE id=?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            utilisateur = new Users(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                    rs.getString("email"), rs.getDate("dateNaissance"), rs.getInt("numTel"), rs.getString("userRole"),rs.getString("password"));
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return utilisateur;
}

}
