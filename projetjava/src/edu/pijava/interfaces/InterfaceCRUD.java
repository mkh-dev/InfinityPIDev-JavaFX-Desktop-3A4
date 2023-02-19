/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pijava.interfaces;

import edu.pijava.model.Facture;
import edu.pijava.model.Reservation;

import java.util.List;

/**
 *
 * @author ouesl
 */
public interface InterfaceCRUD {
    
    /**
     *
     * @param r
     */
    public void ajouterReservation(Reservation r);
    
    public void modifierReservation(Reservation r);

    /**
     *
     * @param f
     */
    public void ajouterFacture(Facture f);

    /**
     *
     * @param numRes
     */
    public void annulerReservation(int numRes);
    public List<Reservation> afficherReservation();

    /**
     *
     * @return
     */
    public List<Facture> afficherFacture();

}
