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
 *
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
     * @param email email of a user
     * @param password password of a user
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
     * This method check if an account exists.
     *
     * @param email email to check
     * @return a user or null
     */
    private User checkAccount(String email) {
        DbFacadeHandler.getInstance().obtainAccount();
        if (users.containsKey(email)) {
            return users.get(email);
        } else {
            return null;
        }
    }

    /**
     * Method to add supervisor account in local instance.
     *
     * @param s a supervisor
     * @return supervisor instance
     */
    public User addSuperAccount(Supervisor s) {
        users.put(s.getEmail(), s);
        return s;
    }

    /**
     * Method to add teacher account in local instance.
     *
     * @param t a teacher
     * @return teacher instance
     */
    public User addTeacherAccount(Teacher t) {
        users.put(t.getEmail(), t);
        return t;
    }

    /**
     * Method to add teacher account in local instance and in the database.
     *
     * @param t a teacher
     * @return teacher instance
     */
    public User addNewTeacherAccount(Teacher t) {
       
        if (checkAccount(t.getEmail()) == null) {
            users.put(t.getEmail(), t);
            DbFacadeHandler.getInstance().insertTeacherAccount(t);
            System.out.println("\nOperazione effettuata con successo\n");
            return t;
        } else {
            System.out.println("\nAccount già esistente\n");
            return null;
        }
    }

    /**
     * Method to delete a teacher account from local instance and database.
     *
     * @param email email of a teacher
     * @return 0 if the Teacher is deleted 1 if not
     * 
     */
    public int deleteTeacherAccount(String email) {
        if ((checkAccount(email) != null) && (users.get(email) instanceof Teacher)) {
            users.remove(email);
            DbFacadeHandler.getInstance().deleteTeacherAccount(email);
            System.out.println("\nOperazione effettuata con successo\n");
            return 0;
        } else {
            System.out.println("\nOperazione non riuscita\n");
            return 1;
        }
    }

    /**
     * This method sets a new password. A user can modify his password only if
     * he writes the email address, the actual password and the new one. The
     * setting is effective only if the new password meets security parameters.
     *
     * @param emailLogin email used for login by a user
     * @param email email of a user
     * @param oldp old password of a user
     * @param newp new password of a user
     * @return boolean value
     */
    public boolean setNewPassword(String emailLogin, String email, String oldp, String newp) {
        if ((emailLogin.equals(email) == false) || (users.get(email) == null)) {
            System.out.println("Email inserita non valida");
            return false;
        }
        if ((emailLogin.equals(email) == true) && (users.get(email) instanceof Teacher)) {
            return users.get(email).setNewPassword(oldp, newp, 0);
        } else if ((emailLogin.equals(email) == true) && (users.get(email) instanceof Supervisor)) {
            return users.get(email).setNewPassword(oldp, newp, 1);
        }
        return false;
    }

    public Map<String, User> getUsers() {
        return users;
    }
}
