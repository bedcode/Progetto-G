/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import PrenotareAula.Reservation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Fabio
 */
public class TestReservation {    
    public static void main(String[] args){
        
        Reservation r1 = new Reservation(new GregorianCalendar(2016,04,27),9,11);
        Reservation r2 = new Reservation(new GregorianCalendar(2016,04,28),11,13);
        Reservation r3 = new Reservation(new GregorianCalendar(2016,05,02),14,16);
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        
        //date field
        Calendar test = r1.getDate();
        System.out.println(test.get(Calendar.DAY_OF_MONTH) + "\n\n");
          
        //compare
        Reservation r4 = new Reservation(new GregorianCalendar(2016,04,27),14,16);
        Reservation r5 = new Reservation(new GregorianCalendar(2016,04,27),14,18);
        System.out.println(r1.compareTo(r2));
        System.out.println(r3.compareTo(r2));
        System.out.println(r4.compareTo(r5));
        
        //list
        System.out.println("\n\n ----Sort test----");
        ArrayList<Reservation> reservations = new ArrayList<>();
        reservations.add(r1);
        reservations.add(r2);
        reservations.add(r3);
        reservations.add(r4);
        reservations.add(r5);
        Collections.sort(reservations);
        for(Reservation r : reservations)
            System.out.println(r);
        
        //Adding date
        System.out.println("Adding days to a date:");
        Reservation r6 = new Reservation(new GregorianCalendar(2016,05,27),14,16);
        System.out.println(r6);
        r6.getDate().add(Calendar.DAY_OF_MONTH, 6);
        System.out.println(r6);
        
        
                
    }

    
}
