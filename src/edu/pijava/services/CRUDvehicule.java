/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.pijava.services;

import edu.pijava.model.Vehicule;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.pijava.utils.MyConnection;

/**
 *
 * @author HP
 */
public class CRUDvehicule {
    
    Statement ste;
Connection conn = MyConnection.getInstance().getConn();
        
    //@Override
    public void ajoutervehicule(Vehicule v) {
    try {
        ste = conn.createStatement();
        String req = "Insert into vehicule values(0,'"+v.getMarque()+"','"+v.getModele()+"','"+v.getImmatriculation()+"','"+v.getDisponibilite()+"')";
        ste.executeUpdate(req);
        System.out.println("vehicule ajouté");
    } catch (SQLException ex) {
            System.out.println("vehicule non ajouté!!!!");    }
    
    
}
    
    
    public void modifiervehicule(Vehicule v) {
        try {
            String req = "UPDATE `vehicule` SET `Id_vehicule` = '" + v.getId_vehicule() + "', `Marque` = '" + v.getMarque() + "',`Modele` = '" + v.getModele() + "',`Immatriculation` = '" + v.getImmatriculation() + "',`Disponibilite` = '" + v.getDisponibilite() + "' WHERE `vehicule`.`Id_vehicule` = " + v.getId_vehicule();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("vehicule updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
    
    
    
    public void supprimervehicule(int Id_vehicule) {
        try {
            String req = "DELETE FROM `vehicule` WHERE Id_vehicule = " + Id_vehicule;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("vehicule deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public List<Vehicule> affichervehicule() {
    List<Vehicule> veh = new ArrayList<Vehicule>();
        try {
        String req = "SELECT * FROM `vehicule`";
        Statement ste = conn.createStatement();
        ResultSet result = ste.executeQuery(req);
        
        while (result.next()) {
            Vehicule resultatVehicule = new Vehicule(result.getInt("Id_vehicule"), result.getString("Marque"),result.getString("Modele"), result.getString("immatriculation"),result.getString("Disponibilite"));
            veh.add(resultatVehicule);
        }
        System.out.println(veh);
      
    } catch (SQLException ex) {
         System.out.println(ex);   
    }
   return veh;
 }
}

