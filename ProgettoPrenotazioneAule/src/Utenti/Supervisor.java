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
    
    private Account a = Account.getInstance();
    
    public Supervisor(String email) {
        super(email);
    }
    
    public Supervisor(String email, String password){
        super(email, password);
    }
            
    public User addTeacherAccount(Teacher t) {
        return a.addTeacherAccount(t);
    }
    
    @Override
    public String toString(){
        String s = "Xclasslive supervisor: " + super.toString();
        return s;
    }
}
