/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

/**
 * Teacher is a subclass of User.
 * @author Andrea
 */
public class Teacher extends User {

    public Teacher(String email) {
        super(email);
    }

    public Teacher(String email, String password) {
        super(email, password);
    }

    @Override
    public String toString() {
        String s = "Docente: " + super.toString();
        return s;
    }
}
