/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import PrenotareAula.Campus;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Federico
 */
public class TestCampus {
     
    public static void main(String[] args) throws IOException {
       Campus cp=new Campus("unipv");
       Calendar ca=new GregorianCalendar();
       ca.set(1993, 2, 1);
       cp.askForReservation(100, ca, 14, 16);
       cp.askForReservation(100, ca, 14, 18);
    }
    
}
