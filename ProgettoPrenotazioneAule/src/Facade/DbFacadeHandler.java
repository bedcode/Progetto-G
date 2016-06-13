/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import PrenotareAula.Campus;
import PrenotareAula.Classroom;
import PrenotareAula.Requirements;
import PrenotareAula.Reservation;
import Utenti.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Federico
 */
public class DbFacadeHandler {

    private static final DbFacadeHandler instance = new DbFacadeHandler();
    private Connection conn;

    public static DbFacadeHandler getInstance() {
        return instance;
    }

    private DbFacadeHandler() {
        try {
            DriverManager.setLoginTimeout(10);
            conn = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7112944?user=sql7112944&password=QavZjtyIJw");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Method to close the connection to database.
     */
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    // methods which read informations from database
    
    /**
     * Method to read classrooms and their requirements from database.
     * 
     * @return a list of classrooms
     */
    public List<Classroom> obtainClassroom() {

        List<Classroom> classi = new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception E) {
            System.err.println("Non trovo il driver da caricare.");
            E.printStackTrace();
        }

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select nome, capacita, blackboard, whiteboard, projector, laboratory from Classroom join Accessories A on nome=A.classroom");
            while (rs.next()) {
                Requirements re = new Requirements(rs.getInt(2), rs.getBoolean(3), rs.getBoolean(4), rs.getBoolean(5), rs.getString(6));
                Classroom cl = new Classroom(rs.getString(1), re);
                classi.add(cl);
            }
            rs.close();
            stmt.close();
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }

        return classi;
    }

    /**
     * Method to read teacher and supervisor accounts from database.
     * 
     * @return a list of accounts
     */
    public List<Account> obtainAccount() {

        List<Account> accounts = new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception E) {
            System.err.println("Non trovo il driver da caricare.");
            E.printStackTrace();
        }

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Supervisor");
            while (rs.next()) {
                Supervisor s = new Supervisor(rs.getString(1), rs.getString(4));
                Account.getInstance().addSuperAccount(s);
            }
            rs.close();
            ResultSet rs2 = stmt.executeQuery("SELECT * from Teacher");
            while (rs2.next()) {
                Teacher t = new Teacher(rs2.getString(1), rs2.getString(4));
                Account.getInstance().addTeacherAccount(t);
            }
            rs2.close();
            stmt.close();
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }

        return accounts;
    }

    /**
     * Method to read all the reservations from database.
     */
    public void updateReservation() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (Exception E) {
            System.err.println("Non trovo il driver da caricare.");
            E.printStackTrace();
        }

        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT * from Reservation";
            ResultSet rs = stmt.executeQuery(query);
            for (Classroom cl : Campus.getInstance().getClassi()) {
                rs.beforeFirst();
                while (rs.next()) {
                    if (cl.getName().equals(rs.getString(6))) {
                        cl.getResReg().makeReservation(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
                    }
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }
    }
    
    /**
     * Method to read maximum id from database.
     */
    public void readId() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (Exception E) {
            System.err.println("Non trovo il driver da caricare.");
            E.printStackTrace();
        }
        try {
            Statement stmt = conn.createStatement();
            String query = "select max(id) from Reservation";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                Reservation r = new Reservation(id);
            }
            rs.close();
            stmt.close();
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }
    }

    // methods which write, update and delete informations in database
    
    /**
     * This method allows the supervisor to add teacher account in the database.
     * 
     * @param t a teacher instance
     */
    public void insertTeacherAccount(Teacher t) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception E) {
            System.err.println("Non trovo il driver da caricare.");
            E.printStackTrace();
        }
        try {
            Statement stmt = conn.createStatement();
            String query = "insert into Teacher values('" + t.getEmail() + "', '" + t.getName() + "', '" + t.getSurname() + "', '" + t.getPassword() + "')";
            stmt.executeUpdate(query);
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }
    }

    /**
     * This method allows the supervisor to remove teacher account from database.
     * 
     * @param email email of a teacher
     */
    public void deleteTeacherAccount(String email) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception E) {
            System.err.println("Non trovo il driver da caricare.");
            E.printStackTrace();
        }
        try {
            Statement stmt = conn.createStatement();
            String query = "delete from Teacher where mail = '" + email + "'";
            stmt.executeUpdate(query);
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }
    }

    /**
     * This method allows a user to change his password.
     * 
     * @param email email of a user
     * @param newPassword new password of a user
     * @param user type of a user: 0 represents teacher, 1 represents supervisor
     */
    public void changePassword(String email, String newPassword, int user) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception E) {
            System.err.println("Non trovo il driver da caricare.");
            E.printStackTrace();
        }
        String query = null;
        try {
            Statement stmt = conn.createStatement();
            if (user == 0) {
                query = "update Teacher set passw = '" + newPassword + "' where mail = '" + email + "'";
            }
            if (user == 1) {
                query = "update Supervisor set passw = '" + newPassword + "' where mail = '" + email + "'";
            }
            stmt.executeUpdate(query);
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }
    }

    /**
     * Method to write new reservations in the database.
     * 
     * @param r a reservation
     * @param cl classroom where you book
     */
    public void writeReservation(Reservation r, String cl) {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception E) {
            System.err.println("Non trovo il driver da caricare.");
            E.printStackTrace();
        }
        
        try {
            Statement stmt = conn.createStatement();
            String query = "INSERT into Reservation values('" + r.getId() + "', '" + r.getDBDate() + "', '" + r.getStartHour() + "', '" + r.getEndHour() + "', '" + r.getDescription() + "', '" + cl + "', null, null)";
            stmt.executeUpdate(query);
            stmt.close();
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }
    }

    /**
     * Method to delete a reservation, identified by its id.
     * 
     * @param id id a reservation
     */
    public void deleteReservation(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception E) {
            System.err.println("Non trovo il driver da caricare.");
            E.printStackTrace();
        }
        try {
            Statement stmt = conn.createStatement();
            String query = "delete from Reservation where id = " + id;
            stmt.executeUpdate(query);
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }
    }
}
