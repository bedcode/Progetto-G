/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utenti;

/**
 * Supervisor is a subclass of User.
 * This class allows the supervisor to add teacher accounts.
 * @author Andrea
 */
public class Supervisor extends User{
    
    private Account a = Account.getInstance();
    
    public Supervisor(String email) {
        super(email);
    }
    
    public Supervisor(String email, String password){
        super(email, password);
    }
            
    public User addTeacherAccount(Teacher t) {
        return a.addNewTeacherAccount(t);
    }
    
    @Override
    public String toString(){
        String s = "Xclasslive supervisor: " + super.toString();
        return s;
    }
}
