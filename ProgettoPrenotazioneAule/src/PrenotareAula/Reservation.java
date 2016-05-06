/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package PrenotareAula;
import java.util.Date;
 /**
 *
 * @author Fabio
 * The class is used to manage Reservations by using a date and a start and end time.
 */
public class Reservation implements Comparable<Reservation>{
    
    //Class variables
    private static int nextId = 0; //The Id used the next time a new reservation is created
    
    //Instance variables
    private int id = 0;
    private int startHour = 0;
    private int endHour = 0;
    private Date d=null;
        
    /**
     * 
     * @param c The Calendar object describing the date of the reservation
     * @param startHour The integer value representing the Start Time of the Reservation
     * @param endHour The integer value representing the End Time of the Reservation
     */
    public Reservation(Date d, int startHour, int endHour){
        this.id = nextId;
        this.d=d;
        this.startHour = startHour;
        this.endHour = endHour;
        nextId++;    
    }
    
    /**
     * 
     * @return A string representing the Reservation by listing the fields using
     * the following format: <br>
     * Reservation Number: ... <br>
     * Date: dd/mm/yyyy <br>
     * Time: hh:mm-hh:mm.   
    */
    @Override
    public String toString(){
        String s;
        return s = "Reservation number: " + this.id + "\n" +
                   "Date: " + this.d.toString()+                                                       
                   "Time: " + this.startHour + ":00-" + this.endHour + ":00\n";
    }    

    /*
        Getter methods
    */   
    
    /**
     * 
     * @return The integer ID of the reservation 
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * 
     * @return The Calendar object representing the date of the reservation
     */
    public Date getDate(){
        return this.d;
    }
    
    /**
     * 
     * @return The integer value representing the Start Time of the reservation
     */
    public int getStartHour(){
        return this.startHour;
    }

    /**
     * 
     * @return The integer value representing the End Time of the reservation
     */
    public int getEndHour(){
        return this.endHour;
    }
    
    /**
     * 
     * @param res The reservation to be compared by date and time
     * @return An integer number representing the comparison result <br>
     * -1: res is at a later date and time than this reservation <br>
     * +1: res is at an earlier date and time <br>
     *  0: res is at the same date and time <br>
     */
    @Override
    public int compareTo(Reservation res){
        
        int day = this.getDate().compareTo(res.getDate());            
        if (day != 0)    //if the day is different return -1 or 1
            return day;
            else{            //else check the time also
                if (this.getStartHour() < res.getStartHour())
                    return -1;
                else if (this.getStartHour() > res.getStartHour())
                         return 1;
                else
                    return 0;
            }
            
        
    }
        
}
