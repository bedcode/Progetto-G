/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import PrenotareAula.Reservation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Fabio
 */
public class TestReservation {    
    public static void main(String[] args){
     
        
        Date now = new Date();
        Reservation r1 = new Reservation(now,9,11);
        Reservation r2 = new Reservation(now,14,16);
        Reservation r3 = new Reservation(now,11,13);
        Reservation r4 = new Reservation(now,16,18);
        
        //list
        System.out.println("\n\n ----Sort test----");
        ArrayList<Reservation> reservations = new ArrayList<>();
        reservations.add(r1);
        reservations.add(r2);
        reservations.add(r3);
        reservations.add(r4);
        Collections.sort(reservations);
        for(Reservation r : reservations)
            System.out.println(r);
        
        System.out.println("\n\n ----Date format test----");
        
        System.out.println("Default format");
        for(Reservation r : reservations)
            System.out.println(r);
        
        System.out.println("\n\ndd MMMM yyyy format");
        Reservation.setDateFormat("dd MMMM yyyy");
        for(Reservation r : reservations)
            System.out.println(r);
        
        System.out.println("\n\nEEE dd MMMM yyyy format");
        Reservation.setDateFormat("EEE dd MMMM yyyy");
        for(Reservation r : reservations)
            System.out.println(r);
       
        
        
    }

    
}
