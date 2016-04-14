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
    private int capacity;
    private ReservationRegister resReg;

    public Classroom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        resReg = new ReservationRegister(name);
    }

    /**
     * This method verifies if a classroom respects the requirements (capacity) and if it is free.
     *
     * @param capacity the capacity of the classroom
     * @param cal calendar which indicates day, month and year
     * @param startTime start time of the request reservation
     * @param endTime end time of the request reservation
     * @return boolean value
     */
    public boolean verifyReservation(int capacity, Calendar cal, int startTime, int endTime) {
        if (checkRequirements(capacity) && resReg.isReserved(cal, startTime, endTime)==false) {
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
    private boolean checkRequirements(int capacity) {
        if (this.capacity >= capacity) {
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
        if (this.capacity < cl.capacity) {
            return -1;
        } else if (this.capacity == cl.capacity) {
            return 0;
        } else {
            return 1;
        }
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public ReservationRegister getResReg() {
        return resReg;
    }
}
