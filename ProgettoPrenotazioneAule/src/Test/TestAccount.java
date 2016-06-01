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
        Teacher t2 = new Teacher("andrea.bonandin@gmail.com", "ciaociao123");
        System.out.println(s1.toString());
        System.out.println(s1.getPassword());
        System.out.println(t1.toString());

        Account a = Account.getInstance();
        a.addSuperAccount(s1);
        s1.addTeacherAccount(t1);

        t1.setNewPassword(t1.getPassword(), "nuova");
        System.out.println(t1.getPassword());
        t1.setNewPassword(t1.getPassword(), "Nuova2016");
        System.out.println(t1.getPassword());

        System.out.println(a.login("ederico.alfeo@unipv.it", "")); // 2 account not existing
        System.out.println(a.login("andrea.bonandin@unipv.it", "abc")); // 2 wrong password
        System.out.println(a.login("andrea.bonandin@unipv.it", t1.getPassword())); // 0 teacher
        System.out.println(a.login("federico.alfeo@unipv.it", s1.getPassword())); // 1 supervisor

        while (a.login() == 2) {
            a.login();
        }
    }
}
