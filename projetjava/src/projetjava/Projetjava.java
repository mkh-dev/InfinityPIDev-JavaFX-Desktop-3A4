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
        Reservation r1 = new Reservation(10, 5, 3);
              ReservationCrud res = new ReservationCrud();

        FactureCrud ress = new FactureCrud();
      
         
        Facture f1 = new Facture( 85, 50, 2);
        //Reservation r2 = new Reservation( 8, 9, 3);

  

        //res.ajouterReservation(r1);
             //   res.ajouterReservation(r2);

        ress.ajouterFacture(f1);
       //res.modifierReservation(r1);
       //System.out.println(res.afficherReservation());
        //ress.supprimerFacture(1);
        //res.annulerReservation(69);
        //System.out.println(res.afficherMesReservations(2));
      // System.out.println(ress.afficherFacture());
        System.out.println( res.getReservationById(73));

    }

}
