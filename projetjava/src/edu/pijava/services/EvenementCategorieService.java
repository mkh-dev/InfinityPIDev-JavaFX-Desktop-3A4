/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.services;

import edu.pijava.model.CategorieEvenement;
import edu.pijava.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MALEK-ADMIN
 */
public class EvenementCategorieService {
    Connection cnx2;
    public EvenementCategorieService(){
    cnx2= MyConnection.getInstance().getCnx();
}

public void ajouterEvenementCategorie (CategorieEvenement eventCat){
    try {
        String requete2="INSERT INTO categorie_event (id_cat_event,cat_event) VALUES (?, ?)";
        try (PreparedStatement pst = cnx2.prepareStatement(requete2)) {
            pst.setInt(1, eventCat.getId_cat_event());
            pst.setString(2,eventCat.getCat_event());

            pst.executeUpdate();
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }   
}



    
   public List<CategorieEvenement> afficherEvenementCategorie (){
    List<CategorieEvenement> myList = new ArrayList<>();
    try {
        String requete3 ="SELECT * FROM categorie_event";
        Statement st = cnx2.createStatement();
        ResultSet rs= st.executeQuery(requete3);
        while(rs.next()){
            CategorieEvenement c = new CategorieEvenement();
            c.setId_cat_event(rs.getInt(1));
            c.setCat_event(rs.getString("cat_event"));
            myList.add(c);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return myList;
}


 public void supprimerEvenementCategorie(CategorieEvenement eventCat) {
    try {
        String requete = "DELETE FROM categorie_event WHERE id_cat_event=?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, eventCat.getId_cat_event());
        pst.executeUpdate();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}


    
    
    
  public void modifierEvenementCategorie(CategorieEvenement eventCat) {
    try {
        String requete = "UPDATE categorie_event SET cat_event=? WHERE id_cat_event=?";

        PreparedStatement pst = cnx2.prepareStatement(requete);
       pst.setString(1, eventCat.getCat_event());
        pst.setInt(2, eventCat.getId_cat_event());

        pst.executeUpdate();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

    
   

public void setConnection(Connection connection) {
    this.cnx2 = connection;
}

   

}
