/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TUI;

import PrenotareAula.Campus;
import PrenotareAula.Requirements;
import Utenti.Account;
import Utenti.Teacher;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The class allows to use XClassLive software in textual mode.
 * 
 * @author Federico
 */
public class TextUserInterface {

    public static void main(String[] args) throws IOException {
        Account a = Account.getInstance();
        Campus cp = Campus.getInstance();
        System.out.println("Benvenuto in XClassLive, software di prenotazione aule");
        int i = 2;
        while (i == 2) {
            i = a.login();
            if (i == 0) {
                teacherOptions();
            } else if (i == 1) {
                supervisorOptions();
            }
        }
    }

    /**
     * This method allows the user to make a reservation.
     * @param cp an instance of campus
     * @param ca an instance of calendar
     * @throws IOException 
     */
    public static void reservation(Campus cp, Calendar ca) throws IOException {
        Scanner tastieraPrenotazione = new Scanner(System.in);
        System.out.println("Inserire la capacità dell'aula che si vuole prenotare");
        int capacity = tastieraPrenotazione.nextInt();
        Requirements req = new Requirements(capacity, true, true, true, null);
        System.out.println("Inserire la data in cui si vuole effettuare la prenotazione aaaa/mm/dd");
        String data = tastieraPrenotazione.next();
        StringTokenizer st = new StringTokenizer(data);
        ca.set(Integer.parseInt(st.nextToken("/")), Integer.parseInt(st.nextToken("/")) - 1, Integer.parseInt(st.nextToken("/")));
        System.out.println("Inserire ora inizio prenotazione");
        int startHour = tastieraPrenotazione.nextInt();
        System.out.println("Inserire ora fine prenotazione");
        int endHour = tastieraPrenotazione.nextInt();
        System.out.println("Inserire la descrizione della prenotazione");
        tastieraPrenotazione.useDelimiter("\n");
        String description = tastieraPrenotazione.next();
        cp.updateReservation();
        System.out.println(description);
        cp.askForReservation(req, ca, startHour, endHour, description);
    }

    /**
     * This method allows the user to delete a reservation.
     * @param cp an instance of campus
     */
    public static void removeReservation(Campus cp) {
        Scanner tastieraPrenotazione = new Scanner(System.in);
        System.out.println("Specificare l'id della prenotazione che si vuole rimuovere");
        int id = tastieraPrenotazione.nextInt();
        if (cp.deleteReservation(id) == true) {
            System.out.println("Prenotazione cancellata");

        } else {
            System.out.println("Non è stata trovata una prenotazione con l'id specificato");
        }
    }

    /**
     * After login, a teacher can choose what to do.
     * @throws IOException 
     */
    public static void teacherOptions() throws IOException {
        Campus cp = Campus.getInstance();
        Account a = Account.getInstance();
        Calendar ca = new GregorianCalendar();
        Scanner tastiera = new Scanner(System.in);
        boolean exit = false;
        while (exit == false) {
            System.out.println("Selezionare un opzione:");
            System.out.println("1) Prenotare un'aula");
            System.out.println("2) Stampare tutte le prenotazioni");
            System.out.println("3) Cambiare password");
            System.out.println("4) Uscire dall'applicazione");

            switch (tastiera.nextInt()) {
                case (1):
                    reservation(cp, ca);
                    break;
                case (2):
                    cp.updateReservation();
                    cp.printAllClassroomReservation();
                    break;
                case (3):
                    System.out.println("Inserire email");
                    String email = tastiera.next();
                    System.out.println("Inserire password attuale");
                    String oldPass = tastiera.next();
                    System.out.println("Inserire password nuova");
                    String newPass = tastiera.next();
                    a.setNewPassword(email, oldPass, newPass);
                    break;
                case (4):
                    cp.closeConnection();
                    exit = true;
                    break;
                default:
                    System.out.println("\nSelezionare una delle opzioni presenti\n");
                    break;
            }
        }
    }

    /**
     * After login, a supervisor can choose what to do.
     * @throws IOException 
     */
    public static void supervisorOptions() throws IOException {
        Campus cp = Campus.getInstance();
        Account a = Account.getInstance();
        Calendar ca = new GregorianCalendar();
        Scanner tastiera = new Scanner(System.in);
        boolean exit = false;
        while (exit == false) {
            System.out.println("Selezionare un opzione:");
            System.out.println("1) Prenotare un'aula");
            System.out.println("2) Effettuare una prenotazione per l'intero semestre");
            System.out.println("3) Annullare una prenotazione");
            System.out.println("4) Modificare una prenotazione");
            System.out.println("5) Stampare tutte le prenotazioni");
            System.out.println("6) Creare nuovo account Teacher");
            System.out.println("7) Cambiare password");
            System.out.println("8) Uscire dall'applicazione");

            switch (tastiera.nextInt()) {
                case (1):
                    reservation(cp, ca);
                    break;
                case (2):
                    System.out.println("\nfunzione al momento non disponibile\n");
                    break;
                case (3):
                    removeReservation(cp);
                    break;
                case (4):
                    System.out.println("Inserire l'id della prenotazione");
                    int id=tastiera.nextInt();
                    System.out.println("Inserire la data della prenotazione");
                    System.out.println("Inserire la nuova data della prenotazione aaaa/mm/dd");
                    String data = tastiera.next();
                    StringTokenizer st = new StringTokenizer(data);
                    ca.set(Integer.parseInt(st.nextToken("/")), Integer.parseInt(st.nextToken("/")) - 1, Integer.parseInt(st.nextToken("/")));
                    System.out.println("Inserire la nuova ora d'inizio della prenotazione");
                    int startHour=tastiera.nextInt();
                    System.out.println("Inserire la nuova ora di fine prenotazione");
                    int endHour=tastiera.nextInt();
                    cp.editReservation(id, ca.getTime(), startHour, endHour);            
                    break;
                case (5):
                    cp.updateReservation();
                    cp.printAllClassroomReservation();
                    break;
                case (6):
                    System.out.println("Inserire email Teacher");
                    Teacher t = new Teacher(tastiera.next());
                    a.addNewTeacherAccount(t);
                    System.out.println("\nOperazione effettuata con successo\n");
                    break;
                case (7):
                    System.out.println("Inserire email");
                    String email = tastiera.next();
                    System.out.println("Inserire password attuale");
                    String oldPass = tastiera.next();
                    System.out.println("Inserire password nuova");
                    String newPass = tastiera.next();
                    a.setNewPassword(email, oldPass, newPass);
                    break;
                case (8):
                    cp.closeConnection();
                    exit = true;
                    break;
                default:
                    System.out.println("\nSelezionare una delle opzioni presenti\n");
                    break;
            }
        }
    }
}
