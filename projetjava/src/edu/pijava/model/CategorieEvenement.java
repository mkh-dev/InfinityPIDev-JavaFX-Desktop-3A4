/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.model;

/**
 *
 * @author MALEK-ADMIN
 */
public class CategorieEvenement {
        private int id_cat_event;
        private String cat_event;

    public CategorieEvenement() {
    }
        
        

    public CategorieEvenement(int id_cat_event, String cat_event) {
        this.id_cat_event = id_cat_event;
        this.cat_event = cat_event;
    }

    public CategorieEvenement(String cat_event) {
        this.cat_event = cat_event;
    }

    public int getId_cat_event() {
        return id_cat_event;
    }

    public void setId_cat_event(int id_cat_event) {
        this.id_cat_event = id_cat_event;
    }

    public String getCat_event() {
        return cat_event;
    }

    public void setCat_event(String cat_event) {
        this.cat_event = cat_event;
    }

    @Override
    public String toString() {
        return "CategorieEvenement{" + "cat_event=" + cat_event + '}';
    }

  
        
}
