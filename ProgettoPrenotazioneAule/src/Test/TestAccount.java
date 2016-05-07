/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import Utenti.*;

/**
 *
 * @author Andrea
 */
public class TestAccount {
    public static void main(String[] args) {
        Teacher t1 = new Teacher("andrea.bonandin@unipv.it");
        Supervisor s1 = new Supervisor("federico.alfeo@unipv.it");
        System.out.println(s1.toString());
        System.out.println(s1.getPassword());
        System.out.println(t1.toString());
        
        Account a = Account.getInstance();
        a.addSuperAccount(s1);
        a.addTeacherAccount(t1);
        
        t1.setPassword(t1.getPassword(), "nuova");
        System.out.println(t1.getPassword());
        t1.setPassword(t1.getPassword(), "Nuova2016"); 
        System.out.println(t1.getPassword());
        
//        System.out.println(a.login("ederico.alfeo@unipv.it")); // 2 account not existing        
//        System.out.println(a.login("andrea.bonandin@unipv.it", t1.getPassword())); // 0 
//        System.out.println(a.login("federico.alfeo@unipv.it"/*, s1.getPassword()*/)); // 1
    }
}
