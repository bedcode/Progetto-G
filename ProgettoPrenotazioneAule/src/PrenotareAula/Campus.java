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
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import jdk.management.resource.ResourceType;

/**
 *
 * @author Federico
 */
public class Campus {

    private String name;
    List<Classroom> classi = new ArrayList();

    private void askForReservation(int capacity) throws FileNotFoundException, IOException {
        FileReader file = new FileReader("classi.txt");
        BufferedReader in = new BufferedReader(file);
        while (in.ready()) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            Classroom cl = new Classroom(st.nextToken(), Integer.parseInt(st.nextToken()));
            classi.add(cl);
        }

        for (Classroom cl : classi) {
            if (cl.verifyReservation(capacity) == true) {
                if (askUser()==true) {
                cl.getResReg().makeReservation();
                }
                else
                {
                    System.out.println("prenotazione non effettuata");
                }

            }
        }

    }
    
    private boolean askUser() {
        Scanner tastiera =new Scanner(System.in);
        System.out.println("è stata trovata un'aula adatta per la prenotazione confermare? (Y|N)");
        if (tastiera.next().equals("Y")) {
            return true;
        }
        else {
            return  false;
        }
    }

}
