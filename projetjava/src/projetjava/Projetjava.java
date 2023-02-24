/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetJava;
import edu.pijava.model.Users;
import edu.pijava.services.UserService;
import edu.pijava.utils.MyConnection;
import edu.pijava.services.ReservationCrud;
import edu.pijava.model.Reservation;
import edu.pijava.model.Facture;
import edu.pijava.services.FactureCrud;

/**
 *
 * @author ouesl
 */
public class Projetjava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyConnection conn = MyConnection.getInstance();
        Reservation r1 = new Reservation(72, 2, 10, 2);
        //Facture f1 = new Facture( 71, 80, 2);
        //Reservation r2 = new Reservation(66, 8, 9, 4);

        ReservationCrud res = new ReservationCrud();

        FactureCrud ress = new FactureCrud();

        //res.ajouterReservation(r1);
        //ress.ajouterFacture(f1);
       res.modifierReservation(r1);
       //System.out.println(res.afficherReservation());
        //ress.supprimerFacture(1);
        //res.annulerReservation(69);
        //System.out.println(res.afficherMesReservations(2));
       // System.out.println(ress.afficherFacture());
       

    }

}
