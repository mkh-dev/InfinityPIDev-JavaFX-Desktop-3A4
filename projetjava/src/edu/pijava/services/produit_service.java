/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.services;

import edu.pijava.model.categorie_prod;
import edu.pijava.model.produit;
import edu.pijava.utils.MyConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rimbs
 */
public class produit_service {
    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
  
    //Mèthode pour afficher produit
    public List<produit> afficherprod() {
       List<produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             produit p = new produit();
             p.setId_prod(RS.getInt("id_prod"));
             p.setDescription(RS.getString("description"));
             p.setNom_prod(RS.getString("nom_prod"));
             p.setPrix(RS.getDouble("prix"));
             p.setQuantite(RS.getInt("quantite"));
             p.setNom_part(RS.getString("nom_part"));
             p.setId_cat(RS.getInt("id_cat"));
             p.setImage(RS.getString("image"));
             // String imagePath = RS.getString("image");
            // Set the image path to the product object
            //p.setImage(imagePath);
           
             


list.add(p);
            }
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
 
     //Ajouter produit 
     public void ajouterprod(produit p) {
        try {
            ste = conn.createStatement();
            String requeteInsert = "INSERT INTO `produit`( `nom_prod`, `description`, `prix`, `quantite`, `nom_part`, `id_cat`,`image`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(requeteInsert);
            pst.setString(1, p.getNom_prod());
            pst.setString(2, p.getDescription());
            pst.setDouble(3,p.getPrix());
            pst.setInt(4,p.getQuantite());
            pst.setString(5, p.getNom_part());
            pst.setInt(6,p.getId_cat());
            pst.setString(7, p.getImage());
            
            
 if (pst.executeUpdate() != 0) {
                System.out.println("Produit added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(produit_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
  
     
       //Mèthode pour supprimer produit
    public boolean supprimerprod(produit p) {

        String reqeute = "delete from produit  where (id_prod = ?) ;";
        try {
            PreparedStatement pst = conn.prepareStatement(reqeute);
            pst.setInt(1, p.getId_prod());
            if (pst.executeUpdate() != 0) {
                System.out.println("produit  supprimé");
            }
            return true;
        } catch (SQLException ex) {
System.out.println(ex.getMessage());

        }

        return false;
    }
    
   
    
    
    public boolean modifierprod(String nom_prod, String description, double prix,int quantite,int id_cat,String image,int id_prod) {

        String requete = "UPDATE produit SET  nom_prod= ? , description= ? ,prix= ? ,quantite= ? ,id_cat= ? ,image= ? where id_prod=?;";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, nom_prod);
            pst.setString(2, description);
            pst.setDouble(3, prix);
            pst.setInt(4, quantite);
            pst.setInt(5, id_cat);
            pst.setString(6, image);
            pst.setInt(7, id_prod);
            
            if (pst.executeUpdate() != 0) {
                System.out.println("produit Updated");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

    public produit findbyid(int id_prod) {
        produit p = new produit();
        try {

            PreparedStatement pre = conn.prepareStatement("Select * from produit  WHERE id_prod=? ");
            pre.setInt(1, id_prod);
            ResultSet RS = pre.executeQuery();
            while (RS.next()) {
                String nom_prod =RS.getString("nom_prod");
                String description=RS.getString("description");
                double prix =RS.getDouble("prix");
                int quantite = RS.getInt("quantite");
                String nom_part =RS.getString("nom_part");
                int id_cat = RS.getInt("id_cat");
                String image =RS.getString("image");
             
             
              p.setNom_prod(nom_prod);
              p.setDescription(description);
              p.setPrix(prix);
              p.setQuantite(quantite);
              p.setNom_part(nom_part);
              p.setId_cat(id_cat);
              p.setImage(image);
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return p;
    }
    
    
    public String getNom_prod(int id_prod) {
        String q = "";

        String requete4 = "select nom_prod from produit where id_prod=?;";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete4);
            pst.setInt(1, id_prod);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                q = rs.getString(1);
            }
        } catch (SQLException ex) {
        }
        return q;
    }
    
    public int findbyImage(String image) {
        int u = 0;
        try {

            PreparedStatement pre = conn.prepareStatement("Select * from produit  WHERE image=? ");
            pre.setString(1, image);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
             
                 u = rs.getInt("id_prod");
             
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

        return u;
    }
    
    public List<produit> trierProduitsParCategorie(String categorie) {
        //Connection conn = getConn();
    List<produit> produits = new ArrayList<>();
    String req = "SELECT * FROM produit WHERE id_cat=?";
    try {
        PreparedStatement pst = conn.prepareStatement(req);
        pst.setString(1, categorie);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            produit p = new produit();
            p.setId_cat(rs.getInt("id prod"));
            p.setNom_prod(rs.getString("nom prod"));
            p.setPrix(rs.getFloat("prix"));
            p.setQuantite(rs.getInt("quantite"));
            p.setNom_part(rs.getString("nom part"));
            produits.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return produits;
}

    public int getTotalProducts() {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    int total = 0;
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT COUNT(*) FROM produit");
        if (rs.next()) {
            total = rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return total;
}

   public int getLowQuantityProducts() {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM produit WHERE quantite <20 ");
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        stmt.close();
        conn.close();
        return count;
    } catch (SQLException e) {
        e.printStackTrace();
        return -1;
    }
}

   
   public List<produit> importer(String filePath) {
    List<produit> produits = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File(filePath))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] fields = line.split(",");

            // Créer un nouvel objet produit à partir des données lues dans le fichier CSV
            String nom_prod = fields[0];
            String description = fields[1];
            double prix = Double.parseDouble(fields[2]);
            int quantite = Integer.parseInt(fields[3]);
            String nom_part = fields[4];
            int id_cat = Integer.parseInt(fields[5]);
            String image = fields[6];

            produit p = new produit(nom_prod, description, prix, quantite, nom_part, id_cat, image);
            produits.add(p);
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return produits;
}

    public void modifierprod(String nom_prod, String description, int prix, int id_cat, String image, int id_prod) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     public boolean modifierprod(String nom_prod, String description, int prix, int quantite, int id_cat, int id_prod) {
          String requete = "UPDATE produit SET  nom_prod= ? , description= ? ,prix= ? ,quantite= ? ,id_cat= ?  where id_prod=?;";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, nom_prod);
            pst.setString(2, description);
            pst.setDouble(3, prix);
            pst.setInt(4, quantite);
            pst.setInt(5, id_cat);
            pst.setInt(6, id_prod);
           
            if (pst.executeUpdate() != 0) {
                System.out.println("produit Updated");
            } else {
                System.out.println("non");
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }


    }


