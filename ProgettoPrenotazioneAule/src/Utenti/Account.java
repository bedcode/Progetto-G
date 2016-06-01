/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import Facade.DbFacadeHandler;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class manages accounts and login logic.
 * @author Andrea
 */
public class Account {

    private static Account instance = new Account();
    private Map<String, User> users;

    private Account() {
        users = new LinkedHashMap();
    }

    public static Account getInstance() {
        return instance;
    }

    /**
     * Method for log in the system of reservation.
     *
     * @param email
     * @param password
     * @return 0 if a teacher logs in, 1 if a supervisor logs in, 2 if there are
     * problems
     */
    public int login(String email, String password) {
        User u = checkAccount(email);
        if (u == null) {
            return 2;
        } else if ((u instanceof Teacher) && users.get(email).getPassword().equals(password)) {
            System.out.println("Login effettuato con successo.\n" + u.getEmail());
            return 0;
        } else if ((u instanceof Supervisor) && users.get(email).getPassword().equals(password)) {
            System.out.println("Login effettuato con successo.\n" + u.getEmail());
            return 1;
        } else if (!(users.get(email).getPassword().equals(password))) {
            System.out.println("Login non effettuato: password errata.\nRiprovare.");
            return 2;
        }
        return 2;
    }

    /**
     * Method for log in the system of reservation.
     *
     * @return 0 if a teacher logs in, 1 if a supervisor logs in, 2 if there are
     * problems
     */
    public int login() {
        System.out.println("Inserire e-mail");
        Scanner in = new Scanner(System.in);
        String email = in.nextLine();
        User u = checkAccount(email);
        if (u == null) {
            return 2;
        }
        System.out.println("Inserire password");
        String password = in.nextLine();
        if ((u instanceof Teacher) && users.get(email).getPassword().equals(password)) {
            System.out.println("Login effettuato con successo.\n" + u.getEmail());
            return 0;
        } else if ((u instanceof Supervisor) && users.get(email).getPassword().equals(password)) {
            System.out.println("Login effettuato con successo.\n" + u.getEmail());
            return 1;
        } else if (!(users.get(email).getPassword().equals(password))) {
            System.out.println("Login non effettuato: password errata.\nRiprovare.");
            return 2;
        }
        return 2;
    }

    /**
     * This method check if an account exists.
     *
     * @param email to check
     * @return a user or null
     */
    private User checkAccount(String email) {
        if (users.containsKey(email)) {
            return users.get(email);
        } else {
            System.out.println("Errore: account non esistente.\nRiprovare.");
            return null;
        }
    }

    /**
     * Method to add supervisor account.
     *
     * @param s
     * @return supervisor
     */
    public User addSuperAccount(Supervisor s) {
        users.put(s.getEmail(), s);
        return s;
    }

    /**
     * Method to add teacher account.
     *
     * @param t
     * @return teacher
     */
    public User addTeacherAccount(Teacher t) {
        users.put(t.getEmail(), t);
        return t;
    }

    public User addNewTeacherAccount(Teacher t) {
        users.put(t.getEmail(), t);
        DbFacadeHandler.getInstance().insertAccount(t);
        return t;
    }
    
    public boolean setNewPassword(String email, String oldp, String newp) {
        if (users.get(email) == null) {
            System.out.println("Email inserita non valida");
            return false;
        }
        if (users.get(email) instanceof Teacher)
            return users.get(email).setNewPassword(oldp, newp, 0);
        else
            return users.get(email).setNewPassword(oldp, newp, 1);
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
