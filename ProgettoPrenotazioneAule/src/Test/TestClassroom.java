/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import PrenotareAula.Classroom;
import PrenotareAula.Requirements;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Andrea
 */
public class TestClassroom {

    public static void main(String[] args) {
        Requirements r = new Requirements(280, true, false, true, "ciao");    
        Classroom ef1 = new Classroom("EF1", r);
        Classroom ef2 = new Classroom("EF2", r);
        Classroom c5 = new Classroom("C5", r);
        Classroom a1 = new Classroom("A1", r);
        
        Calendar cal = new GregorianCalendar(2016, 3, 5);

        ef1.verifyReservation(r, cal, 9, 11); //classroom don't available for reservation
        ef1.verifyReservation(r, cal, 9, 11); //classroom available for reservation
        c5.verifyReservation(r, cal, 14, 16); //classroom don't available for reservation
        c5.verifyReservation(r, cal, 14, 16); //classroom  available for reservation
        
        System.out.println(c5.getResReg().isReserved(cal, 14, 16)); //false, classroom is not reserved
        System.out.println(c5.getResReg().makeReservation(cal, 14, 16)); //true
        System.out.println(c5.getResReg().isReserved(cal, 14, 15)); //true, classroom is reserved
        System.out.println(c5.getResReg().makeReservation(cal, 14, 15)); //false
        System.out.println(c5.getResReg().isReserved(cal, 15, 16)); //true
        System.out.println(c5.getResReg().makeReservation(cal, 15, 16)); //false
        
        List<Classroom> classi = new ArrayList<>();
        classi.add(ef1);
        classi.add(ef2);
        classi.add(c5);
        classi.add(a1);
        Collections.sort(classi);
        for (Classroom cl : classi) {
            System.out.println(cl.getName() + " : " + cl.getRequirements().getCapacity());
        }
    }
}
