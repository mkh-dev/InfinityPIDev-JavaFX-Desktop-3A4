package edu.pijava.services;


import edu.pijava.model.Evenement;
import edu.pijava.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MALEK-ADMIN
 */
public class EvenementService {
    Connection cnx2;
    public EvenementService (){
    cnx2= MyConnection.getInstance().getCnx();
}

    

public void ajouterEvenement (Evenement event){
    try {
        String requete2="INSERT INTO evenement ( id_event, nom_event, description_event, date_debut_event, date_fin_event, lieu_event, budget_event, id_transport,image,id_cat_event,id_prod) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?,?)";
        try (PreparedStatement pst = cnx2.prepareStatement(requete2)) {
            pst.setString(1,event.getNom_event());
            pst.setString(2,event.getDescription_event());
            SimpleDateFormat sdf;
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            String Date_debut_event = sdf.format(event.getDate_debut_event());
            pst.setString(3,Date_debut_event);
            SimpleDateFormat sdf2;
            sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String Date_fin_event = sdf2.format(event.getDate_fin_event());
            pst.setString(4,Date_fin_event);           
            pst.setString(5, String.valueOf(event.getLieu_event()));
            pst.setFloat(6, event.getBudget_event());
            pst.setInt(7, event.getId_transport());
            pst.setString(8,event.getImage());
            pst.setInt(9, event.getId_cat_event());
            pst.setInt(10, event.getId_cat_event());

            pst.executeUpdate();
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }   
}



    
    public List<Evenement> afficherEvenement (){
        List<Evenement> myList = new ArrayList<>();
        try {
           
            String requete3 ="SELECT * FROM evenement";
            Statement st = cnx2.createStatement();
            ResultSet rs= st.executeQuery(requete3);
            while(rs.next()){
                Evenement e = new Evenement();
                e.setId_event(rs.getInt(1));
                e.setNom_event(rs.getString("nom_event"));
                e.setDescription_event(rs.getString("description_event"));
                e.setDate_debut_event(rs.getDate("date_debut_event"));
                e.setDate_fin_event(rs.getDate("date_fin_event"));
                e.setLieu_event(rs.getString("lieu_event"));
                e.setBudget_event(rs.getFloat("budget_event"));
                e.setId_transport(rs.getInt("id_transport"));
                e.setImage(rs.getString("image"));
                e.setId_cat_event(rs.getInt("id_cat_event"));
                e.setId_prod(rs.getInt("id_prod"));
                myList.add(e);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return myList;
        
    }

 public void supprimerEvenement(Evenement event) {
    try {
        String requete = "DELETE FROM evenement WHERE id_event=?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, event.getId_event());
        pst.executeUpdate();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}


    
    
    
  public void modifierEvenement(Evenement event) {
    try {
        String requete = "UPDATE evenement SET nom_event=?, description_event=?, date_debut_event=?, date_fin_event=?, lieu_event=?, budget_event=?, id_transport=?, image=?, id_cat_event=? WHERE id_event=?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setString(1, event.getNom_event());
        pst.setString(2, event.getDescription_event());
        pst.setDate(3, new java.sql.Date(event.getDate_debut_event().getTime()));
        pst.setDate(4, new java.sql.Date(event.getDate_fin_event().getTime()));
        pst.setString(5, event.getLieu_event());
        pst.setFloat(6, event.getBudget_event());
        pst.setInt(7, event.getId_transport());
        pst.setString(8, event.getImage());
        pst.setInt(9, event.getId_cat_event());
        pst.setInt(10, event.getId_event());
        pst.executeUpdate();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}

    
   

public void setConnection(Connection connection) {
    this.cnx2 = connection;
}

   

}
