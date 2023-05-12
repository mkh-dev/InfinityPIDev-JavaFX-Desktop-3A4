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
    private int id_cat_prod;
    private String cat_prod;

    public categorie_prod() {
        this.id_cat_prod=0;
        this.cat_prod="";   
    }

    public categorie_prod( String cat_prod) {
     
        this.cat_prod = cat_prod;
    }

    public categorie_prod(int id_cat_prod, String cat_prod) {
        this.id_cat_prod = id_cat_prod;
        this.cat_prod = cat_prod;
    }
    

    public int getId_cat_prod() {
        return id_cat_prod;
    }

    public void setId_cat_prod(int id_cat_prod) {
        this.id_cat_prod = id_cat_prod;
    }

    public String getCat_prod() {
        return cat_prod;
    }

    public void setCat_prod(String cat_prod) {
        this.cat_prod = cat_prod;
    }

    @Override
    public String toString() {
        return "categorie_prod{" + "id_cat_prod=" + id_cat_prod + ", cat_prod=" + cat_prod + '}';
    }

    
    
    
    
}
