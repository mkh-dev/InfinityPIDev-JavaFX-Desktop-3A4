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
public class categorie_prod_service {
    
    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
  
    
    //Mèthode pour afficher categorie produit
    
    public List<categorie_prod> affichercatprod() {
       List<categorie_prod> list = new ArrayList<>();
        try {
            String req = "Select * from categorie_prod";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             categorie_prod p = new categorie_prod();
             p.setCat_prod(RS.getString("cat_prod"));
             
list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
      //Mèthode pour ajouter produit
     public void ajoutercatprod(categorie_prod c) {
        try {
            ste = conn.createStatement();
            String requeteInsert = "INSERT INTO `categorie_prod`(`cat_prod`) VALUES (?)";
            PreparedStatement pst = conn.prepareStatement(requeteInsert);
            pst.setString(1, c.getCat_prod());
            
 if (pst.executeUpdate() != 0) {
                System.out.println("Catégorie produit added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(categorie_prod_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
    
      
           
           
           
      //Mèthode pour supprimer categorie produit 
    public boolean supprimercatprod(categorie_prod cp) {

        String reqeute = "delete from categorie_prod where (id_cat_prod = ?) ;";
        try {
            PreparedStatement pst = conn.prepareStatement(reqeute);
            pst.setInt(1, cp.getId_cat_prod());
            
            if (pst.executeUpdate() != 0) {
                System.out.println("categorie prod  supprimé");
            }
            return true;
        } catch (SQLException ex) {
System.out.println(ex.getMessage());

        }

        return false;
    }
    
    
  
    
    
    public boolean modifiercatprod(String cat_prod,int id_cat_prod) {

        String requete = "UPDATE categorie_prod SET  cat_prod= ? where id_cat_prod=?;";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, cat_prod);
            pst.setInt(2, id_cat_prod);
            
            if (pst.executeUpdate() != 0) {
                System.out.println("Categorie produit Updated");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }
    
    
    
    
     public categorie_prod findbyid(int id_cat_prod) {
        categorie_prod cp = new categorie_prod();
        try {

            PreparedStatement pre = conn.prepareStatement("Select * from categorie_prod WHERE id_cat_prod=? ");
            pre.setInt(1,id_cat_prod);
            ResultSet RS = pre.executeQuery();
            while (RS.next()) {
                String cat_prod =RS.getString("cat_prod");
                
             
              cp.setCat_prod(cat_prod);
              
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return cp;
    }

     public String getCat_prod(int id_cat_prod) {
        String q = "";

        String requete4 = "select cat_prod from categorie_prod where id_cat_prod=?;";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete4);
            pst.setInt(1, id_cat_prod);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                q = rs.getString(1);
            }
        } catch (SQLException ex) {
        }
        return q;
    }
}