/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.services;

import edu.pijava.model.categorie_prod;
import edu.pijava.model.produit;
import edu.pijava.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rimbs
 */
public class produit_service {
     Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
  
    //Mèthode pour afficher produit
    public List<produit> afficherprod() {
       List<produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             produit p = new produit();
             p.setId_prod(RS.getInt("id_prod"));
             p.setDescription(RS.getString("description"));
             p.setNom_prod(RS.getString("nom_prod"));
             p.setPrix(RS.getDouble("prix"));
             p.setQuantite(RS.getInt("quantite"));
             p.setNom_part(RS.getString("nom_part"));
             p.setId_cat(RS.getInt("id_cat"));
             p.setImage(RS.getString("image"));
             


list.add(p);
            }
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
 
     //Ajouter produit 
     public void ajouterprod(produit p) {
        try {
            ste = conn.createStatement();
            String requeteInsert = "INSERT INTO `produit`( `nom_prod`, `description`, `prix`, `quantite`, `nom_part`, `id_cat`,`image`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(requeteInsert);
            pst.setString(1, p.getNom_prod());
            pst.setString(2, p.getDescription());
            pst.setDouble(3,p.getPrix());
            pst.setInt(4,p.getQuantite());
            pst.setString(5, p.getNom_part());
            pst.setInt(6,p.getId_cat());
            pst.setString(7, p.getImage());
            
 if (pst.executeUpdate() != 0) {
                System.out.println("Produit added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(produit_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
  
     
       //Mèthode pour supprimer produit
    public boolean supprimerprod(produit p) {

        String reqeute = "delete from produit  where (id_prod = ?) ;";
        try {
            PreparedStatement pst = conn.prepareStatement(reqeute);
            pst.setInt(1, p.getId_prod());
            if (pst.executeUpdate() != 0) {
                System.out.println("produit  supprimé");
            }
            return true;
        } catch (SQLException ex) {
System.out.println(ex.getMessage());

        }

        return false;
    }
    
    
    public boolean modifierprod(String nom_prod, String description, double prix,int id_prod) {

        String requete = "UPDATE produit SET  nom_prod= ? , description= ? ,prix= ? where id_prod=?;";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, nom_prod);
            pst.setString(2, description);
            pst.setDouble(3, prix);
            pst.setInt(4, id_prod);
            
            if (pst.executeUpdate() != 0) {
                System.out.println("produit Updated");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

    public produit findbyid(int id_prod) {
        produit p = new produit();
        try {

            PreparedStatement pre = conn.prepareStatement("Select * from produit  WHERE id_prod=? ");
            pre.setInt(1, id_prod);
            ResultSet RS = pre.executeQuery();
            while (RS.next()) {
                String nom_prod =RS.getString("nom_prod");
                String description=RS.getString("description");
                double prix =RS.getDouble("prix");
                int quantite = RS.getInt("quantite");
                String nom_part =RS.getString("nom_part");
                int id_cat = RS.getInt("id_cat");
                String image =RS.getString("image");
             
             
              p.setNom_prod(nom_prod);
              p.setDescription(description);
              p.setPrix(prix);
              p.setQuantite(quantite);
              p.setNom_part(nom_part);
              p.setId_cat(id_cat);
              p.setImage(image);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return p;
    }
    
    
    public String getNom_prod(int id_prod) {
        String q = "";

        String requete4 = "select nom_prod from produit where id_prod=?;";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete4);
            pst.setInt(1, id_prod);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                q = rs.getString(1);
            }
        } catch (SQLException ex) {
        }
        return q;
    }
    
    public int findbyImage(String image) {
        int u = 0;
        try {

            PreparedStatement pre = conn.prepareStatement("Select * from produit  WHERE image=? ");
            pre.setString(1, image);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
             
                 u = rs.getInt("id_prod");
             
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return u;
    }
}
