/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.services;

import edu.pijava.model.Reservation;
import edu.pijava.interfaces.InterfaceCRUD;
import edu.pijava.model.Facture;
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
            String req = "INSERT INTO `reservation`( `numRes`, `idUser`,`nbPlaces`,`idEvent`) VALUES ('" + r.getNumRes() + "','" + r.getIdUser() + "','" + r.getNbPlaces() + "','" + r.getIdEvent() + "')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Reservation ajoutée!!!");
        } catch (SQLException ex) {
            System.out.println("Réservation non ajouté");
        }
    }
     @Override
        public void modifierReservation (Reservation r) {
        try {
            String req = "UPDATE `reservation` SET `idUser` = '" + r.getIdUser() + "', `nbPlaces` = '" + r.getNbPlaces() + "', `idEvent` = '" + r.getIdEvent() +"' WHERE `reservation`.`numRes` = " + r.getNumRes();
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
        try {
            String req = "Select * from reservation";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Reservation r = new Reservation();
             r.setNumRes(RS.getInt("numRes"));
             r.setIdUser(RS.getInt("idUser"));
             r.setNbPlaces(RS.getInt("nbPlaces"));
             r.setIdEvent(RS.getInt("idEvent"));

             list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public void ajouterFacture(Facture f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Facture> afficherFacture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   

}
