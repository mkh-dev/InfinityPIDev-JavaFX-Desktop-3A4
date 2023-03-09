/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.services;

import edu.pijava.model.Reservation;
import edu.pijava.interfaces.InterfaceCRUD;
import edu.pijava.model.Facture;
import edu.pijava.model.Reservation2;
import edu.pijava.model.evenement;
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
    public void ajouterReservation2(Reservation r,evenement e) {
        try {
            String req = "INSERT INTO `reservation2`( `idUser`,`nbPlaces`,`nom_event`) VALUES ('" + r.getIdUser() + "','" + r.getNbPlaces() + "','" + e.getNom_event()+ "')";
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
    
    
        
    public void ajouterReservation2(Reservation2 r) {
        try {
            String req = "INSERT INTO `reservation2`( `idUser`,`nbPlaces`,`nomEvent`) VALUES ('" + r.getIdUser() + "','" + r.getNbPlaces() + "','" + r.getIdEvent() + "')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Reservation ajoutée!!!");
        } catch (SQLException ex) {
            System.out.println("Réservation non ajouté");
        }
    }
    public List<Reservation2> afficherReservation2(int idUser) {
                List<Reservation2> list = new ArrayList<>();

    
        try {
            String req = "SELECT reservation2.numRes , reservation2.nbPlaces , reservation2.nom_event , evenement.date_debut_event , evenement.date_fin_event , evenement.lieu_event FROM `evenement` CROSS JOIN reservation2 WHERE reservation2.nom_event=evenement.nom_event AND idUser= " + idUser;
            //SELECT * FROM table1 INNER JOIN table2 ON table1.id = table2.table1_id
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Reservation2 r = new Reservation2();
                r.setNumRes(RS.getInt("numRes"));
                r.setNbPlaces(RS.getInt("nbPlaces"));
                r.setNom_event(RS.getString("nom_event"));

                list.add(r);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } return list;
    }
   /* 
    public List<Reservation> afficherReservation(int idUser, evenement e ) {
       
              List<Reservation> list = new ArrayList<>();


        try {
            String req = "SELECT reservation.numRes , reservation.nbPlaces , evenement.nom_event , evenement.date_debut_event , evenement.date_fin_event , evenement.lieu_event FROM `evenement` CROSS JOIN reservation WHERE reservation.idEvent=evenement.id_event AND idUser= " + idUser;
            //SELECT * FROM table1 INNER JOIN table2 ON table1.id = table2.table1_id
            Statement st = conn.createStatement();
                

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Reservation r = new Reservation();
                r.setNumRes(RS.getInt("numRes"));
                r.setNbPlaces(RS.getInt("nbPlaces"));
                r.setIdEvent(RS.getInt("idEvent"));
                r.setDate_debut_event(RS.getTimestamp("date_debut_event"));
                r.setDate_fin_event(RS.getTimestamp("date_fin_event"));
                r.setDate_debut_event(RS.getString("lieu_event"));


                

                list.add(r);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
 
    }*/

    public List<Reservation> afficherMesReservations(int  idUser) {
      
        List<Reservation> list = new ArrayList<>();
        try {
            String req = "SELECT numRes, nbPlaces,idEvent FROM `reservation` WHERE idUser= " + idUser;
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

    public List<String> afficherMesReservationsTri(int  idUser) {
      
              List<String> list = new ArrayList<>();

        try {
            String req = "SELECT reservation2.numRes , reservation2.nbPlaces , reservation2.nom_event , evenement.date_debut_event , evenement.lieu_event FROM `evenement` CROSS JOIN reservation2 WHERE reservation2.nom_event=evenement.nom_event AND idUser= " +idUser+"  Order BY evenement.date_debut_event " ;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
            String s = new String();
            s=s.concat(RS.getString("numRes"));
            s=s.concat("  ");
            s=s.concat(RS.getString("nbPlaces"));
                        s=s.concat("  ");

            s=s.concat(RS.getString("nom_event"));
                        s=s.concat("  ");

            s=s.concat(RS.getString("date_debut_event"));
                        s=s.concat("  ");

            s=s.concat(RS.getString("lieu_event"));

         
               list.add(s);}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

      return list;
    }
  
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

    @Override
    public void afficherReservation(int idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficherReservation(int idUser, evenement e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



   

}
