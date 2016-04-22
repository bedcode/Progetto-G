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
     * @param c date int dd int mm int yyyy
     * @param startHour Start Time of the Reservation
     * @param endHour   End Time of the Reservation
     * @return false if is free, true if is reserved
     */
    public boolean isReserved( Calendar c, int startHour, int endHour) {
      for ( Reservation r : res)  {
          if( c.compareTo(r.getDate()) == 0 && // check dates are equals
                  ((startHour >= r.getStartHour() && endHour<=r.getEndHour()) // Reservation is in the interval start-end time of the other reservation
                  || (startHour <=r.getStartHour() && endHour>r.getStartHour() && endHour<=r.getEndHour()) // Reservation starts before or at the same time of an other Reservation 
                                                                                                        //  and end before or at the same time
                  || startHour>= r.getStartHour() &&startHour< r.getEndHour() && endHour >= r.getEndHour() //Reservation starts before the end of an other reservation and end after or in the same time of the other
                  || (startHour< r.getStartHour() && endHour>= r.getEndHour()))) // Reservation starts before an other reservation and end after
              
              return true;
         
      }
       return false;
    }
    
    /**
     * this method creates the specified reservation if it is free
     * @param c Date int dd int mm int yyyy
     * @param startHour Start time of the reservation
     * @param endHour   End time of the reservation
     * @return true if the reservation is made, false if it isn't
     */
    public boolean makeReservation(Calendar c, int startHour, int endHour) {
        if(isReserved(c,  startHour, endHour) == false){
          Reservation  newRes = new Reservation(c, startHour, endHour);
          res.add(newRes);
          return true;
        }
          else
          return false;
    }
    }

