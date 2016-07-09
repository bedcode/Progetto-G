/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrenotareAula;

import Facade.DbFacadeHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class manages all the operations of the system.
 * 
 * @author Federico
 */
public class Campus  {

    private static Campus instance = new Campus("unipv");
    private String name;
    private List<Classroom> classi;

    private Campus(String name) {
        this.name = name;
        classi = DbFacadeHandler.getInstance().obtainClassroom();
    }
    
    public static Campus getInstance() {
        return instance;
    }
    
    /**
     * This method reads all the reservations and the maximum ID reservation from the database.
     */
    public void updateReservation() {
        DbFacadeHandler.getInstance().readId();
        DbFacadeHandler.getInstance().updateReservation();
    }
    
    /**
     * This method is used for asking the campus to make a reservation, if there aren't classrooms with the specified requirements available
     * the campus suggests a classroom which has similar requirements.
     * If the user doesn't find a classroom suitable for his needs, he must ask again for a reservation with different parameters.
     * 
     * @param req requirements of a classroom
     * @param d date of the reservation
     * @param startHour start time of the reservation
     * @param endHour end time of the reservation
     * @return list of string which contains the names of the classrooms that could be reserved.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public List askForReservation(Requirements req, Date d, int startHour, int endHour) {
        List<String> ClassroomOk = new ArrayList<>();
        if (this.checkTime(startHour, endHour) == true) {
            for (Classroom cl : classi) {
                if (cl.verifyReservation(req, d, startHour, endHour) == 1) {
                    ClassroomOk.add(cl.getName());
                }
            }
            ClassroomOk.add("Requisiti Simili");
            for (Classroom cl : classi) {
                if (cl.verifyReservation(req, d, startHour, endHour) == -3 || cl.verifyReservation(req, d, startHour, endHour) == -4) {
                    ClassroomOk.add(cl.getName());
                }
            }
        } else {
            System.out.println("errore nell'inserimento dei tempi di inizio e fine prenotazione");
        }
        return ClassroomOk;
    }
    
    /**
     * This method makes a reservation in the specified classroom.
     * 
     * @param name name of the classroom to reserve
     * @param req requirements of a classroom
     * @param d date of the reservation
     * @param startHour start time of the reservation
     * @param endHour end time of the reservation
     * @param description description of the reservation
     * @return boolean value
     */
    public boolean makeReservation(String name, Requirements req, Date d, int startHour, int endHour, String description) {
        for (Classroom cl : classi) {
            if (cl.getName().equalsIgnoreCase(name)) {
                cl.getResReg().makeReservation(d, startHour, endHour, description);
                return true;
            }
        }
        return false;
    }

    /**
     * This method makes a semestral reservation in the specified classroom.
     * 
     * @param name name of the classroom to reserve
     * @param req requirements of a classroom
     * @param startDate initial date of the reservation
     * @param endDate final date of the reservation
     * @param startHour start time of the reservation
     * @param endHour end time of the reservation
     * @param description description of the reservation
     * @return boolean value
     */
    public boolean makeWeeklyReservation(String name, Requirements req, Date startDate, Date endDate, int startHour, int endHour, String description) {
        for (Classroom cl : classi) {
            if (cl.getName().equalsIgnoreCase(name)) {
                cl.getResReg().makeWeeklyReservation(startDate, endDate, startHour, endHour, description);
                return true;
            }
        }
        return false;
    }
    
    /**
     * this method deletes a reservation that was made previously
     * 
     * @param id id of a reservation
     * @return boolean value
     */
    public boolean deleteReservation(int id) {
        for (Classroom cl : classi) {
            if(cl.getResReg().deleteReservation(id)==true) {
                cl.getResReg().deleteReservation(id);
                DbFacadeHandler.getInstance().deleteReservation(id);
                return true;
            }       
        }
        return false;
    }        
    
    /**
     * method for printing all the reservation that was made in all the classroom of the campus
     * @return a String with Reservation made 
     */
    public String printAllClassroomReservation() {
        String s = "";
            for (Classroom cl : classi) {
                s += cl.printClassroom();
                System.out.println(cl.printClassroom());
            }   
            return s;
    }
    
    /**
     * Method for printing all the reservations that were made in a classroom of the campus.
     * 
     * @param cl a classroom
     */
    public void printSingleClassroom(Classroom cl) {
        System.out.println(cl.printClassroom());
    }

    /**
     * this method check if startHour and endHour are correct parameters
     *
     * @param startHour start time of a reservation
     * @param endHour end time of a reservation
     * @return boolean value
     */
    public boolean checkTime(int startHour, int endHour) {
        if (endHour <= startHour) {
            return false;
        }
        if (startHour < 9) {
            return false;
        }
        if (endHour > 18) {
            return false;
        }
        return true;
    }
    
    /**
     * This method closes the database connection.
     */
    public void closeConnection() {
        DbFacadeHandler.getInstance().closeConnection();
    }
    
    public List<Classroom> getClassi() {
        return classi;
    }
}
