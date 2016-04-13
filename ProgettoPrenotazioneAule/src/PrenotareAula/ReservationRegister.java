/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrenotareAula;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Federico
 */
public class ReservationRegister {
    private String name;
    private List<Reservation> res;

    public ReservationRegister(String name) {
        this.name = name;
        res = new ArrayList<Reservation>();
    }
    public boolean isReserved( Calendar ca, int startTime, int endTime) {
      for ( Reservation r : res)  {
          if( ca.compareTo(r.getCa()) == 0 && 
                  ((startTime >= r.getStartTime() && endTime<=r.getEndTime())|| (startTime <=r.getStartTime() && endTime>=r.getStartTime() &&endTime<=r.getEndTime())
                  || startTime>= r.getStartTime() &&startTime< r.getEndTime() && endTime >= r.getEndTime()))
              return true;
         
      }
       return false;
    }
    
    
    public boolean makeReservation(Calendar ca, int startTime, int endTime) {
        if(isReserved(ca,  startTime, endTime) == false){
          Reservation  newRes = new Reservation(ca, startTime, endTime);
          res.add(newRes);
          return true;
        }
          else
          return false;
    }
    }

