/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.model;

import java.util.logging.Logger;

/**
 *
 * @author rimbs
 */
public class produit {
    private int id_prod;
    private String nom_prod;
    private String description;
    private double prix;
    private int quantite;
    private String nom_part;
    private int id_cat_prod;
    private String image;
    private int nb_likes;
    private int  nb_dislikes;
    

    public produit() {
        this.id_prod=0;
        this.nom_prod=""; 
        this.description=""; 
        this.prix=0;
        this.quantite=0;
        this.nom_part="";
        this.id_cat_prod=0;
        this.image="";
        this. nb_likes=0;
        this. nb_dislikes=0;
    }

    public produit( String nom_prod, String description, double prix, int quantite, String nom_part, int id_cat_prod,String image) {
        
        this.nom_prod = nom_prod;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.nom_part = nom_part;
        this.id_cat_prod = id_cat_prod;
        this.image=image;
    }

     public produit(int id_prod, String nom_prod, String description, double prix, int quantite, String nom_part, int id_cat_prod,String image) {
        this.id_prod=id_prod;
        this.nom_prod = nom_prod;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.nom_part = nom_part;
        this.id_cat_prod = id_cat_prod;
        this.image=image;
    }

    public produit(String nom_prod, double prix, int quantite, int id_cat_prod, String description, String nom_part, String image) {
        this.nom_prod = nom_prod;
        this.prix = prix;
        this.quantite = quantite;
        this.id_cat_prod = id_cat_prod;
        this.description = description;
        this.nom_part = nom_part;
        this.image=image;
    }
    
     public produit(String nom_prod, double prix, int quantite, int id_cat_prod, String description, String nom_part, String image,int  nb_likes,int  nb_dislikes) {
        this.nom_prod = nom_prod;
        this.prix = prix;
        this.quantite = quantite;
        this.id_cat_prod = id_cat_prod;
        this.description = description;
        this.nom_part = nom_part;
        this.image=image;
        this.nb_likes= nb_likes;
        this. nb_dislikes= nb_dislikes;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNom_part() {
        return nom_part;
    }

    public void setNom_part(String nom_part) {
        this.nom_part = nom_part;
    }

    public int getId_cat_prod() {
        return id_cat_prod;
    }

    public void setId_cat_prod(int id_cat_prod) {
        this.id_cat_prod = id_cat_prod;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
     public int getNb_likes() {
        return  nb_likes;
    }

    public void setNb_likes(int  nb_likes) {
        this.nb_likes =  nb_likes;
    }
    
     public int getNb_dislikes() {
        return  nb_dislikes;
    }

    public void setNb_dislikes(int nb_dislikes) {
        this. nb_dislikes =  nb_dislikes;
    }

    @Override
public String toString() {
    return "produit{" + "id_prod=" + id_prod + ", nom_prod=" + nom_prod + ", description=" + description + ", prix=" + prix + ", quantite=" + quantite + ", nom_part=" + nom_part + ", id_cat_prod=" + id_cat_prod + ", image=" + image + '}';
}

  
    
    
}
