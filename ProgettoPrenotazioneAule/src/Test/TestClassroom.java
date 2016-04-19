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
        Requirements ref1 = new Requirements(280, true, false, true, "none");
        Requirements ref2 = new Requirements(250, true, false, true, "none");
        Requirements rc5 = new Requirements(20, true, false, true, "none");
        Requirements ra1 = new Requirements(180, true, false, true, "none");
        Classroom ef1 = new Classroom("EF1", ref1);
        Classroom ef2 = new Classroom("EF2", ref2);
        Classroom c5 = new Classroom("C5", rc5);
        Classroom a1 = new Classroom("A1", ra1);
        
        Calendar cal = new GregorianCalendar(2016, 3, 5);

        ef1.verifyReservation(ref1, cal, 9, 11); //classroom don't available for reservation
        ef1.verifyReservation(ref1, cal, 9, 11); //classroom available for reservation
        c5.verifyReservation(rc5, cal, 14, 16); //classroom don't available for reservation
        c5.verifyReservation(rc5, cal, 14, 16); //classroom  available for reservation
        
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
