/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TUI;

import PrenotareAula.Campus;
import PrenotareAula.Requirements;
import Utenti.*;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Federico
 */
public class TextUserInterface {

    public static void main(String[] args) throws IOException {
        Account a = Account.getInstance();
        Campus cp = Campus.getInstance();
        System.out.println("benvenuto in XClassLive, software di prenotazione aule, selezionare un opzione:");
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

    public static void prenotazione(Campus cp, Calendar ca) throws IOException {
        Scanner tastieraPrenotazione = new Scanner(System.in);
        System.out.println("inserire la capacità dell'aula che si vuole prenotare");
        int capacity = tastieraPrenotazione.nextInt();
        Requirements req = new Requirements(capacity, true, true, true, null);
        System.out.println("inserire la data in cui si vuole effettuare la prenotazione aaaa/mm/dd");
        String data = tastieraPrenotazione.next();
        StringTokenizer st = new StringTokenizer(data);
        ca.set(Integer.parseInt(st.nextToken("/")), Integer.parseInt(st.nextToken("/")) - 1, Integer.parseInt(st.nextToken("/")));
        System.out.println("inserire ora inizio prenotazione");
        int startHour = tastieraPrenotazione.nextInt();
        System.out.println("inserire ora fine prenotazione");
        int endHour = tastieraPrenotazione.nextInt();
        cp.updateReservation();
        cp.askForReservation(req, ca, startHour, endHour);

    }

    public static void rimuoviPrenotazione(Campus cp) {
        Scanner tastieraPrenotazione = new Scanner(System.in);
        System.out.println("specifica l'id della prenotazione che si vuole rimuovere");
        int id = tastieraPrenotazione.nextInt();
        if (cp.deleteReservation(id) == true) {
            System.out.println("prenotazione cancellata");

        } else {
            System.out.println("non è stata trovata una prenotazione con l'id specificato");
        }
    }

    public static void teacherOptions() throws IOException {
        Campus cp = Campus.getInstance();
        Account a = Account.getInstance();
        Calendar ca = new GregorianCalendar();
        Scanner tastiera = new Scanner(System.in);
        boolean exit = false;
        while (exit == false) {
            System.out.println("1) prenotare un'aula");
            System.out.println("2) stampare tutte le prenotazioni");
            System.out.println("3) cambiare password");
            System.out.println("4) esci");

            switch (tastiera.nextInt()) {
                case (1):
                    prenotazione(cp, ca);
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
                    exit = true;
                    break;
                default:
                    System.out.println("\n selezionare una delle opzioni presenti\n");
                    break;
            }
        }
    }

    public static void supervisorOptions() throws IOException {
        Campus cp = Campus.getInstance();
        Account a = Account.getInstance();
        Calendar ca = new GregorianCalendar();
        Scanner tastiera = new Scanner(System.in);
        boolean exit = false;
        while (exit == false) {
            System.out.println("1) prenotare un'aula");
            System.out.println("2) effettuare una prenotazione per l'intero semestre");
            System.out.println("3) annullare una prenotazione");
            System.out.println("4) modificare una prenotazione");
            System.out.println("5) stampare tutte le prenotazioni");
            System.out.println("6) creare nuovo account Teacher");
            System.out.println("7) cambiare password");
            System.out.println("8) esci");

            switch (tastiera.nextInt()) {
                case (1):
                    prenotazione(cp, ca);
                    break;
                case (2):
                    System.out.println("\nfunzione al momento non disponibile\n");
                    break;
                case (3):
                    rimuoviPrenotazione(cp);
                    break;
                case (4):
                    System.out.println("inserire id classe");
                    int id=tastiera.nextInt();
                    System.out.println("inserire data prenotazione");
                    System.out.println("inserire la nuova data della prenotazione aaaa/mm/dd");
                    String data = tastiera.next();
                    StringTokenizer st = new StringTokenizer(data);
                    ca.set(Integer.parseInt(st.nextToken("/")), Integer.parseInt(st.nextToken("/")) - 1, Integer.parseInt(st.nextToken("/")));
                    System.out.println("inserire la nuova ora d'inizio della prenotazione");
                    int startHour=tastiera.nextInt();
                    System.out.println("inserire la nuova ora di fine prenotazione");
                    int endHour=tastiera.nextInt();
                    cp.editReservation(id, ca.getTime(), startHour, endHour);            
                    break;
                case (5):
                    cp.updateReservation();
                    cp.printAllClassroomReservation();
                    break;
                case (6):
                    System.out.println("Inserire email Teacher");
                    Supervisor s = new Supervisor(tastiera.next());
                    a.addSuperAccount(s);
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
                    exit = true;
                    break;
                default:
                    System.out.println("\n selezionare una delle opzioni presenti\n");
                    break;
            }
        }
    }
}
