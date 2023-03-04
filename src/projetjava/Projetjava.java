package projetjava;

import edu.pijava.model.Facture;
import edu.pijava.model.Reservation;
import edu.pijava.model.Users;
import edu.pijava.services.UserService;
import edu.pijava.utils.MyConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import edu.pijava.model.Transport;
import edu.pijava.model.Vehicule;
import edu.pijava.services.CRUDtransport;
import edu.pijava.services.CRUDvehicule;
import edu.pijava.services.FactureCrud;
import edu.pijava.services.ReservationCrud;

public class Projetjava {

    public static void main(String[] args) {

        // Obtenir une instance de la classe MyConnection
        MyConnection mc = MyConnection.getInstance();
        MyConnection mc2 = MyConnection.getInstance();
        System.out.println(mc.hashCode() + " - " + mc2.hashCode());
        MyConnection connexion = MyConnection.getInstance();

        // Obtenir une instance de la classe UserService
       /* UserService userService = new UserService();
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           Date dateNaissance = Date sdf.parse("1993-03-23");
        Users usr = new Users("yosra","oues" ,"hhhh" ,dateNaissance,"5555555","Participant" );
        userService.ajouterUtilisateur2(usr);
      /*  // Ajouter un utilisateur
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateNaissance = Date sdf.parse("1993-03-23");
            Users u2 = new Users("Moulin", "Sylvie", "moulin@gmail.com", dateNaissance, "12244554", "Organisateur");
            userService.ajouterUtilisateur2(u2);
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (ParseException ex) {
            System.err.println("Erreur de parsing : " + ex.getMessage());
        }
*/
        // Afficher tous les utilisateurs
       /* System.out.println("Liste des utilisateurs :");
        System.out.println(userService.afficherUtilisateur());

        // Modifier un utilisateur existant
//        Users utilisateurAModifier = userService.getUtilisateurParId(1);
//        if (utilisateurAModifier != null) {
//            utilisateurAModifier.setNom("Poulin");
//            utilisateurAModifier.setPrenom("Eric");
//            utilisateurAModifier.setEmail("eric@gmail.com");
//            utilisateurAModifier.setNumTel(11111111);
//            utilisateurAModifier.setUserRole("Participant");
//            userService.modifierUtilisateur(utilisateurAModifier);
//            System.out.println("Utilisateur modifié avec succès !");
//        } else {
//            System.err.println("Utilisateur non trouvé !");
//        }
        //Afficher tous les utilisateurs après la modification
//        System.out.println("Liste des utilisateurs après la modification :");
//        System.out.println(userService.afficherUtilisateur());
        // Supprimer un utilisateur existant
        Users utilisateurASupprimer = userService.getUtilisateurParId(3);
        if (utilisateurASupprimer != null) {
            userService.supprimerUtilisateur(utilisateurASupprimer);
            System.out.println("Utilisateur supprimé avec succès !");
        } else {
            System.err.println("Utilisateur non trouvé !");
        }

        // Afficher tous les utilisateurs après la suppression
        System.out.println("Liste des utilisateurs après la suppression :");
        System.out.println(userService.afficherUtilisateur());

        Transport t = new Transport(2, "119:23:22", "2018-10-16", "sss", "jjj", "cvvv", 1200f);
        CRUDtransport crud = new CRUDtransport();

        Vehicule v = new Vehicule(2, "rollsroys", "n2022", "r12", "non");
        CRUDvehicule crudt = new CRUDvehicule();

        //teste ajoutertransport
        crud.ajoutertransport(t);

        //teste modifier
        t.setId_transport(2);
        t.setPrix_transport(78952f);
        crud.modifiertransport(t);

        //teste supprimer
        crud.supprimertransport(2);

        //teste affichertransport
        crud.affichertransport();
        //ListTransport.get(1).toString();

        //teste ajoutervehicule
        crudt.ajoutervehicule(v);

        //teste modifiervehicule
        v.setId_vehicule(0);
        v.setModele("c2");
        crudt.modifiervehicule(v);

        //teste supprimer
        crudt.supprimervehicule(0);

        Reservation r1 = new Reservation(10, 8, 5, 2);
        Facture f1 = new Facture(9, 10, 80.5f, "yosra");
        Reservation r2 = new Reservation(66, 8, 9, 4);

        ReservationCrud res = new ReservationCrud();

        FactureCrud ress = new FactureCrud();

        res.ajouterReservation(r1);
        ress.ajouterFacture(f1);
        res.modifierReservation(r1);

        ress.supprimerFacture(1);
        res.annulerReservation(8);
        System.out.println(res.afficherReservation());
        System.out.println(ress.afficherFacture());*/

    }
}
