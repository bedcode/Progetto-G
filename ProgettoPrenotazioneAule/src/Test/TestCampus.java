/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import PrenotareAula.Campus;
import PrenotareAula.Requirements;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Federico
 */
public class TestCampus {
     
    public static void main(String[] args) throws IOException {
       Campus cp=Campus.getInstance();
       cp.updateReservation();
       Calendar ca=new GregorianCalendar();
       ca.set(2000, 2, 1);
       Requirements r= new Requirements(200, true, true, true, "none");
       List d = cp.askForReservation(r, ca.getTime(), 12, 14);
       System.out.println(d);
       String s = d.get(0).toString();
       cp.makeReservation(s, r, ca.getTime(), 12, 14, "-");
//       cp.askForReservation(r, ca.getTime(), 12, 14, "-");
//       ca.set(1990, 2, 1);
//       cp.askForReservation(r, ca.getTime(), 12 , 14, "-");
        s = cp.printAllClassroomReservation();
       System.out.println("\n\n\n\n\n\n" +s);
       
    }
    
}
