/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CategorieEvenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;

/**
 *
 * @author miguel
 */
public class CRUDCategorieEvenement {

    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();

    //Mèthode pour afficher categorie event
    public List<CategorieEvenement> affichercatevent() {
        List<CategorieEvenement> list = new ArrayList<>();
        try {
            String req = "Select * from categorie_event";
            Statement st = conn.createStatement();
            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                CategorieEvenement ce = new CategorieEvenement();
                ce.setId_cat_event(RS.getInt("id_cat_event"));
                ce.setCat_event(RS.getString("cat_event"));

                list.add(ce);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    //Mèthode pour supprimer event
    public boolean supprimercatevent(CategorieEvenement ce) {

        String reqeute = "delete from categorie_event where (id_cat_event = ?) ;";
        try {
            PreparedStatement pst = conn.prepareStatement(reqeute);
            pst.setInt(1, ce.getId_cat_event());
            if (pst.executeUpdate() != 0) {
                System.out.println("Categorie evenement supprimé");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

        return false;
    }

    public void ajoutercatEvent(CategorieEvenement ce) {
        try {
            ste = conn.createStatement();
            String requeteInsert = "INSERT INTO categorie_event (cat_event) VALUES (?)";
            PreparedStatement pst = conn.prepareStatement(requeteInsert);
            pst.setString(1, ce.getCat_event());

            if (pst.executeUpdate() != 0) {
                System.out.println("Categorie evenement added");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public CategorieEvenement findbyidcat(int id_cat_event) {
        CategorieEvenement ce = new CategorieEvenement();
        try {

            PreparedStatement pre = conn.prepareStatement("Select * from categorie_event  WHERE id_cat_event=? ");
            pre.setInt(1, id_cat_event);
            ResultSet RS = pre.executeQuery();
            while (RS.next()) {

                String cat_event = RS.getString("cat_event");
                ce.setCat_event(cat_event);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return ce;
    }

    public String getnomcat(int id_cat_event) {
        String q = "";

        String requete = "select cat_event from categorie_event where id_cat_event=?;";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete);
            pst.setInt(1, id_cat_event);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                q = rs.getString(1);
            }
        } catch (SQLException ex) {
        }
        return q;
    }

    public boolean UpdatecatEvent(String cat_event, int id_cat_event) {

        String requete = "UPDATE categorie_event SET  cat_event= ? where id_cat_event=?;";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, cat_event);
            pst.setInt(2, id_cat_event);

            if (pst.executeUpdate() != 0) {
                System.out.println("categorie evenement Updated");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }
    
    
    public List<String> ListNomCat() {

        List<String> listC = new ArrayList<String>();
        try {
            String req = "SELECT id_cat_event,cat_event FROM categorie_event";
            Statement ste = conn.createStatement();
            ResultSet result = ste.executeQuery(req);

            while (result.next()) {

                String name = result.getString("cat_event");
                listC.add(name);
            }
            //System.out.println(listC);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return listC;
    }
    
    
    
    public int getCatId(String nom) {
        int id = -1;
        try {
            String req = "SELECT id_cat_event FROM categorie_event where cat_event='" + nom + "'";
            Statement ste = conn.createStatement();
            ResultSet result = ste.executeQuery(req);

            while (result.next()) {

                id = result.getInt("id_cat_event");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return id;
    }
    
    
    
    
}
