/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.pijava.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import edu.pijava.utils.MyConnection;
import edu.pijava.model.Transport;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */
public class CRUDtransport /*implements InterfaceServices*/ {

    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();

    /* @Override*/
    public void ajoutertransport(Transport t) {
        try {
            ste = conn.createStatement();
            String req = "Insert into transport values(0,'" + t.getType_transport() + "','" + t.getDate_depart() + "','" + t.getHeure_depart() + "','" + t.getLieu_depart() + "','" + t.getLieu_arriver() + "','" + t.getPrix_transport() + "')";
            ste.executeUpdate(req);
            System.out.println("transport ajouté");
        } catch (SQLException ex) {
            System.out.println("transport non ajouté!!!!");
            System.out.println(ex.getMessage());
        }

    }

    public void modifiertransport(Transport t) {
        try {
            String req = "UPDATE `transport` SET `Id_transport` = '" + t.getId_transport() + "', `Heure_depart` = '" + t.getHeure_depart() + "',`Date_depart` = '" + t.getDate_depart() + "',`Type_transport` = '" + t.getType_transport() + "',`Lieu_depart` = '" + t.getLieu_depart() + "',`Lieu_arriver` = '" + t.getLieu_arriver() + "',`Prix_transport` = '" + t.getPrix_transport() + "' WHERE `transport`.`Id_transport` = " + t.getId_transport();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("transport updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimertransport(int Id_transport) {
        try {
            String req = "DELETE FROM `transport` WHERE Id_transport = " + Id_transport;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("transport deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Transport> affichertransport() {
        List<Transport> trans = new ArrayList<Transport>();
        try {
            String req = "SELECT * FROM transport";
            Statement ste = conn.createStatement();
            ResultSet result = ste.executeQuery(req);

            while (result.next()) {
                Transport resultatTransport = new Transport(result.getInt("Id_transport"), result.getString("heure_depart"), result.getString("date_depart"), result.getString("type_transport"), result.getString("lieu_depart"), result.getString("lieu_arriver"), result.getFloat("prix_transport"));
                trans.add(resultatTransport);
            }
            //System.out.println(trans);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return trans;
    }

    public HashMap<String, Integer> getSTatsTypeTrans() {
        // Code pour remplir le tableau associatif
        // ...

        Map<String, Integer> stats = new HashMap<String, Integer>();

        try {
            String req = "SELECT "
                    + "  COUNT(*) AS Voiture,"
                    + "  (SELECT COUNT(*) FROM transport WHERE type_transport = 'Moto') AS Moto,"
                    + "  (SELECT COUNT(*) FROM transport WHERE type_transport = 'Mini bus') AS MiniBus,"
                    + "  (SELECT COUNT(*) FROM transport WHERE type_transport = 'Grand Bus') AS GrandBus,"
                    + "  (SELECT COUNT(*) FROM transport WHERE type_transport = 'Camion') AS Camion "
                    + "FROM transport "
                    + "WHERE type_transport = 'Voiture'";
            Statement ste = conn.createStatement();
            ResultSet result = ste.executeQuery(req);
            result.next();

            /*while (result.next()) {
                Transport resultatTransport = new Transport(result.getInt("Id_transport"), result.getString("heure_depart"), result.getString("date_depart"), result.getString("type_transport"), result.getString("lieu_depart"), result.getString("lieu_arriver"), result.getFloat("prix_transport"));
                trans.add(resultatTransport);
            }*/
            //System.out.println(trans);
            stats.put("Voiture", result.getInt("Voiture"));
            stats.put("Moto", result.getInt("Moto"));
            stats.put("MiniBus", result.getInt("MiniBus"));
            stats.put("GrandBus", result.getInt("GrandBus"));
            stats.put("Camion", result.getInt("Camion"));

            /*     "Moto",
                "Voiture",
                "Mini bus",
                "Grand Bus",
                "Camion"*/
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return (HashMap<String, Integer>) stats;
    }

}