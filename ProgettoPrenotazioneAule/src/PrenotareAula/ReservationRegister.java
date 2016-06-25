/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrenotareAula;

import Facade.DbFacadeHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
     * @param d date int yyyy, mm, dd
     * @param startHour Start Time of the Reservation
     * @param endHour   End Time of the Reservation
     * @return false if is free, true if is reserved
     */
    public boolean isReserved( Date d, int startHour, int endHour) {
      for ( Reservation r : res)  {
          if( d.compareTo(r.getDate()) == 0 && // check dates are equals
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
     * @param d Date int yyyy, mm, dd
     * @param startHour Start time of the reservation
     * @param endHour   End time of the reservation
     * @param description Description of the reservation
     * @return true if the reservation is made, false if it isn't
     */
    public boolean makeReservation(Date d, int startHour, int endHour, String description) {
        if(isReserved(d,  startHour, endHour) == false){
          Reservation  newRes = new Reservation(d, startHour, endHour, description);
          res.add(newRes);
          Collections.sort(res);
          DbFacadeHandler.getInstance().writeReservation(newRes, this.name);
          return true;
        }
          else
          return false;
    }
    
    /**
     * this method creates the specified reservation if it is free
     * informations about reservation are taken from the database
     * @param id Id of the reservation
     * @param d Date int yyyy, mm, dd
     * @param startHour Start time of the reservation
     * @param endHour   End time of the reservation
     * @param description Description of the reservation
     * @return true if the reservation is made, false if it isn't
     */
    public boolean makeReservation(int id, Date d, int startHour, int endHour, String description) {
        if(isReserved(d,  startHour, endHour) == false){
          Reservation  newRes = new Reservation(id, d, startHour, endHour, description);
          res.add(newRes);
          Collections.sort(res);
          return true;
        }
          else
          return false;
    }
    
    /** this method prints the content details of the Reservation Register
     * 
     * @return a String with the details of the register
     */
    public String printRegister() {
        String s = "";
        List<Integer> a = new ArrayList();
            for ( Reservation r : res)
                if (a.contains(r.getId()) == false) {
                    s += r.toString();
                    a.add(r.getId());
                }
        a.clear();
        return s;
    }
    /**this method delete an existent Reservation  
     * 
     * @param idReservation the id of a Reservation 
     * @return true if the Reservation is deleted, false if it isn't (the Reservation 
     * doesn't exist)
     */
    
    public boolean deleteReservation(int idReservation) {
        for (int i = 0; i < res.size(); i ++){
            if (idReservation == res.get(i).getId()){
                res.remove(i);
                Collections.sort(res);
                return true;
            }    
                
            
        }
        return false;
    }
    /**
     * this method make weekly Reservation between two dates
     * @param d1 start date int yyyy, mm, dd
     * @param d2 end date int yyyy, mm, dd
     * @param startHour Start time of the reservation
     * @param endHour   End time of the reservation
     * @param description Description of the reservation
     * @return true if there is the happy end, false if something goes wrong
     */
    public boolean makeWeeklyReservation(Date d1, Date d2, int startHour, int endHour, String description) {
        Date d = d1;
        boolean i = false;
        while ( d.getTime() < d2.getTime()){
        if(isReserved(d,  startHour, endHour) == false){
          Reservation  newRes = new Reservation(d, startHour, endHour, description);
          res.add(newRes);
          Collections.sort(res);
          DbFacadeHandler.getInstance().writeReservation(newRes, this.name);
          long s = d.getTime() + (24 * 7 * 60 * 60* 1000);
          d = new Date (s);
          i = true;
        }
          else
                return false;
        }
        return i;
    }
}
