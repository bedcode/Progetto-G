/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import PrenotareAula.Reservation;
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
        
        //esempio per accedere ai campi della data
        Calendar test = r1.getCa();
        System.out.println(test.get(Calendar.DAY_OF_MONTH));
    }

    
}
