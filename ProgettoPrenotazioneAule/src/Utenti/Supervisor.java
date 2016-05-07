/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

/**
 *
 * @author Andrea
 */
public class Supervisor extends User{
    
//    private Account a = Account.getInstance();
    
    public Supervisor(String email) {
        super(email);
    }
    
//    public User addTeacherAccount(Teacher t) { 
//        User u = new Teacher(t.getEmail());
//        a.getUsers().put(t.getEmail(), u);
//        return u;
//    }

//    public Account getA() {
//        return a;
//    }
    
    @Override
    public String toString(){
        String s = "Xclasslive supervisor: " + super.toString();
        return s;
    }
}
