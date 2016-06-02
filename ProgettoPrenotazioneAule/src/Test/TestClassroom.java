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
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Andrea
 */
public class TestClassroom {

    public static void main(String[] args) {
        Requirements ref1 = new Requirements(280, true, true, true, "none");
        Requirements ra1 = new Requirements(180, false, false, true, "none"); //no blackboard
        Requirements ref2 = new Requirements(250, true, true, false, "none"); //no projector
        Requirements rc5 = new Requirements(20, true, false, true, "none"); //no whiteboard
        
        Classroom ef1 = new Classroom("EF1", ref1);
        Classroom a1 = new Classroom("A1", ra1);
        Classroom ef2 = new Classroom("EF2", ref2);
        Classroom c5 = new Classroom("C5", rc5);
        
        Calendar cal = new GregorianCalendar(2016, 3, 5);
        Date d = cal.getTime();
        
        Requirements req1 = new Requirements(280, true, true, true, "none");
        Requirements req2 = new Requirements(300, true, true, true, "none");
        Requirements req3 = new Requirements(100, true, true, false, "none");
        Requirements req4 = new Requirements(200, true, false, true, "none");
        Requirements req5 = new Requirements(20, true, true, true, "none");
        
        System.out.println(ef1.verifyReservation(req1, d, 9, 11)); //all ok 1
        System.out.println(ef1.verifyReservation(req2, d, 9, 11)); //no capacity -1
        System.out.println(a1.verifyReservation(req3, d, 9, 11)); //no blackboard -2
        System.out.println(ef2.verifyReservation(req4, d, 9, 11)); //no projector -3
        System.out.println(c5.verifyReservation(req5, d, 14, 16)); //no whiteboard -4
        
        Requirements req6 = new Requirements(20, true, false, true, "none");
        System.out.println(c5.verifyReservation(req6, d, 14, 16)); //all ok 1
        
        System.out.println(c5.getResReg().isReserved(d, 14, 16)); //false, classroom is not reserved
        System.out.println(c5.getResReg().makeReservation(d, 14, 16, "-")); //true
        System.out.println(c5.getResReg().isReserved(d, 14, 15)); //true, classroom is reserved
        System.out.println(c5.getResReg().makeReservation(d, 14, 15, "-")); //false
        System.out.println(c5.getResReg().isReserved(d, 15, 16)); //true
        System.out.println(c5.getResReg().makeReservation(d, 15, 16, "-")); //false
        
        System.out.println(c5.verifyReservation(req6, d, 14, 16)); //c5 is reserved 2
        
        Requirements ref4 = new Requirements(280, false, false, false, "none");
        Requirements req7 = new Requirements(200, true, false, false, "none"); //no blackboard -2
        Requirements req8 = new Requirements(200, false, false, true, "none"); //no projector -3
        Requirements req9 = new Requirements(200, false, true, false, "none"); //no whiteboard -4
        Requirements req10 = new Requirements(300, true, true, true, "none"); //no capacity -1
        Classroom ef4 = new Classroom("EF4", ref4);
        System.out.println(ef4.verifyReservation(req7, d, 14, 16));
        System.out.println(ef4.verifyReservation(req8, d, 14, 16));
        System.out.println(ef4.verifyReservation(req9, d, 14, 16));
        System.out.println(ef4.verifyReservation(req10, d, 14, 16));
        
        List<Classroom> classi = new ArrayList<>();
        classi.add(ef1);
        classi.add(ef2);
        classi.add(c5);
        classi.add(a1);
        Collections.sort(classi);
        for (Classroom cl : classi) {
            System.out.println(cl.getName() + " : " + cl.getRequirements().getCapacity());
        }
        System.out.println(ef1.printClassroom());
        System.out.println(c5.printClassroom());
    }
}
