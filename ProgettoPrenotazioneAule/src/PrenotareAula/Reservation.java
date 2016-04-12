/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package PrenotareAula;
 //import java.util.Date;
/**
 *
 * @author Fabio
 */
public class Reservation {
    private int id = 0;
    //private Date date = null;
    private int day = 0;
    private int month = 0;
    private int year = 0;
    private int startTime = 0;
    private int endTime = 0;
    private static int nextId = 0; //The Id used the next time a new reservation is created
        
    public Reservation(int day, int month, int year, int startTime, int endTime){
        this.id = nextId;
        this.day = day;
        this.month = month;
        this.year = year;
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
                   "Date: " + this.day + "/" + this.month + "/" + this.year + "\n" + 
                   "Time: " + this.startTime + ":00-" + this.endTime + ":00\n";
    }    

    /*
        Getter methods
    */   
    public int getId(){
        return this.id;
    }
    
    public String getDate(){
        return this.day + "/" + this.month + "/" + this.year;
    }
    
    public String getTime(){
        return this.startTime + ":00-" + this.endTime + ":00";
    }
    
        
}
