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
public class FactureCrud implements InterfaceCRUD  {
    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
    
    @Override
    public void ajouterFacture(Facture f) {
        try {
            String req = "INSERT INTO `facture` (  `numRes`,`netApayer`,`idUser`) VALUES ('"  + f.getNumRes() + "','" + f.getNetApayer() + "','" + f.getIdUser() + "')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Facture ajoutée!!!");
        } catch (SQLException ex) {
            System.out.println("Facture non ajouté");
        }
    }
        
    public void supprimerFacture(int idFacture) {
        try {
            String req = "DELETE FROM `facture` WHERE idFacture = " + idFacture;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("facture annulée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
    
     public List<Facture> afficherMesFactures(int idUser) {
       List<Facture> list = new ArrayList<>();
       
     try {
            String req = "Select *  from facture where idUser=" + idUser;
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Facture f = new Facture();
             f.setIdFacture(RS.getInt("idFacture"));
             f.setNumRes(RS.getInt("numRes"));
             f.setNetApayer(RS.getInt("netApayer"));
             f.setIdUser(RS.getInt("idUser"));
             //f.setPrenomUser(RS.getString("prenom")); 

             list.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
       
       
       
       
       
       return list;
       
       
       
       
     
       
       
     }  
     public List<Facture> afficherFacture() {
       List<Facture> list = new ArrayList<>();
       
     try {
            String req = "Select *  from facture " ;
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Facture f = new Facture();
             f.setIdFacture(RS.getInt("idFacture"));
             f.setNumRes(RS.getInt("numRes"));
             f.setNetApayer(RS.getInt("netApayer"));
             f.setIdUser(RS.getInt("idUser"));
             //f.setPrenomUser(RS.gtring("prenom")); lezem nsob classet malek 

             list.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
       
       
       
       
       
       return list;
       
       
       
       
     
       
       
     }  
    
    @Override
    public void ajouterReservation(Reservation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifierReservation(Reservation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void annulerReservation(int numRes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> afficherReservation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}
