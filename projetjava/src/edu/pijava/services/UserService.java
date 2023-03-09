package edu.pijava.services;


import edu.pijava.model.Users;
import edu.pijava.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author MALEK-ADMIN
 */
public class UserService {
    Connection cnx2;
    public UserService(){
    cnx2= MyConnection.getInstance().getCnx();
}

    

public void ajouterUtilisateur2(Users usr){
    try {
        String requete2="INSERT INTO users (prenom, nom, email, dateNaissance, numTel, userRole, password) VALUES (?, ?, ?, ?, ?,?, ?)";
        try (PreparedStatement pst = cnx2.prepareStatement(requete2)) {
            pst.setString(1,usr.getPrenom());
            pst.setString(2,usr.getNom());
            pst.setString(3,usr.getEmail());
            SimpleDateFormat sdf;
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateNaissance = sdf.format(usr.getDateNaissance());
            pst.setString(4,dateNaissance);
            pst.setString(5, String.valueOf(usr.getNumTel()));
            pst.setString(6,usr.getUserRole());
            pst.setString(7, BCrypt.hashpw(usr.getPassword(), BCrypt.gensalt()));
            pst.executeUpdate();
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }   
}



    
    public List<Users> afficherUtilisateur (){
        List<Users> myList = new ArrayList<>();
        try {
           
            String requete3 ="SELECT * FROM users";
            Statement st = cnx2.createStatement();
            ResultSet rs= st.executeQuery(requete3);
            while(rs.next()){
                Users u = new Users();
                u.setId(rs.getInt(1));
                u.setPrenom(rs.getString("prenom"));
                u.setNom(rs.getString("nom"));
                u.setDateNaissance(rs.getDate("dateNaissance"));
                u.setEmail(rs.getString("email"));
                u.setNumTel(rs.getInt("numTel"));
                u.setUserRole(rs.getString("userRole"));
                u.setPassword(rs.getString("password"));
                myList.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return myList;
        
    }

 public void supprimerUtilisateur(Users utilisateur) {
    try {
        String requete = "DELETE FROM users WHERE id=?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, utilisateur.getId());
        pst.executeUpdate();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}


    
    
    
    public void modifierUtilisateur(Users utilisateur) {
    try {
        String requete = "UPDATE users SET prenom=?, nom=?, email=?, dateNaissance=?, numTel=?, userRole=?, password=? WHERE id=?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setString(1, utilisateur.getPrenom());
        pst.setString(2, utilisateur.getNom());
        pst.setString(3, utilisateur.getEmail());
        pst.setDate(4, new java.sql.Date(utilisateur.getDateNaissance().getTime()));
        pst.setInt(5, utilisateur.getNumTel());
        pst.setString(6, utilisateur.getUserRole());
        pst.setString(7, utilisateur.getPassword());
        pst.setInt(8, utilisateur.getId());
        pst.executeUpdate();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}


    
    
    public Users getUtilisateurParId(int id) {
    Users utilisateur = null;
    try {
        String requete = "SELECT * FROM users WHERE id=?";
        PreparedStatement pst = cnx2.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            utilisateur = new Users(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                    rs.getString("email"), rs.getDate("dateNaissance"), rs.getInt("numTel"), rs.getString("userRole"),rs.getString("password"));
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return utilisateur;
}
    
    
  public boolean authenticateUser(String email, String password) {
    try {
        String query = "SELECT * FROM users WHERE email=?";
        PreparedStatement pst = cnx2.prepareStatement(query);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            String hashedPassword = rs.getString("password");
            return BCrypt.checkpw(password, hashedPassword);
        } else {
            return false;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false;
    }
}

   
   public Users getUserByEmail(String email) {
    Users user = null;
    try {
        String query = "SELECT * FROM users WHERE email=?";
        PreparedStatement statement = cnx2.prepareStatement(query);
        statement.setString(1, email);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            user = new Users();
            user.setId(rs.getInt("id"));
            user.setPrenom(rs.getString("prenom"));
            user.setNom(rs.getString("nom"));
            user.setDateNaissance(rs.getDate("dateNaissance"));
            user.setEmail(rs.getString("email"));
            user.setNumTel(rs.getInt("numTel"));
            user.setUserRole(rs.getString("userRole"));
            user.setPassword(rs.getString("password"));
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return user;
}
   
   public List<Users> getAllUsers() {
    List<Users> userList = new ArrayList<>();
    try {
        String query = "SELECT * FROM users";
        Statement stmt = cnx2.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Users user = new Users();
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setDateNaissance(rs.getDate("dateNaissance"));
            userList.add(user);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return userList;
}

public void setConnection(Connection connection) {
    this.cnx2 = connection;
}

   

}