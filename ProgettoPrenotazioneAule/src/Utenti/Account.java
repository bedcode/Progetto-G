/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Andrea
 */
public class Account {

    private static Account instance = new Account();
    private Map<String, User> users;

    private Account() {
        users = new LinkedHashMap();
    }

    public static Account getInstance(){
        return instance;
    }
    
    /**
     * Method for log in the system of reservation.
     * There are 3 attempts to log in.
     * @param email
     * @param password
     * @return 0 if a teacher logs in, 1 if a supervisor logs in, 2 if there are problems
     */
    public int login(String email/*, String password*/) {
        User u = checkAccount(email);
        int tentativo = 0;
        while (tentativo < 3) {
            System.out.println("Inserisci password");
            Scanner in = new Scanner(System.in);
            String pass = in.nextLine();
            if (u == null){
                tentativo++;
                return 2;
            } else if ((u instanceof Teacher) && users.get(email).getPassword().equals(pass)){
                tentativo = 3;
                return 0;
            } else if ((u instanceof Supervisor) && users.get(email).getPassword().equals(pass)){
                tentativo = 3;
                return 1;
            } else if (!(users.get(email).getPassword().equals(pass))) {
                tentativo++;
                System.out.println("Login non effettuato: password errata.\nRiprovare.");
                return 2;
            }
        }
        System.out.println("Tentativi di login esauriti");
        return 2;
    }
    
    /**
     * This method check if an account exists.
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
     * @param s
     * @return supervisor
     */
    public User addSuperAccount(Supervisor s) {
        User u = new Supervisor(s.getEmail());
        users.put(s.getEmail(), u);
        return u;
    }

    /**
     * Method to add teacher account.
     * @param t
     * @return teacher
     */
    public User addTeacherAccount(Teacher t) { 
        User u = new Teacher(t.getEmail());
        users.put(t.getEmail(), u);
        return u;
    }
    
    public Map<String, User> getUsers() {
        return users;
    }
}
