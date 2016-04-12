/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import PrenotareAula.Reservation;
/**
 *
 * @author Fabio
 */
public class TestReservation {    
    public static void main(String[] args){
        
      
        Reservation r1 = new Reservation(3,5,2016,9,11);
        Reservation r2 = new Reservation(3,5,2016,11,13);
        Reservation r3 = new Reservation(7,5,2016,14,16);
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        
        //System.out.println(r1.getTime());
        
        
    }

    
}
