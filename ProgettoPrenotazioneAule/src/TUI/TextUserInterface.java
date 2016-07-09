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
import Utenti.User;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * The class allows to use XClassLive software in textual mode.
 *
 * @author Federico
 */
public class TextUserInterface {

    private static String emailLogin;

    public static void main(String[] args) throws IOException {
        Campus cp = Campus.getInstance();
        System.out.println("Benvenuto in XClassLive, software di prenotazione aule");
        login();
    }

    /**
     * Method for log in XClassLive.
     */
    private static void login() {
        Account a = Account.getInstance();
        int i = 2;
        while (i == 2) {
            System.out.println("Inserire e-mail");
            Scanner in = new Scanner(System.in);
            emailLogin = in.nextLine();
            System.out.println("Inserire password");
            String password = in.nextLine();
            i = a.login(emailLogin, password);
            if (i == 0) {
                try {
                    teacherOptions();
                } catch (Exception ex) {
                    System.out.println("Errore nell'input, si prega di riprovare");
                }
            } else if (i == 1) {
                try {
                    supervisorOptions();
                } catch (Exception ex) {
                    System.out.println("Errore nell'input, si prega di riprovare");
                }
            }
        }
    }

    /**
     * This method allows the user to make a reservation.
     *
     * @param cp an instance of campus
     * @param ca an instance of calendar
     * @throws IOException
     */
    private static void reservation(Campus cp, Calendar ca) throws IOException {
        Scanner tastieraPrenotazione = new Scanner(System.in);
        System.out.println("Inserire la capacità dell'aula che si vuole prenotare");
        int capacity = tastieraPrenotazione.nextInt();
        System.out.println("Inserire le caratteristiche dell'aula che si vuole prenotare");
        System.out.println("Utilizzare il formato true/true/false/null, per indicare lavagna/lucidi/proiettore/requisiti speciali");
        System.out.println("Legenda requisiti speciali: n -> aula normale, pc -> aula computer, ele -> aula materiale elettrico, bio -> aula biologia");
        String requirements = tastieraPrenotazione.next();
        StringTokenizer str = new StringTokenizer(requirements);
        Requirements req = new Requirements(capacity, Boolean.valueOf(str.nextToken("/")), Boolean.valueOf(str.nextToken("/")), Boolean.valueOf(str.nextToken("/")), writeSpecial(str.nextToken("/")));
        System.out.println("Inserire la data in cui si vuole effettuare la prenotazione aaaa/mm/dd");
        String data = tastieraPrenotazione.next();
        StringTokenizer st = new StringTokenizer(data);
        ca.set(Integer.parseInt(st.nextToken("/")), Integer.parseInt(st.nextToken("/")) - 1, Integer.parseInt(st.nextToken("/")), 0, 0, 0);
        Date date = ca.getTime();
        System.out.println("Inserire ora inizio prenotazione");
        int startHour = tastieraPrenotazione.nextInt();
        System.out.println("Inserire ora fine prenotazione");
        int endHour = tastieraPrenotazione.nextInt();
        System.out.println("Inserire la descrizione della prenotazione");
        tastieraPrenotazione.useDelimiter("\n");
        String description = tastieraPrenotazione.next();
        cp.updateReservation();
        List<String> c = cp.askForReservation(req, date, startHour, endHour);
        int risp = askUser(c);
        if (risp >= 0) {
            if (cp.makeReservation(c.get(risp), req, date, startHour, endHour, description)) {
                System.out.println("Prenotazione effettuata");
            }
        } else {
            System.out.println("Prenotazione non effettuata");
        }
    }

    /**
     * This method allows the user to make a semestral reservation.
     *
     * @param cp an instance of campus
     * @param ca an instance of calendar
     * @throws IOException
     */
    private static void semestralReservation(Campus cp, Calendar ca) throws IOException {
        Scanner tastieraPrenotazione = new Scanner(System.in);
        System.out.println("Inserire la capacità dell'aula che si vuole prenotare");
        int capacity = tastieraPrenotazione.nextInt();
        System.out.println("Inserire le caratteristiche dell'aula che si vuole prenotare");
        System.out.println("Utilizzare il formato true/true/false/null, per indicare lavagna/lucidi/proiettore/requisiti speciali");
        System.out.println("Legenda requisiti speciali: n -> aula normale, pc -> aula computer, ele -> aula materiale elettrico, bio -> aula biologia, dis -> disegno");
        String requirements = tastieraPrenotazione.next();
        StringTokenizer str = new StringTokenizer(requirements);
        Requirements req = new Requirements(capacity, Boolean.valueOf(str.nextToken("/")), Boolean.valueOf(str.nextToken("/")), Boolean.valueOf(str.nextToken("/")), writeSpecial(str.nextToken("/")));
        System.out.println("Inserire la data in cui si vuole iniziare la prenotazione aaaa/mm/dd");
        String data = tastieraPrenotazione.next();
        StringTokenizer st = new StringTokenizer(data);
        ca.set(Integer.parseInt(st.nextToken("/")), Integer.parseInt(st.nextToken("/")) - 1, Integer.parseInt(st.nextToken("/")), 0, 0, 0);
        Date startDate = ca.getTime();
        System.out.println("Inserire la data in cui si vuole finire la prenotazione aaaa/mm/dd");
        data = tastieraPrenotazione.next();
        st = new StringTokenizer(data);
        ca.set(Integer.parseInt(st.nextToken("/")), Integer.parseInt(st.nextToken("/")) - 1, Integer.parseInt(st.nextToken("/")), 0, 0, 0);
        Date endDate = ca.getTime();
        System.out.println("Inserire ora inizio prenotazione");
        int startHour = tastieraPrenotazione.nextInt();
        System.out.println("Inserire ora fine prenotazione");
        int endHour = tastieraPrenotazione.nextInt();
        System.out.println("Inserire la descrizione della prenotazione");
        tastieraPrenotazione.useDelimiter("\n");
        String description = tastieraPrenotazione.next();
        cp.updateReservation();
        List<String> c = cp.askForReservation(req, startDate, startHour, endHour);
        int risp = askUser(c);
        if (risp >= 0) {
            if (cp.makeWeeklyReservation(c.get(risp), req, startDate, endDate, startHour, endHour, description)) {
                System.out.println("Prenotazione effettuata");
            }
        } else {
            System.out.println("Prenotazione non effettuata");
        }
    }

    /**
     * This method asks to the user which classroom he wants to reserve.
     *
     * @param c list of string which contains the names of the classrooms that could be reserved.
     * @return index in the list of the classroom that the user wants to
     * reserve, -1 in case of error or if there aren't choices classrooms.
     */
    private static int askUser(List<String> c) {
        int i = 0;
        for (i = 0; !(c.get(i).equalsIgnoreCase("Requisiti Simili")); i++) {
            System.out.println("Si desidera prenotare l'aula " + c.get(i) + " ? (Y|N)");
            Scanner tastiera = new Scanner(System.in);
            if (tastiera.next().equalsIgnoreCase("Y")) {
                return i;
            } else {
                continue;
            }
        }
        i++;
        for (int j = i; j < c.size(); j++) {
            System.out.println("Non è stata trovata un'aula con i requisiti richiesti, tuttavia è possibile prenotare l'aula " + c.get(i) + " che presenta requisiti simili");
            System.out.println("Si desidera prenotare l'aula " + c.get(j) + "? (Y|N)");
            Scanner tastiera = new Scanner(System.in);
            if (tastiera.next().equalsIgnoreCase("Y")) {
                return j;
            } else {
                continue;
            }
        }
        System.out.println("Nessuna aula trovata con i requisiti inseriti");
        return -1;
    }

    /**
     * Method to identify special requirements written by keyboard.
     *
     * @param s string value
     * @return string value: returns special requirements as: computer,
     * materiale elettrico, biologia, disegno, otherwise returns null
     */
    private static String writeSpecial(String s) {
        switch (s) {
            case "n":
                return null;
            case "pc":
                return "computer";
            case "ele":
                return "materiale elettrico";
            case "bio":
                return "biologia";
            case "dis":
                return "disegno";
            default:
                return null;
        }
    }

    /**
     * This method allows the user to delete a reservation.
     *
     * @param cp an instance of campus
     */
    private static void removeReservation(Campus cp) {
        Scanner tastieraPrenotazione = new Scanner(System.in);
        System.out.println("Specificare l'id della prenotazione che si vuole rimuovere");
        int id = tastieraPrenotazione.nextInt();
        cp.updateReservation();
        if (cp.deleteReservation(id) == true) {
            System.out.println("Prenotazione cancellata");
        } else {
            System.out.println("Non è stata trovata una prenotazione con l'id specificato");
        }
    }

    /**
     * After login, a teacher can choose what to do.
     *
     * @throws IOException
     */
    private static void teacherOptions() throws IOException {
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
                    System.out.println(cp.printAllClassroomReservation());
                    break;
                case (3):
                    System.out.println("Inserire email");
                    String email = tastiera.next();
                    System.out.println("Inserire password attuale");
                    String oldPass = tastiera.next();
                    System.out.println("Inserire password nuova");
                    String newPass = tastiera.next();
                    a.setNewPassword(emailLogin, email, oldPass, newPass);
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
     *
     * @throws IOException
     */
    private static void supervisorOptions() throws IOException {
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
            System.out.println("4) Stampare tutte le prenotazioni");
            System.out.println("5) Creare nuovo account Teacher");
            System.out.println("6) Eliminare account Teacher");
            System.out.println("7) Cambiare password");
            System.out.println("8) Uscire dall'applicazione");

            switch (tastiera.nextInt()) {
                case (1):
                    reservation(cp, ca);
                    break;
                case (2):
                    semestralReservation(cp, ca);
                    break;
                case (3):
                    removeReservation(cp);
                    break;
                case (4):
                    cp.updateReservation();
                    System.out.println(cp.printAllClassroomReservation());
                    break;
                case (5):
                    System.out.println("Inserire email Teacher");
                    Teacher t = new Teacher(tastiera.next());
                    User u = a.addNewTeacherAccount(t);
                    if (u != null) {
                        System.out.println("Docente: " + u.getName() + " " + t.getSurname());
                        System.out.println("Password: " + u.getPassword() + "\n");
                    }
                    break;
                case (6):
                    System.out.println("Inserire email");
                    String email = tastiera.next();
                    a.deleteTeacherAccount(email);
                    break;
                case (7):
                    System.out.println("Inserire email");
                    email = tastiera.next();
                    System.out.println("Inserire password attuale");
                    String oldPass = tastiera.next();
                    System.out.println("Inserire password nuova");
                    String newPass = tastiera.next();
                    a.setNewPassword(emailLogin, email, oldPass, newPass);
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
