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
 * @author Federica
 * The Class deals with a Register of Reservations
 */
public class ReservationRegister {
    private String name;
    private List<Reservation> res;

    public ReservationRegister(String name) {
        this.name = name;
        res = new ArrayList<Reservation>();
    }
    
    
    /**
     * this method verifies  if the the register is reserved in the date and time specified.
     * @param ca date int dd int mm int yyyy
     * @param startTime Start Time of the Reservation
     * @param endTime   End Time of the Reservation
     * @return false if is free, true if is reserved
     */
    public boolean isReserved( Calendar ca, int startTime, int endTime) {
      for ( Reservation r : res)  {
          if( ca.compareTo(r.getCa()) == 0 && 
                  ((startTime >= r.getStartTime() && endTime<=r.getEndTime())|| (startTime <=r.getStartTime() && endTime>r.getStartTime() &&endTime<=r.getEndTime())
                  || startTime>= r.getStartTime() &&startTime< r.getEndTime() && endTime >= r.getEndTime()))
              return true;
         
      }
       return false;
    }
    
    /**
     * this method creates the specified reservation if it is free
     * @param ca Date int dd int mm int yyyy
     * @param startTime Start time of the reservation
     * @param endTime   End time of the reservation
     * @return true if the reservation is made, false if it isn't
     */
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

