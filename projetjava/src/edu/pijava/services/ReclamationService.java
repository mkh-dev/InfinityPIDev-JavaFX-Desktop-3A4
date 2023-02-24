package edu.pijava.services;

import edu.pijava.model.Reclamations;
import edu.pijava.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReclamationService {
    Connection cnx;

public ReclamationService() {
    cnx = MyConnection.getInstance().getCnx();
}

public void ajouterReclamation(Reclamations recl) {
    try {
        String requete = "INSERT INTO reclamations (prenom, nom, email, message) VALUES (?, ?, ?, ?)";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, recl.getPrenom());
        pst.setString(2, recl.getNom());
        pst.setString(3, recl.getEmail());
        pst.setString(4, recl.getMessage());
        pst.executeUpdate();
        System.out.println("Reclamation ajoutée !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

public List<Reclamations> afficherReclamations() {
    List<Reclamations> myList = new ArrayList<>();
    try {
        String requete = "SELECT * FROM reclamations";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Reclamations r = new Reclamations();
            r.setId(rs.getInt(1));
            r.setPrenom(rs.getString("prenom"));
            r.setNom(rs.getString("nom"));
            r.setEmail(rs.getString("email"));
            r.setMessage(rs.getString("message"));
            myList.add(r);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return myList;
}

public void supprimerReclamation(Reclamations recl) {
    try {
        String requete = "DELETE FROM reclamations WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, recl.getId());
        pst.executeUpdate();
        System.out.println("Reclamation supprimée !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

public void modifierReclamation(Reclamations recl) {
    try {
        String requete = "UPDATE reclamations SET prenom=?, nom=?, email=?, message=? WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setString(1, recl.getPrenom());
        pst.setString(2, recl.getNom());
        pst.setString(3, recl.getEmail());
        pst.setString(4, recl.getMessage());
        pst.setInt(5, recl.getId());
        pst.executeUpdate();
        System.out.println("Reclamation modifiée !");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

public Reclamations getReclamationById(int id) {
    Reclamations r = null;
    try {
        String requete = "SELECT * FROM reclamations WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            r = new Reclamations();
            r.setId(rs.getInt(1));
            r.setPrenom(rs.getString("prenom"));
            r.setNom(rs.getString("nom"));
            r.setEmail(rs.getString("email"));
            r.setMessage(rs.getString("message"));
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return r;
}

}
