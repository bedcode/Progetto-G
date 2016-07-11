/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import PrenotareAula.ReservationRegister;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Federica
 */
public class TestReservationRegister {
    public static void main(String[] args) {
        Calendar c = new GregorianCalendar();
        c.set(2016, 5, 5);
        Date ca = c.getTime();
        c.set(2016, 8, 5);
        Date c1 = c.getTime();
        ReservationRegister r = new ReservationRegister("EF4");
        r.isReserved(ca, 9,11);
        System.out.println(r.isReserved(ca,9,11));//false
        System.out.println(r.makeReservation(ca,9,11,"-")); //true
        System.out.println(r.makeReservation(ca,9,11,"-")); //false
        r.isReserved(ca ,9,11);     
        System.out.println(r.isReserved(ca ,9,11)); //true
        System.out.println(r.makeReservation(ca,11,12,"-")); //true
        System.out.println(r.isReserved(ca ,9,11));     //true
        System.out.println(r.isReserved(ca ,7,10));     //true
        System.out.println(r.makeReservation(ca,7,11,"-"));//false
        System.out.println(r.makeReservation(ca,12,14,"-"));//true
        System.out.println(r.makeReservation(ca,13,17,"-")); //false
        System.out.println(r.makeReservation(ca,9,17,"-"));  //false
        System.out.println(r.printRegister());
        System.out.println(r.deleteReservation(1));
        System.out.println(r.printRegister());
        System.out.println(r.makeWeeklyReservation(ca, c1, 15,17, "-"));  //true
        System.out.println(r.printRegister());
    }
}
