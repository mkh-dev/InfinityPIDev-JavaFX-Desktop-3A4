/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.model;

/**
 *
 * @author rimbs
 */
public class categorie_prod {
    private int id_cat;
    private String cat_prod;

    public categorie_prod() {
        this.id_cat=0;
        this.cat_prod="";   
    }

    public categorie_prod( String cat_prod) {
     
        this.cat_prod = cat_prod;
    }

    public categorie_prod(int id_cat, String cat_prod) {
        this.id_cat = id_cat;
        this.cat_prod = cat_prod;
    }
    

    public int getId_cat() {
        return id_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public String getCat_prod() {
        return cat_prod;
    }

    public void setCat_prod(String cat_prod) {
        this.cat_prod = cat_prod;
    }

    @Override
    public String toString() {
        return "categorie_prod{" + "id_cat=" + id_cat + ", cat_prod=" + cat_prod + '}';
    }

    
    
    
    
}
