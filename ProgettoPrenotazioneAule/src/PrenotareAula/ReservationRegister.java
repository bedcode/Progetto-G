/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrenotareAula;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Federico
 */
public class ReservationRegister {
    String name;
    List res;

    public ReservationRegister(String name) {
        this.name = name;
        res = new ArrayList<Reservation>();
    }
    
    
}
