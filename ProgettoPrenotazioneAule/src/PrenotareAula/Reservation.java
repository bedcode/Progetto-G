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
 */
public class Reservation {
    private int id = 0;
    private Calendar cal = null;
    private int startTime = 0;
    private int endTime = 0;
    private static int nextId = 0; //The Id used the next time a new reservation is created
        
    public Reservation(Calendar cal, int startTime, int endTime){
        this.id = nextId;
        this.cal = cal;
        this.startTime = startTime;
        this.endTime = endTime;
        nextId++;    
    }
    
    /* toString override. Format:
    Reservation number:
    Date:
    Time:
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
    public int getId(){
        return this.id;
    }
    
    public Calendar getCa(){
        return this.cal;
    }
    
    public int getStartTime(){
        return this.startTime;
    }

    public int getEndTime(){
        return this.endTime;
    }
            
/*            
    public String getTime(){
        return this.startTime + ":00-" + this.endTime + ":00";
    }
*/    
        
}
