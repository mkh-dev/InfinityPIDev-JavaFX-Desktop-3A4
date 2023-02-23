/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.services;

import edu.pijava.model.categorie_event;
import edu.pijava.model.evenement;
import edu.pijava.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *

 */
public class evenement_service {
     Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
    
   //Mèthode pour afficher  event
    public List<evenement> afficherevenement() {
       List<evenement> list = new ArrayList<>();
        try {
            String req = "Select * from evenement";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             evenement e = new evenement();
             e.setId_event(RS.getInt("id_event"));
             e.setNom_event(RS.getString("nom_event"));
             e.setDescription_event(RS.getString("description_event"));
             e.setDate_debut_event(RS.getTimestamp("date_debut_event"));
             e.setDate_fin_event(RS.getTimestamp("date_fin_event"));
             e.setLieu_event(RS.getString("lieu_event"));
             e.setBudget_event(RS.getDouble("budget_event"));
             e.setId_transport(RS.getInt("id_transport"));
             e.setImage(RS.getString("image"));
             e.setId_cat_event(RS.getInt("id_cat_event"));


        
list.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
      //Mèthode pour supprimer event
    public boolean supprimerevent(evenement e) {

        String reqeute = "delete from evenement  where (id_event = ?) ;";
        try {
            PreparedStatement pst = conn.prepareStatement(reqeute);
            pst.setInt(1, e.getId_event());
            // pst.setString(2, m.setDescription(reqeute));
            if (pst.executeUpdate() != 0) {
                System.out.println("event supprimé");
            }
            return true;
        } catch (SQLException ex) {
System.out.println(ex.getMessage());

        }

        return false;
    }
    
    
    public void ajouterEvent(evenement e) {
        try {
            ste = conn.createStatement();
            String requeteInsert = "INSERT INTO evenement (nom_event,description_event,date_debut_event,date_fin_event,lieu_event,budget_event,id_transport,image,id_cat_event) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(requeteInsert);
            pst.setString(1, e.getNom_event());
            pst.setString(2, e.getDescription_event());
            pst.setTimestamp(3, (Timestamp) e.getDate_debut_event());
            pst.setTimestamp(4, (Timestamp) e.getDate_fin_event());
            pst.setString(5, e.getLieu_event());
            pst.setDouble(6, e.getBudget_event());
            pst.setInt(7,e.getId_transport());
            pst.setString(8, e.getImage());
            pst.setInt(9,e.getId_cat_event());
     if (   pst.executeUpdate() != 0) {
                System.out.println("Evenement added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(evenement_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public evenement findbyid(int id_event) {
        evenement e = new evenement();
        try {

            PreparedStatement pre = conn.prepareStatement("Select * from evenement  WHERE id_event=? ");
            pre.setInt(1, id_event);
            ResultSet RS = pre.executeQuery();
            while (RS.next()) {
                String nom_event =RS.getString("nom_event");
                String description_event=RS.getString("description_event");
                Timestamp date_debut_event=RS.getTimestamp("date_debut_event");
                Timestamp date_fin_event=RS.getTimestamp("date_fin_event");
                String lieu_event = RS.getString("lieu_event");
                double budget_event =RS.getDouble("budget_event");
                int id_transport = RS.getInt("id_transport");
                String image =RS.getString("image");
                int id_cat_event = RS.getInt("id_cat_event");
              e.setNom_event(nom_event);
              e.setDescription_event(description_event);
              e.setDate_debut_event(date_debut_event);
              e.setDate_fin_event(date_fin_event);
              e.setLieu_event(lieu_event);
              e.setBudget_event(budget_event);
              e.setId_transport(id_transport);
              e.setImage(image);
              e.setId_cat_event(id_cat_event);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return e;
    }
      public String getnomevent(int id_event) {
        String q = "";

        String requete = "select nom_event from evenement where id_event=?;";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete);
            pst.setInt(1, id_event);
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

            PreparedStatement pre = conn.prepareStatement("Select * from evenement  WHERE image=? ");
            pre.setString(1, image);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
             
                 u = rs.getInt("id_event");
             
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return u;
    }
    public boolean UpdateEvent(String nom_event, String description_event, String lieu_event,int id_event) {

        String requete = "UPDATE evenement SET  nom_event= ? , description_event = ? ,lieu_event= ?   where id_event=?;";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, nom_event);
            pst.setString(2, description_event);
            pst.setString(3, lieu_event);
            pst.setInt(4,id_event);

            if (pst.executeUpdate() != 0) {
                System.out.println("evenement Updated");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }
    
    
    
    
    

}
