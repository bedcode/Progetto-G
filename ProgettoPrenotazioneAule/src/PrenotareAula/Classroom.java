/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrenotareAula;

import java.util.Calendar;

/**
 *
 * @author Andrea
 */
public class Classroom implements Comparable<Classroom> {

    private String name;
    private Requirements req;
    private ReservationRegister resReg;

    public Classroom(String name, Requirements req) {
        this.name = name;
        this.req = req;
        resReg = new ReservationRegister(name);
    }

    /**
     * This method verifies if a classroom respects the requirements (capacity) and if it is free.
     *
     * @param req the requirements of the classroom
     * @param cal calendar which indicates day, month and year
     * @param startHour start time of the request reservation
     * @param endHour end time of the request reservation
     * @return boolean value
     */
    public boolean verifyReservation(Requirements req, Calendar cal, int startHour, int endHour) {
        if (checkRequirements(req) && resReg.isReserved(cal, startHour, endHour)==false) {
            //System.out.println("Trovata aula libera che soddisfa i requisiti richiesti: " + this.name);
            return true;
        } else {
            //System.out.println("Aula non disponibile");
            return false;
        }
    }

    /**
     * This method check if a classroom has at least the request capacity.
     *
     * @param capacity the capacity of the classroom
     * @return boolean value
     */
    private boolean checkRequirements(Requirements req) {
        if (this.req.getCapacity() >= req.getCapacity()) {
            //System.out.println("Trovata aula con il numero di posti richiesto (" + capacity + ")");
            return true;
        } else {
            //System.out.println("L'aula non ha il numero di posti richiesto (" + capacity + ")");
            return false;
        }
    }

    /**
     * compareTo returns a negative integer, zero, or a positive integer as this
     * capacity is less than, equal to, or greater than the specified capacity
     * of the classroom past as argument.
     *
     * @param cl classroom, its capacity is to be compared
     * @return integer value
     */
    @Override
    public int compareTo(Classroom cl) {
        if (this.req.getCapacity() < cl.getRequirements().getCapacity()) {
            return -1;
        } else if (this.req.getCapacity() == cl.getRequirements().getCapacity()) {
            return 0;
        } else {
            return 1;
        }
    }

    public String getName() {
        return name;
    }

    public Requirements getRequirements() {
        return req;
    }

    public ReservationRegister getResReg() {
        return resReg;
    }
}
