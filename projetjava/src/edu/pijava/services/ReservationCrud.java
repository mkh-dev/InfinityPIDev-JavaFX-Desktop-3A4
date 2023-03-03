/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.services;

import edu.pijava.model.Reservation;
import edu.pijava.interfaces.InterfaceCRUD;
import edu.pijava.model.Facture;
import edu.pijava.model.evenement;
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
 * @author ouesl
 */
public class ReservationCrud implements InterfaceCRUD {

    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterReservation(Reservation r) {
        try {
            String req = "INSERT INTO `reservation`( `idUser`,`nbPlaces`,`idEvent`) VALUES ('" + r.getIdUser() + "','" + r.getNbPlaces() + "','" + r.getIdEvent() + "')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Reservation ajoutée!!!");
        } catch (SQLException ex) {
            System.out.println("Réservation non ajouté");
        }
    }

    @Override
    public void modifierReservation(Reservation r) {
        try {
            String req = "UPDATE `reservation` SET `idUser` = '" + r.getIdUser() + "', `nbPlaces` = '" + r.getNbPlaces() + "', `idEvent` = '" + r.getIdEvent() + "' WHERE `numRes` = " + r.getNumRes();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Réservation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     *
     * @param numRes
     */
    @Override
    public void annulerReservation(int numRes) {
        try {
                   
            String req = "DELETE FROM `reservation` WHERE numRes = " + numRes;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Réservation annulée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reservation> afficherReservation() {
        List<Reservation> list = new ArrayList<>();
                List<evenement> list1 = new ArrayList<>();

        try {
            String req = "SELECT  FROM evenement CROSS JOIN reservation ON evenement.id_event=reservation.idEvent";
            //SELECT * FROM table1 INNER JOIN table2 ON table1.id = table2.table1_id
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Reservation r = new Reservation();
                                evenement e = new evenement();

                r.setNumRes(RS.getInt("numRes"));
                r.setIdUser(RS.getInt("idUser"));
                r.setNbPlaces(RS.getInt("nbPlaces"));
                r.setIdEvent(RS.getInt("idEvent"));
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
                list1.add(e);
                list.add(r);
                //System.out.println("");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<Reservation> afficherMesReservations(int  idUser) {
      
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "SELECT `numRes`, `nbPlaces`, `idEvent` FROM `reservation` WHERE  `idUser`=" + idUser;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Reservation r = new Reservation();
                r.setNumRes(RS.getInt("numRes"));
                r.setNbPlaces(RS.getInt("nbPlaces"));
                r.setIdEvent(RS.getInt("idEvent"));

                list.add(r);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    /*
  public int montant (int nbPlaces,int prixU){
      Reservation r ;
      int x = r.getNumRes();
       r = getReservationById(r.getNumRes());
      nbPlaces=r.getNbPlaces();
      return prixU*nbPlaces;
      
  }*/
  
  public Reservation getReservationById (int numRes){
      
      
      Reservation r = null;
    try {
        String requete = "SELECT * FROM reservation WHERE numRes=?";
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, numRes);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            r = new Reservation(rs.getInt("numRes"), rs.getInt("idUser"), rs.getInt("nbPlaces"),
                    rs.getInt("idEvent"));
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return r;
      
  }
   

    @Override
    public List<Facture> afficherFacture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public void ajouterFacture(Facture f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
