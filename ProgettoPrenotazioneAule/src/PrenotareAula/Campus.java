/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrenotareAula;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Federico
 */
public class Campus {

    private String name;
    List<Classroom> classi;

    public Campus(String name) throws IOException {
        this.name = name;
        classi = new ArrayList();
        updateRegister();
    }

    /**
     * this method is used for asking the campus to make a reservation
     *
     * @param capacity
     * @param ca
     * @param startHour
     * @param endHour
     * @return boolean value
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean askForReservation(Requirements req, Calendar ca, int startHour, int endHour) throws FileNotFoundException, IOException {
        if (this.checkTime(startHour, endHour) == true) {
            for (Classroom cl : classi) {
                if (cl.verifyReservation(req, ca, startHour, endHour) == true) {
                    if (askUser() == true) {
                        cl.getResReg().makeReservation(ca, startHour, endHour);
                        System.out.println("prenotazione effettuata aula: " + cl.getName());
                        return true;
                    } else {
                        System.out.println("prenotazione non effettuata");
                    }

                }
            }
            return false;
        } else {
            System.out.println("errore nell'inserimento dei tempi di inizio e fine prenotazione");
            return false;
        }
    }

    /**
     * this method asks the User if campus has to make the reservation, the user
     * must answer with yes (Y) or no (N)
     *
     * @return boolean value
     */
    public boolean askUser() {
        Scanner tastiera = new Scanner(System.in);
        System.out.println("è stata trovata un'aula adatta per la prenotazione confermare? (Y|N)");
        if (tastiera.next().equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public List<Classroom> getClassi() {
        return classi;
    }

    /**
     * this method is used for updating the classes of the campus
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void updateRegister() throws FileNotFoundException, IOException {
        FileReader file = new FileReader("classi.txt");
        BufferedReader in = new BufferedReader(file);
        while (in.ready()) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            Classroom cl = new Classroom(st.nextToken(), new Requirements(Integer.parseInt(st.nextToken()), Boolean.parseBoolean(st.nextToken()), Boolean.parseBoolean(st.nextToken()), Boolean.parseBoolean(st.nextToken()), st.nextToken()));
            classi.add(cl);
        }

        Collections.sort(classi);
    }

    /**
     * this method check if startHour and endHour are correct parameters
     *
     * @param startHour
     * @param endHour
     * @return
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

}
