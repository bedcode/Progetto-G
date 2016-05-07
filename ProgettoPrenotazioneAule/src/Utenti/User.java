/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author Andrea
 */
public abstract class User {

    private String name;
    private String surname;
    private String email;
    private String password = "";
    int i, j;

    public User(String email) {
        this.email = email;
        this.password = genPassword();
        //this.password = "ciao";
        this.name = getName();
        this.surname = getSurname();
    }

    /**
     * This method starting from the e-mail derives the name of the user.
     * @return the name of the user
     */
    public String getName() {
        i = this.email.indexOf(".");
        if (i == -1) {
            System.out.println("Indirizzo e-mail non corretto");
        }
        this.name = this.email.substring(0, i);
        this.name = this.name.substring(0, 1).toUpperCase() + this.name.substring(1, name.length());
        return name;
    }

    /**
     * This method starting from the e-mail derives the surname of the user.
     * @return the surname of the user
     */
    public String getSurname() {
        j = this.email.indexOf("@");
        if (j == -1) {
            System.out.println("Indirizzo e-mail non corretto");
        }
        this.surname = this.email.substring(i + 1, j);
        this.surname = this.surname.substring(0, 1).toUpperCase() + this.surname.substring(1, surname.length());
        return surname;
    }

    /**
     * Method for automatic generation of password.
     * @return a new password only at the first invocation of a user.
     */
    public String genPassword() {
        if (this.password.equals("")) {
            SecureRandom random = new SecureRandom();
            return new BigInteger(40, random).toString(32);
        } else {
            return this.password;
        }
    }

    /**
     * Method for modifying password
     * @param oldPsw old password
     * @param newPsw new password
     * @return the new password only if it respects this requirement: 8 to 20 alphanumeric characters
     */
    public boolean setPassword(String oldPsw, String newPsw) {
        if (this.password.equals(oldPsw)) {
            if (newPsw.matches("((?=.*[0-9])(?=.*[a-zA-Z]).{8,20})")) {
                this.password = newPsw;
                System.out.println("Password aggiornata");
                return true;
            }
            System.out.println("Password non sicura: deve contenere almeno 8 caratteri, tra cui un numero");
            return false;
        } else {
            System.out.println("Password non aggiornata");
            return false;
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        String s = this.name + " " + this.surname + "\nE-mail: " + this.email;
        return s;
    }
}
