/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package art;

import edu.pijava.model.Transport;
import edu.pijava.model.Vehicule;
import java.util.*;
import edu.pijava.services.CRUDtransport;
import edu.pijava.services.CRUDvehicule;
import edu.pijava.utils.MyConnection;

/**
 *
 * @author HP
 */
public class Art {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        MyConnection connexion = MyConnection.getInstance();
        //Transport t = new Transport(2,"119:23:22","2018-10-16","sss","jjj","cvvv",1200f);
        //CRUDtransport crud = new CRUDtransport();
        
         //Vehicule v = new Vehicule(2,"rollsroys","n2022","r12","non");
         //CRUDvehicule crud = new CRUDvehicule();
        
        //teste ajoutertransport
       //crud.ajoutertransport(t);
        
        //teste modifier
        /*t.setId_transport(2);
        t.setPrix_transport(78952f);
        crud.modifiertransport(t);*/
        
        //teste supprimer
       // crud.supprimertransport(2);
        
       //teste affichertransport
       //List<Transport> ListTransport=crud.affichertransport();
       /*/ListTransport.get(1).toString();
        */
       
       //teste ajoutervehicule
       //crud.ajoutervehicule(v);
       
      /* //teste modifiervehicule
        v.setId_vehicule(0);
        v.setModele("c2");
        crud.modifiervehicule(v);*/
      
      /*//teste supprimer
       crud.supprimervehicule(0);*/

    }
}
