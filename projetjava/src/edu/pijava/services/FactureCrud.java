/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.services;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import edu.pijava.model.Reservation;
import edu.pijava.interfaces.InterfaceCRUD;
import edu.pijava.model.Facture;
import edu.pijava.model.Reservation2;
import edu.pijava.model.evenement;
import edu.pijava.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ouesl
 */
public class FactureCrud implements InterfaceCRUD {

    public static void generateQRcode(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, h, w);
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));

    }
    Connection conn = MyConnection.getInstance().getConn();

    @Override
    public void ajouterFacture(Facture f) {
        try {

            String req = "INSERT INTO `facture`( `numRes`, `netApayer`, `idUser`) VALUES ('" + f.getNumRes() + "','" + f.getNetApayer() + "','" + f.getIdUser() + "')";
            Statement ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Facture ajoutée!!!");
            String path = "C:\\Users\\ouesl\\Downloads\\Qr.png";
            String str = "Facture payee ya yosra ";
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
            //generates QR code with Low level(L) error correction capability  
            hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //invoking the user-defined method that creates the QR code
            generateQRcode(str, path, charset, hashMap, 500, 500);//increase or decrease height and width accodingly   
            System.out.println("QR Code created successfully.");
        } catch (WriterException | IOException ex) {
            Logger.getLogger(FactureCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("Facture non ajoutée");
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
            String req = "SELECT netApayer FROM `facture` where idUser=" + idUser;
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                Facture f = new Facture();

                f.setNetApayer(RS.getInt("netApayer"));
                //f.setIdUser(RS.getInt("idUser"));
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
            String req = "Select *  from facture ";
            Statement st = conn.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
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
    public void afficherReservation(int idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterReservation2(Reservation r, evenement e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficherReservation(int idUser, evenement e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouterReservation2(Reservation2 r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
