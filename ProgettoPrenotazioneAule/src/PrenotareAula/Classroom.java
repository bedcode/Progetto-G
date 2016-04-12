/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrenotareAula;

/**
 *
 * @author Federico
 */
public class Classroom implements Comparable{

    String name;
    int capacity;
    ReservationRegister rr;

    public Classroom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        rr = new ReservationRegister(name);
    }

    public ReservationRegister getResReg() {
        return rr;
    }

    @Override
    public int compareTo(Object t) {
        // mettere in ordine le classi da capacità più piccola a più grande
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
