/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrenotareAula;
import java.util.Date;
import java.text.SimpleDateFormat;
 /**
 *
 * @author Fabio
 * The class is used to manage Reservations by using a date and a start and end time.
 */
public class Reservation implements Comparable<Reservation>{
    
    //Class variables
    private static int nextId; //The ID used the next time a reservation is created
    private static String dateFormat = "dd/MM/yyyy"; //The date format used to display the reservations
    //Instance variables
    private int id;
    private Date date;
    private int startHour;
    private int endHour;
    private String description;      
    
    /**
     * @param date The Date object representing the date of the reservation
     * @param startHour The integer value representing the Start Time of the reservation
     * @param endHour The integer value representing the End Time of the reservation
     */  
    public Reservation(Date date, int startHour, int endHour) {
        this(nextId, date, startHour, endHour, "-");
        nextId++;
    }
    
    /**
     * @param date The Date object representing the date of the reservation
     * @param startHour The integer value representing the Start Time of the reservation
     * @param endHour The integer value representing the End Time of the reservation
     * @param description The string object representing a description of the reservation
     */  
    public Reservation(Date date, int startHour, int endHour, String description) {
        this(nextId, date, startHour, endHour, description);
        nextId++;
    }
    
    /**
     * @param id The integer value representing the ID of the reservation
     * @param date The Date object representing the date of the reservation
     * @param startHour The integer value representing the Start Time of the reservation
     * @param endHour The integer value representing the End Time of the reservation
     * @param description The string object representing a description of the reservation
     */
    public Reservation(int id, Date date, int startHour, int endHour, String description){
        this.id = id;
        this.date = date;
        this.startHour = startHour;
        this.endHour = endHour;
        this.description = description;        
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
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        String s = "Reservation number: " + this.id + "\n" +
                   "Date: " + format.format(this.date) + "\n" +                                                 
                   "Time: " + this.startHour + ":00-" + this.endHour + ":00\n";                   
        
        if(!(this.description.equals("-")))
            s += "Description: " + this.description + "\n\n";
        else
            s += "\n";            
        
        return s;
    }    

    /*
        Getter and setter methods
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
     * @return The Date object representing the date of the reservation
     */
    public Date getDate(){
        return this.date;
    }
    
    /**
     * 
     * @return A string representing the date of the reservation in SQL format    
     */
    public String getDBDate(){                
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);        
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
     * @return The String object representing the description of the reservation
     */
    public String getDescription(){
        return this.description;
    }
    
    /**
     * @param id The new ID to be set
     */
    public void setId(int id){
        this.id = id;
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
    
    /**
     * 
     * @param d The new date format to be used (example: "dd/MM/yyyy")
     */
    public static void setDateFormat(String d){
        dateFormat = d;    
    }
    
    
    
    
        
}


