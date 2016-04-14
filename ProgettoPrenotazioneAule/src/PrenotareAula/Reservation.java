/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package PrenotareAula;
 import java.util.Calendar;
 /**
 *
 * @author Fabio
 * The class is used to manage Reservations by using a date and a start and end time.
 */
public class Reservation {
    
    //Class variables
    private static int nextId = 0; //The Id used the next time a new reservation is created
    
    //Instance variables
    private int id = 0;
    private Calendar cal = null;
    private int startTime = 0;
    private int endTime = 0;
        
    /**
     * 
     * @param cal The Calendar object describing the date of the reservation
     * @param startTime The integer value representing the Start Time of the Reservation
     * @param endTime The integer value representing the End Time of the Reservation
     */
    public Reservation(Calendar cal, int startTime, int endTime){
        this.id = nextId;
        this.cal = cal;
        this.startTime = startTime;
        this.endTime = endTime;
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
                   "Date: " + this.cal.get(Calendar.DAY_OF_MONTH) + "/" +
                              this.cal.get(Calendar.MONTH) + "/" + 
                              this.cal.get(Calendar.YEAR) + "\n" +
                   "Time: " + this.startTime + ":00-" + this.endTime + ":00\n";
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
    public Calendar getCa(){
        return this.cal;
    }
    
    /**
     * 
     * @return The integer value representing the Start Time of the reservation
     */
    public int getStartTime(){
        return this.startTime;
    }

    /**
     * 
     * @return The integer value representing the End Time of the reservation
     */
    public int getEndTime(){
        return this.endTime;
    }
            
/*            
    public String getTime(){
        return this.startTime + ":00-" + this.endTime + ":00";
    }
*/    
        
}
